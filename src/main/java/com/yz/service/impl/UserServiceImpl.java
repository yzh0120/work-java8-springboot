package com.yz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.code.kaptcha.Producer;
import com.yz.dto.req.UserLogin;
import com.yz.dto.res.Captcha;
import com.yz.dto.res.UserToken;
import com.yz.entity.User;
import com.yz.mapper.UserMapper;
import com.yz.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yz.utils.JwtUtils;
import com.yz.utils.RedisUtil;
import com.yz.utils.exception.BusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yz
 * @since 2022-04-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Value("${yz.jwt.expire}")
    private long expire;

    @Autowired
    Producer producer;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public Captcha getCaptcha() {
        String captchaToken = UUID.randomUUID().toString();
        String code = producer.createText();

        // 为了测试
        captchaToken = "aaaaa";
        code = "11111";

        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encode(outputStream.toByteArray());
        redisUtil.hset("captcha", captchaToken, code, 120);


        /*return MapUtil.builder()
                .put("token", key)
                .put("captchaImg", base64Img)
                .build();*/

        Captcha captcha = new Captcha();
        captcha.setCaptchaToken(captchaToken);
        captcha.setCaptchaImgBase64(base64Img);
        return captcha;
    }


    @Override
    public UserToken checkLogin(UserLogin userLogin, HttpServletResponse resp) {
        //1 判断验证码和验证码token是否正确
        if (StringUtils.isBlank(userLogin.getCaptchaImgBase64Code()) || StringUtils.isBlank(userLogin.getCaptchaToken())) {
            throw new BusException("验证码或者token为空");
        }
        if (!userLogin.getCaptchaImgBase64Code().equals(redisUtil.hget("captcha", userLogin.getCaptchaToken()))) {
            throw new BusException("验证码错误");
        }

        //2 检查数据库中是否有此用户
        User user = userService.getOne(new QueryWrapper<User>().
                eq("username", userLogin.getUsername())
                .eq("password", userLogin.getPassword()));

        if(user != null){
            // 生成jwt，并放置到请求头中
            String jwt = jwtUtils.generateToken(user.getId());
            //返回 jwt 过期时间
            Date nowDate = new Date();
            Date expireDate = new Date(nowDate.getTime() + 1000 * expire);
            System.out.println(expireDate + "---" + expire);
            UserToken userToken = new UserToken();
            userToken.setExpireDateTimeSpan(expireDate.getTime());
            userToken.setToken(jwt);
            return userToken;
        }else {
            throw new BusException("用户不存在");
        }

    }

}

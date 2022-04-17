package com.yz.service.impl;

import com.google.code.kaptcha.Producer;
import com.yz.dto.res.CaptchaDTO;
import com.yz.entity.UserEntity;
import com.yz.mapper.UserMapper;
import com.yz.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yz.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yz
 * @since 2022-04-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    @Value("${yz.jwt.expire}")
    private long expire;

    @Autowired
    Producer producer;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public CaptchaDTO getCaptcha() {
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

        CaptchaDTO captchaDTO = new CaptchaDTO();
        captchaDTO.setCaptchaToken(captchaToken);
        captchaDTO.setCaptchaImgBase64(base64Img);
        return captchaDTO;
    }
}

package com.yz.service;

import com.yz.dto.req.UserLogin;
import com.yz.dto.res.Captcha;
import com.yz.dto.res.UserToken;
import com.yz.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yz
 * @since 2022-04-14
 */
public interface UserService extends IService<User> {
    Captcha getCaptcha();

    UserToken checkLogin(UserLogin userLogin, HttpServletResponse resp);
}

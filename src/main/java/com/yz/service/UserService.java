package com.yz.service;

import com.yz.dto.res.CaptchaDTO;
import com.yz.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yz
 * @since 2022-04-17
 */
public interface UserService extends IService<UserEntity> {

    CaptchaDTO getCaptcha();
}

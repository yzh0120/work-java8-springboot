package com.yz.controller;


import com.yz.dto.res.CaptchaDTO;
import com.yz.entity.UserEntity;
import com.yz.service.UserService;
import com.yz.utils.web.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yz
 * @since 2022-04-17
 */
@RestController
@RequestMapping("/user-entity")
@Api(tags = "用户模块")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/captcha")
    @ApiOperation(value = "获取验证码", notes = "详细描述")
    public ApiResult<CaptchaDTO> captcha() {
        return ApiResult.success(userService.getCaptcha());
    }
}

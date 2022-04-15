package com.yz.controller;


import com.yz.dto.req.UserLogin;
import com.yz.service.UserService;
import com.yz.utils.web.ApiResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yz
 * @since 2022-04-14
 */
@RestController
@RequestMapping("/user")
@Api(tags = "电子保函-上海银行(完成)")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/captcha")
    @ApiOperation(value="获取验证码", notes="详细描述")
    public ApiResult captcha(){
        return ApiResult.success(userService.getCaptcha());
    }

    @PostMapping("/login")
    @ApiOperation(value="用户登录", notes="详细描述")
    public ApiResult login(@ApiParam(value = "用户登录参数", required = true) @RequestBody UserLogin userLogin, HttpServletResponse resp){
        return ApiResult.success(userService.checkLogin(userLogin,resp));
    }
}

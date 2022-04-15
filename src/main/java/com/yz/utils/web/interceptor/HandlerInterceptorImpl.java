package com.yz.utils.web.interceptor;




import com.yz.utils.JwtUtils;
import com.yz.utils.exception.BusException;
import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Method;

public class HandlerInterceptorImpl implements HandlerInterceptor {
    @Autowired
    JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println(handler+"---handler");
        if (!(handler instanceof HandlerMethod)){// handler 不是  HandlerMethod 的实例则不处理
            return true;
        }

        //handler   //com.yz.controller.demoController#demo()
        //handlerMethod 对象 和 handler差不多 //
        
        //08.SpringMVC 处理器 - HandlerMethod
        //https://blog.csdn.net/u012420654/article/details/59480498?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1.pc_relevant_default&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1.pc_relevant_default&utm_relevant_index=1
        HandlerMethod handlerMethod = (HandlerMethod)handler;//
        Class cls = handlerMethod.getBeanType();//class ---com.yz.controller.demoController
        Method method = handlerMethod.getMethod();//public java.lang.String --com.yz.controller.demoController.demo()

//        System.out.println(cls+"---cls");
//        System.out.println(method+"---method");

        //检查是否不需要认证
        if (method.isAnnotationPresent(PassToken.class)){
            return true;
        }

        //检查是否需要用户认证
        if (cls.isAnnotationPresent(UserLoginToken.class) || method.isAnnotationPresent(UserLoginToken.class)){
            String authorization = request.getHeader("Authorization");
            if (!authorization.startsWith("Bearer ")) {
                throw new BusException("请检查认证信息");
            }
            String token = authorization.replace("Bearer ", "");


            //解析 token
            Claims claim = jwtUtils.getClaimByToken(token);
            // claim 为空说明解析失败
            if (claim == null) {
                throw new BusException("token错误");
            }
            //token过期
            if (jwtUtils.isTokenExpired(claim)) {
                throw new BusException("token已过期");
            }

            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

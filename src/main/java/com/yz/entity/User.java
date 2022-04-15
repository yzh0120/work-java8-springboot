package com.yz.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yz
 * @since 2022-04-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "用户实体")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("用户密码")
    //使用@JsonIgnore注解，忽略此属性，前端不会拿到该属性
    @JsonIgnore
    private String password;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("性别")
    private Integer sex;

    @ApiModelProperty("联系方式")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("最后登录时间")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty("注册时间")
    private LocalDateTime registrationTime;


}

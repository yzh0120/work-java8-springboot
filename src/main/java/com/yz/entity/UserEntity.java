package com.yz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2022-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="user")
@ApiModel(description = "用户实体")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value ="ID")
    private String id;

    @ApiModelProperty(value ="用户名")
    private String username;

    @ApiModelProperty(value ="用户密码")
    //使用@JsonIgnore注解，忽略此属性，前端不会拿到该属性
    @JsonIgnore
    private String password;

    @ApiModelProperty(value ="年龄")
    private Integer age;

    @ApiModelProperty(value ="性别")
    private Integer sex;

    @ApiModelProperty(value ="联系方式")
    private String phone;

    @ApiModelProperty(value ="邮箱")
    private String email;

    @ApiModelProperty(value ="最后登录时间")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value ="注册时间")
    private LocalDateTime registrationTime;


}

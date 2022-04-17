package com.yz.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserLoginDTO {
    @ApiModelProperty(value = "用户名称",required=true)
    public String username;

    @ApiModelProperty(value ="用户密码",required=true)
    public String password;

    @ApiModelProperty(value ="token",required=true)
    public String captchaToken;

    @ApiModelProperty(value ="图片验证码",required=true)
    public String captchaImgBase64Code;
}


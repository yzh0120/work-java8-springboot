package com.yz.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value ="返回实体")
public class CaptchaDTO {
    @ApiModelProperty(value ="验证码的token")
    private String captchaToken;

    @ApiModelProperty(value ="验证码")
    private String captchaImgBase64;
}

package com.yz.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value ="登录成功返回对象")
public class UserTokenDTO {

    @ApiModelProperty(value ="token")
    private String token;

    @ApiModelProperty(value ="时间戳")
    private Long expireDateTimeSpan;
}

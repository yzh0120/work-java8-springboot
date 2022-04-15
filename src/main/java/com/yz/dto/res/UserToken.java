package com.yz.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("登录成功返回对象")
public class UserToken {

    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("时间戳")
    private Long expireDateTimeSpan;
}

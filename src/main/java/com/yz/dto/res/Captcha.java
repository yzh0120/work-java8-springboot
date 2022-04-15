package com.yz.dto.res;

import lombok.Data;

@Data
public class Captcha {
    private String captchaToken;
    private String captchaImgBase64;
}

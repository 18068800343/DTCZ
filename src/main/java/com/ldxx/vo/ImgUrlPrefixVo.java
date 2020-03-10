package com.ldxx.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImgUrlPrefixVo {

    @Value("${img.url.prefix}")
    private String imgUrlPrefix;

    public String getImgUrlPrefix() {
        return imgUrlPrefix;
    }

    public void setImgUrlPrefix(String imgUrlPrefix) {
        this.imgUrlPrefix = imgUrlPrefix;
    }
}

package com.ldxx.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImgUrlPrefixVo {

    @Value("${img.url.prefix}")
    private String imgUrlPrefix;//图片的前缀地址

    @Value("${yujingUrl}")
    private String yujingUrl;//预警称重数据接口

    @Value("${fujianUrl}")
    private String fujianUrl;//附件路径

    @Value("${loginUrl}")
    private String loginUrl;//附件路径

    @Value("${indexUrl}")
    private String indexUrl;//附件路径

    public String getIndexUrl() {
        return indexUrl;
    }

    public void setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getFujianUrl() {
        return fujianUrl;
    }

    public void setFujianUrl(String fujianUrl) {
        this.fujianUrl = fujianUrl;
    }

    public String getYujingUrl() {
        return yujingUrl;
    }

    public void setYujingUrl(String yujingUrl) {
        this.yujingUrl = yujingUrl;
    }

    public String getImgUrlPrefix() {
        return imgUrlPrefix;
    }

    public void setImgUrlPrefix(String imgUrlPrefix) {
        this.imgUrlPrefix = imgUrlPrefix;
    }
}

package com.study.netty.route;

import java.util.List;

/**
 * @author hong.zheng
 * @Date: 10/6/21 3:01 PM
 **/
public class Route {
    private String fromUrl;

    private List<String> toUrl;


    public Route(String fromUrl, List<String> toUrl) {
        this.fromUrl = fromUrl;
        this.toUrl = toUrl;
    }

    public String getFromUrl() {
        return fromUrl;
    }

    public void setFromUrl(String fromUrl) {
        this.fromUrl = fromUrl;
    }

    public List<String> getToUrl() {
        return toUrl;
    }

    public void setToUrl(List<String> toUrl) {
        this.toUrl = toUrl;
    }
}
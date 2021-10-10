package com.study.netty.route;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hong.zheng
 * @Date: 10/6/21 3:04 PM
 **/
public class RouteRepository {

    private static Map<String,Route> ROUTE_MAP = new HashMap<>();

    static {
        ROUTE_MAP.put("/hello",new Route("/hello", Arrays.asList("http://localhost:8081/hello",
                "http://localhost:8082/hello")));
    }

    public static Route getRoute(String fromUrl){
        fromUrl = formatUrl(fromUrl);
        Route route = ROUTE_MAP.get(fromUrl);
        if(route == null){
            throw new IllegalArgumentException("the fromUrl "+fromUrl+"can't match any route");
        }
        return route;
    }

    private static String formatUrl(String url) {
        return url.endsWith("/")?url.substring(0,url.length()-1):url;
    }

}
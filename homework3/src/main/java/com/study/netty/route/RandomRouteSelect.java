package com.study.netty.route;

import java.util.List;
import java.util.Random;

/**
 * @author hong.zheng
 * @Date: 10/6/21 3:04 PM
 **/
public class RandomRouteSelect implements RouteSelect {
    private Random rand;

    public RandomRouteSelect() {
        rand = new Random();
    }


    @Override
    public String select(String url) {
        Route route = RouteRepository.getRoute(url);

        List<String> toUrls = route.getToUrl();
        int index = rand.nextInt(toUrls.size());


        return toUrls.get(index);
    }
}
package com.study.rpc.api.core;

import java.util.List;

public interface LoadBalancer {

    String select(List<String> urls);

}

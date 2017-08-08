package com.bruce.ffrpc.server.config;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @Author bruce.ge
 * @Date 2017/8/8
 * @Description
 */
@Getter
@Setter
public class ServerConfig {
    private String appId;

    private String group;

    private String protocol;

    private Integer threadPoolSize;

    private Integer bufferQueueSize;

    Map<Class, Object> serviceClassMap;
}

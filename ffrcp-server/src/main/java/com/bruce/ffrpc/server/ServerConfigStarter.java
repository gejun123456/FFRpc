package com.bruce.ffrpc.server;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

/**
 * @Author bruce.ge
 * @Date 2017/8/7
 * @Description
 */
@Getter
@Setter
public class ServerConfigStarter implements ApplicationListener<ContextRefreshedEvent> {
    private String appId;

    private String group;

    private String protocol;

    private Integer threadPoolSize;

    private Integer bufferQueueSize;

    private List<Class> classList;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }

}

package com.bruce.ffrpc.server;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
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

    private List<String> classList;

    // TODO: 2017/8/7 start the server on application event.
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context =
                event.getApplicationContext();
        for (String ss : classList) {
            try {
                Class s = Class.forName(ss);
                Object bean = context.getBean(s);
                ServiceProvider.addService(s, bean);
            } catch (ClassNotFoundException e) {
                throw new Error("class not found for:" + ss);
            }
        }
    }
}

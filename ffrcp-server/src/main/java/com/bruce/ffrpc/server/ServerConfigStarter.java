package com.bruce.ffrpc.server;

import com.bruce.ffrpc.server.config.ServerConfig;
import com.bruce.ffrpc.server.realserver.NettyServer;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;
import java.util.Map;

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
        Map<Class, Object> serviceClassMap = Maps.newHashMap();
        for (String ss : classList) {
            Class s = null;
            try {
                s = Class.forName(ss);
            } catch (ClassNotFoundException e) {
                throw new Error("class not found for:" + ss);
            }
            Object bean = context.getBean(s);
            if (bean == null) {
                throw new Error("can't find bean for service class name:" + ss);
            }
            serviceClassMap.put(s, bean);
        }

        ServerConfig config = new ServerConfig();
        config.setAppId(this.appId);
        config.setGroup(this.group);
        config.setProtocol(this.protocol);
        config.setThreadPoolSize(this.threadPoolSize);
        config.setBufferQueueSize(this.bufferQueueSize);
        config.setServiceClassMap(serviceClassMap);
        try {
            NettyServer.add(config);
        } catch (InterruptedException e) {
            throw new Error("server started catch exception",e);
        }
    }
}

package com.bruce.ffrpc.server.handler;

import com.bruce.ffrpc.server.config.ServerConfig;
import com.bruce.ffrpc.server.context.InvokeContext;
import com.bruce.ffrpc.server.context.MethodParam;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @Author bruce.ge
 * @Date 2017/8/8
 * @Description
 */
public class ServiceInvokeHandler extends ChannelInboundHandlerAdapter {

    //find the service and class and invoke with them.
    private ServerConfig serverConfig;

    public ServiceInvokeHandler(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
        init(serverConfig);
    }

    private void init(ServerConfig serverConfig) {
        Map<Class, Object> serviceClassMap =
                serverConfig.getServiceClassMap();
        // TODO: 2017/8/8 should build on cache to store things.
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //todo should first convert to msg to the InvokeContext class.
        InvokeContext context = (InvokeContext) msg;
        String className = context.getClassName();
        Class<?> aClass = Class.forName(className);
        Map<Class, Object> serviceClassMap = serverConfig.getServiceClassMap();
        Object o = serviceClassMap.get(aClass);
        String methodName = context.getMethodName();
        List<MethodParam> paramList = context.getParamList();
        Class[] paramsTypes = new Class[paramList.size()];
        Object[] value = new Object[paramList.size()];
        for (int i = 0; i < paramList.size(); i++) {
            paramsTypes[i] = Class.forName(paramList.get(i).getParamType());
            value[i] = paramList.get(i).getParamValue();
        }
        Method method = aClass.getMethod(methodName, paramsTypes);
        //get the method.
        //invoke the obj value.
        //should deserilize on the place.
        Object invoke = method.invoke(o, value);
        //return the value to the servier
        ctx.writeAndFlush(invoke);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}

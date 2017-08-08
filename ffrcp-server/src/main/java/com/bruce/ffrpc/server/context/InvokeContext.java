package com.bruce.ffrpc.server.context;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author bruce.ge
 * @Date 2017/8/8
 * @Description
 */
@Getter
@Setter
public class InvokeContext {
    private String className;

    private String methodName;

    private String returnType;

    private List<MethodParam> paramList;
}

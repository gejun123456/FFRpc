package com.bruce.ffrpc.server.context;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author bruce.ge
 * @Date 2017/8/8
 * @Description
 */
@Getter
@Setter
public class MethodParam {
    private String paramType;

    private Object paramValue;
}

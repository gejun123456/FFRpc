package com.bruce.ffrpc.servertest.service;

import org.springframework.stereotype.Service;

/**
 * @Author bruce.ge
 * @Date 2017/8/7
 * @Description
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public String say() {
        return "hello man";
    }
}

package com.bruce.ffrpc.servertest.service;

import com.bruce.ffrpc.servertest.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author bruce.ge
 * @Date 2017/8/7
 * @Description
 */
public class StartTest extends BaseTest{
    @Autowired
    private TestService testService;
    @Test
    public void test(){
        String say = testService.say();
        System.out.println(say);
    }

}

package com.siebre.wechat.baidu.service;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by jhonelee on 2017/9/25.
 */
@Service
public class MockBaiduRemoteService {

    private Random random;

    private Integer bound = 100000;

    public MockBaiduRemoteService () {
        this.random = new Random(System.currentTimeMillis());
    }

    public Integer evaluate(String mobile) {
        return this.random.nextInt(this.bound);
    }

}

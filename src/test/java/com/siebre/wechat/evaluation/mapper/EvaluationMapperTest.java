package com.siebre.wechat.evaluation.mapper;

import com.siebre.wechat.config.DatabaseConfig;
import com.siebre.wechat.config.MybatisConfig;
import com.siebre.wechat.enums.EvaluationSource;
import com.siebre.wechat.enums.PaymentStatus;
import com.siebre.wechat.evaluation.module.Evaluation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by jhonelee on 2017/9/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestConfiguration
@ComponentScan(basePackages = "com.siebre.wechat.**.mapper,com.siebre.wechat.**.service")
@ContextConfiguration(classes = {
        DatabaseConfig.class,
        MybatisConfig.class
})
@TestPropertySource(locations="classpath:application.properties")
public class EvaluationMapperTest {

    @Autowired
    private EvaluationMapper evaluationMapper;

    @Test
    public void insertTest() {
        Evaluation evaluation = new Evaluation();
        evaluation.setEvaluationSource(EvaluationSource.BAIDU);
        evaluation.setScore(10000);
        evaluation.setOpenid("helloworld");
        evaluation.setMobile("13482432953");
        evaluation.setUsername("李志强");
        evaluation.setCreateDate(new Date());
        evaluation.setPaymentStatus(PaymentStatus.PAYING);
        evaluation.setOrderNumber("123456");
        evaluation.setPaySuccessDate(new Date());
        this.evaluationMapper.insert(evaluation);

        Evaluation evaluation1 = this.evaluationMapper.selectByPrimaryKey(evaluation.getId());

        Assert.assertEquals(evaluation.getId(), evaluation1.getId());
        Assert.assertEquals(evaluation.getEvaluationSource(), evaluation1.getEvaluationSource());
        Assert.assertEquals(evaluation.getScore(), evaluation1.getScore());
        Assert.assertEquals(evaluation.getOpenid(), evaluation1.getOpenid());
        Assert.assertEquals(evaluation.getMobile(), evaluation1.getMobile());
        Assert.assertEquals(evaluation.getUsername(), evaluation1.getUsername());
        Assert.assertEquals(evaluation.getPaymentStatus(), evaluation1.getPaymentStatus());
        Assert.assertEquals(evaluation.getOrderNumber(), evaluation1.getOrderNumber());
    }

    @Test
    public void selectByOpenidTest() {

        List<Evaluation> evaluations = this.evaluationMapper.selectByOpenid("123", (long) 22, 10);

        Assert.assertTrue(evaluations.size() <= 10);

    }

}

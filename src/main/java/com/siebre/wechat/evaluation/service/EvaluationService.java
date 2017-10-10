package com.siebre.wechat.evaluation.service;

import com.siebre.wechat.baidu.service.MockBaiduRemoteService;
import com.siebre.wechat.enums.EvaluationSource;
import com.siebre.wechat.evaluation.mapper.EvaluationMapper;
import com.siebre.wechat.evaluation.module.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by jhonelee on 2017/9/18.
 */
@Service
public class EvaluationService {

    @Autowired
    private EvaluationMapper evaluationMapper;

    @Autowired
    private MockBaiduRemoteService mockBaiduRemoteService;

    public Evaluation doEvaluate(String openId, String mobile, String username, EvaluationSource evaluationSource) {
        Evaluation evaluation = new Evaluation();
        evaluation.setUsername(username);
        evaluation.setMobile(mobile);
        evaluation.setEvaluationSource(EvaluationSource.BAIDU);
        evaluation.setOpenid(openId);
        evaluation.setCreateDate(new Date());
        evaluation.setScore(this.mockBaiduRemoteService.evaluate(mobile));
        this.evaluationMapper.insertSelective(evaluation);
        return evaluation;
    }

    public List<Evaluation> findByOpenid(String openid, Long lastId, Integer showCount) {
        return this.evaluationMapper.selectByOpenid(openid, lastId, showCount);
    }

}

package com.siebre.wechat.evaluation.mapper;

import com.siebre.wechat.evaluation.module.Evaluation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Evaluation record);

    int insertSelective(Evaluation record);

    Evaluation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Evaluation record);

    int updateByPrimaryKey(Evaluation record);

    List<Evaluation> selectByOpenid(@Param("openid") String openId, @Param("lastId") Long lastId, @Param("showCount") Integer showCount);

}
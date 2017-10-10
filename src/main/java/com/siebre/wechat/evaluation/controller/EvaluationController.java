package com.siebre.wechat.evaluation.controller;

import com.siebre.wechat.basic.web.WebResult;
import com.siebre.wechat.config.ErrorMessageUtils;
import com.siebre.wechat.enums.EvaluationSource;
import com.siebre.wechat.evaluation.module.Evaluation;
import com.siebre.wechat.evaluation.service.EvaluationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhonelee on 2017/9/18.
 */
@RestController
public class EvaluationController {

	@Autowired
	private EvaluationService evaluationService;

	@RequestMapping(path = "/api/v1/evaluation/evaluate", method = RequestMethod.POST)
	public WebResult<Evaluation> doEvaluate(@RequestBody @Valid EvaluateDto evaluateDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return WebResult.<Evaluation>builder().returnCode("500").returnMessage(ErrorMessageUtils.resloveErrorMessage(bindingResult)).build();
		}

        Evaluation evaluation = this.evaluationService.doEvaluate(evaluateDto.getOpenId(), evaluateDto.getMobile(), evaluateDto.getUsername(), EvaluationSource.BAIDU);

		return WebResult.<Evaluation>builder().returnCode("200").returnMessage("Success").data(evaluation).build();
	}

	@RequestMapping(path = "/api/v1/evaluations", method = RequestMethod.GET)
	public WebResult<List<Evaluation>> list(@RequestParam String openid,
											@RequestParam Long lastId,
											@RequestParam Integer showCount) {
		List<String> errorMessageList = new ArrayList<String>();

		if (StringUtils.isBlank(openid)) {
			errorMessageList.add("参数openid是必须的");
		}
		if (lastId == null) {
			errorMessageList.add("参数lastid是必须的");
		}
		if (showCount == null) {
			errorMessageList.add("参数showCount是必须的");
		}
		if (!errorMessageList.isEmpty()) {
			return WebResult.<List<Evaluation>>builder().returnCode("500").returnMessage(StringUtils.join(errorMessageList, ",")).build();
		}

		List<Evaluation> evaluations = this.evaluationService.findByOpenid(openid, lastId, showCount);

		return WebResult.<List<Evaluation>>builder().returnCode("200").returnMessage("success").data(evaluations).build();
	}


}

package com.siebre.wechat.basic.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jhonelee on 2017/9/28.
 */
@Controller
public class BaseController {

    @RequestMapping({
            "/insuranceEvaluation/index",
            "/insuranceEvaluation/evaluation",
            "/evaluationRecoder/index"
    })
    public String redirect() {
        return "forward:/index.html";
    }

}

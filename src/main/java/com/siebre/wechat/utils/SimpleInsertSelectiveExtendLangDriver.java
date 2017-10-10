package com.siebre.wechat.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhonelee on 2017/9/20.
 */
public class SimpleInsertSelectiveExtendLangDriver extends XMLLanguageDriver {

    @Override
    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {

        if (this.isInsertSql(script)) {

            String[] strs = StringUtils.substringsBetween(script, "(", ")");

            String[] columnArray = StringUtils.split(strs[0], ",");
            String[] valueArray = StringUtils.split(strs[1], ",");

            String columnStr = this.convertColumnString(columnArray, valueArray);
            String valueStr = this.convertValueStrng(columnArray, valueArray);

            script = script.replace(strs[0], columnStr);
            script = script.replace(strs[1], valueStr);

            script = "<script>" + script + "</script>";
        }

        return super.createSqlSource(configuration, script, parameterType);
    }

    private boolean isInsertSql(String script) {
        String str = StringUtils.trim(script);

        return StringUtils.startsWith(str, "insert");
    }

    private String convertColumnString(String[] columnArray, String[] valueArray) {
        List<String> result = new ArrayList<String>();

        for (int i = 0; i < columnArray.length; i ++) {
            String str = "" +
                    "<if test=\"" + StringUtils.substringBetween(StringUtils.trim(valueArray[i]), "{", "}") + " != null\">" +
                    StringUtils.trim(columnArray[i]) + (i == valueArray.length - 1 ? "" : ",") +
                    "</if>";
            result.add(str);
        }

        return StringUtils.join(result, "");
    }

    private String convertValueStrng(String[] columnArray, String[] valueArray) {
        List<String> result = new ArrayList<String>();

        for (int i = 0; i < columnArray.length; i ++) {
            String str = "" +
                    "<if test=\"" + StringUtils.substringBetween(StringUtils.trim(valueArray[i]), "{", "}") + " != null\">" +
                    StringUtils.trim(valueArray[i]) + (i == valueArray.length - 1 ? "" : ",") +
                    "</if>";
            result.add(str);
        }

        return StringUtils.join(result, "");
    }

    public static void main(String[] args) {
        String columsStr = "id, evaluation_source, score, openid, mobile, username ,create_date, payment_status, order_number, pay_success_date";
        String valuesStr = "#{id}, #{evaluationSource}, #{score}, #{openid}, #{mobile}, #{username}, #{createDate}, #{paymentStatus}, #{orderNumber}, #{paySuccessDate}";


        SimpleInsertSelectiveExtendLangDriver simpleInsertSelectiveExtendLangDriver = new SimpleInsertSelectiveExtendLangDriver();

    }
}

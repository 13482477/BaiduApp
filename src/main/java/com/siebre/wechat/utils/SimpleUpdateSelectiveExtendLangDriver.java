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
public class SimpleUpdateSelectiveExtendLangDriver extends XMLLanguageDriver {

    @Override
    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {

        if (this.isUpdateSql(script)) {
            String[] strs = StringUtils.substringsBetween(script, "set", "where");
            String[] valueArray = StringUtils.substringsBetween(script, "{", "}");

            String[] setValueArray = StringUtils.split(strs[0], ",");

            String setValueStr = this.convertSetValueString(valueArray, setValueArray);

            script = script.replace(strs[0], setValueStr);

            script = "<script>" + script + "</script>";
        }

        return super.createSqlSource(configuration, script, parameterType);
    }

    private boolean isUpdateSql(String script) {
        String str = StringUtils.trim(script);

        return StringUtils.startsWith(str, "update");
    }

    private String convertSetValueString(String[] valueArray, String[] setValueArray) {
        List<String> result = new ArrayList<String>();

        for (int i = 0; i < setValueArray.length; i ++) {
            String str = "" +
                    "<if test=\"" + valueArray[i] + " != null\">" +
                    StringUtils.trim(setValueArray[i]) + (i == setValueArray.length - 1 ? "" : ",") +
                    "</if>";
            result.add(str);
        }

        return StringUtils.join(result, "");
    }

}

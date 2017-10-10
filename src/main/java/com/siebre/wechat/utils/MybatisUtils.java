package com.siebre.wechat.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by jhonelee on 2017/9/20.
 */
public class MybatisUtils {

    public static final String TYPE_LONG = "jdbcType=BIGINT";

    public static final String TYPE_INTEGER = "jdbcType=INTEGER";

    public static final String TYPE_STRING = "jdbcType=VARCHAR";

    public static final String TYPE_DATE_TIME = "jdbcType=TIMESTAMP";

    public static final String TYPE_ENUM = "typeHandler=org.apache.ibatis.type.EnumTypeHandler";

    public static String covertColumSql(String[] colums) {
        return StringUtils.join(colums, ",");
    }


}

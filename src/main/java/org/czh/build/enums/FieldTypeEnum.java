package org.czh.build.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.czh.commons.enums.parent.IKeyEnum;

/**
 * @author : czh
 * description :
 * date : 2021/8/29
 * email 916419307@qq.com
 */
@Getter
@AllArgsConstructor
public enum FieldTypeEnum implements IKeyEnum<String> {

    /**
     * 域类型:String
     */
    FIELD_TYPE_STRING("String"),

    /**
     * 域类型:Date
     */
    FIELD_TYPE_DATE("Date"),

    /**
     * 域类型:BigDecimal
     */
    FIELD_TYPE_BIG_DECIMAL("BigDecimal"),

    /**
     * 域类型:Long
     */
    FIELD_TYPE_LONG("Long"),

    /**
     * 域类型:Integer
     */
    FIELD_TYPE_INTEGER("Integer"),

    /**
     * 域类型:Boolean
     */
    FIELD_TYPE_BOOLEAN("Boolean"),

    /**
     * 域属性:Double
     */
    FIELD_TYPE_DOUBLE("Double"),

    /**
     * 域属性:Float
     */
    FIELD_TYPE_FLOAT("Float"),

    // 占位符
    ;

    public final String key;
}

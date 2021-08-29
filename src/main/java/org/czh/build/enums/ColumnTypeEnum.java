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
public enum ColumnTypeEnum implements IKeyEnum<String> {

    /**
     * 域类型:varchar
     */
    COLUMN_TYPE_VARCHAR("varchar"),

    /**
     * 域类型：text
     */
    COLUMN_TYPE_TEXT("text"),

    /**
     * 长文本：longtext
     */
    COLUMN_TYPE_LONGTEXT("longtext"),

    /**
     * 域类型:char
     */
    COLUMN_TYPE_CHAR("char"),

    /**
     * 域类型:bigint
     */
    COLUMN_TYPE_BIGINT("bigint"),

    /**
     * 域类型:smallint
     */
    COLUMN_TYPE_SMALLINT("smallint"),

    /**
     * 域类型:int
     */
    COLUMN_TYPE_INT("int"),

    /**
     * 域类型:tinyint
     */
    COLUMN_TYPE_TINYINT("tinyint"),

    /**
     * 域类型:bit
     */
    COLUMN_TYPE_BIT("bit"),

    /**
     * 域类型:decimal
     */
    COLUMN_TYPE_DECIMAL("decimal"),

    /**
     * 域类型:numeric
     */
    COLUMN_TYPE_NUMERIC("numeric"),

    /**
     * 域类型:double
     */
    COLUMN_TYPE_DOUBLE("double"),

    /**
     * 域类型:float
     */
    COLUMN_TYPE_FLOAT("float"),

    /**
     * 域类型:date
     */
    COLUMN_TYPE_DATE("date"),

    /**
     * 域类型:time
     */
    COLUMN_TYPE_TIME("time"),

    /**
     * 域类型:datetime
     */
    COLUMN_TYPE_DATETIME("datetime"),

    /**
     * 域类型:timestamp
     */
    COLUMN_TYPE_TIMESTAMP("timestamp"),

    // 占位符
    ;

    public final String key;

}

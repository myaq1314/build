package org.czh.build.entity.eo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.czh.commons.entity.eo.IBaseEO;

/**
 * @author : czh
 * description :
 * date : 2021/8/28
 * email 916419307@qq.com
 */
@Data
@ToString
@EqualsAndHashCode
public final class ColumnMetaEO implements IBaseEO {

    private static final long serialVersionUID = -8009231767653270209L;

    /**
     * 列名称 eg id、name、sex、stock_code、sum_num_2021、avg_num2022
     */
    private String columnName;

    /**
     * 数据类型 eg bigint、date、varchar、decimal、char、datetime
     */
    private String dataType;

    /**
     * 列类型 eg bigint unsigned、date、varchar(10)、decimal(10,2)、char(16)、datetime
     */
    private String columnType;

    /**
     * 是否允许为空 eg NO、YES
     */
    private String isNullable;

    /**
     * 默认值 eg null、0、1970-01-01、0.00、空字符串、CURRENT_TIMESTAMP、
     */
    private String columnDefault;

    /**
     * 字符型长度 eg 32 varchar(32)
     */
    private String characterMaximumLength;

    /**
     * 列精确度 eg 10 int(10)
     */
    private String numericPrecision;

    /**
     * 列刻度 eg 2 decimal(18, 2)
     */
    private String numericScale;

    /**
     * Key eg PRI 主键，MUL 普通索引，UNI 唯一索引，
     */
    private String columnKey;

    /**
     * 特殊列 eg auto_increment 自增， DEFAULT_GENERATED 插入时间， DEFAULT_GENERATED on update CURRENT_TIMESTAMP 最后一次更新时间
     */
    private String extra;

    /**
     * 列描述 eg 主键
     */
    private String columnComment;

}

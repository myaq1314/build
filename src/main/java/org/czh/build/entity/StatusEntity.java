package org.czh.build.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.czh.build.enums.FieldTypeEnum;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.entity.IBaseEntity;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

/**
 * @author : czh
 * description :
 * date : 2021/8/29
 * email 916419307@qq.com
 */
@Getter
@ToString
@EqualsAndHashCode
public class StatusEntity implements IBaseEntity {

    private static final long serialVersionUID = 5998340825731570991L;

    private boolean whetherView; // 是否是视图
    private boolean hasBigDecimal; // 是否有BigDecimal导入
    private boolean hasDate; // 是否有Date导入

    private boolean hasPkCol; // 是否有自增主键
    private ColumnEntity pkCol; // 自增主键列

    private boolean hasVersionCol; // 是否有版本列
    private ColumnEntity versionCol; // 版本列

    private boolean hasInsertTimeCol; // 是否有插入时间列
    private boolean hasSelfInsertTimeCol; // 是否有自维护插入时间列
    private ColumnEntity insertTimeCol; // 自维护插入时间列

    private boolean hasUpdateTimeCol; // 是否有更新时间列
    private boolean hasSelfUpdateTimeCol; // 是否有自维护更新时间列
    private ColumnEntity selfUpdateTimeCol; // 自维护更新时间列

    private StatusEntity() {
    }

    public static StatusEntity init() {
        return new StatusEntity();
    }

    public StatusEntity configTable(@NotNullTag TableEntity tableEntity) {
        EmptyAssert.isNotNull(tableEntity);

        StatusEntity statusEntity = new StatusEntity();
        statusEntity.whetherView = EmptyValidate.isNotNull(tableEntity.getTableComment())
                && "VIEW".equalsIgnoreCase(tableEntity.getTableComment())
                && tableEntity.getTableName().toUpperCase().endsWith("VIEW");
        return statusEntity;
    }

    public void configColumnStatus(@NotNullTag ColumnEntity columnEntity) {
        EmptyAssert.isNotNull(columnEntity);

        // 是BigDecimal类型，需要导入 java.math.BigDecimal 类
        if (FieldTypeEnum.FIELD_TYPE_BIG_DECIMAL.key.equals(columnEntity.getFieldType())) {
            this.hasBigDecimal = true;
        } else if (FieldTypeEnum.FIELD_TYPE_DATE.key.equals(columnEntity.getFieldType())) {
            this.hasDate = true;
        }
    }
}

package org.czh.build.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.czh.build.entity.config.ConfigEntity;
import org.czh.build.entity.eo.ColumnMetaEO;
import org.czh.build.enums.ColumnTypeEnum;
import org.czh.build.enums.FieldTypeEnum;
import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.entity.IBaseEntity;
import org.czh.commons.utils.convertor.EnumConvertor;
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
public class ColumnEntity implements IBaseEntity {

    private static final long serialVersionUID = 8653733190112788707L;

    private String columnName; // 列名称 eg id、name、sex、stock_code、sum_num_2021、avg_num2022、studentName
    private boolean whetherVersionCol; // 是否是版本列
    private boolean whetherInsertTimeCol; // 是否是插入时间列
    private boolean whetherUpdateTimeCol; // 是否是更新时间列
    private String fieldName; // 属性名称 eg id、name、sex、stockCode、sumNum2021、avgNum2022、studentName
    private String enumName; // 枚举项名称 eg ID、NAME、SEX、STOCK_CODE、SUM_NUM_2021、AVG_NUM2022、STUDENT_NAME
    private String getterMethod; // Getter方法名称 eg getId、getName、getSex、getStockCode、getSumNum021、getAvgNum2022、getStudentName
    private String setterMethod; // Setter方法名称 eg setId、setName、setSex、setStockCode、setSumNum021、setAvgNum2022、setStudentName

    private String dataType; // 列类型 eg bigint、date、varchar、decimal、char、datetime
    private String columnType; // 列类型 eg bigint unsigned、date、varchar(10)、decimal(10,2)、char(16)、datetime
    private String fieldType; // 属性类型
    private boolean whetherBigDecimal; // 是否是 BigDecimal 类型
    private boolean whetherDate; // 是否是 Date 类型
    private boolean isVarcharType; // 是否是字符串

    private String isNullable; // 是否允许为空 eg NO、YES
    private String columnDefault; // 默认值 eg null、0、1970-01-01、0.00、空字符串、CURRENT_TIMESTAMP、
    private String characterMaximumLength; // 字符型长度 eg 32 varchar(32)
    private String numericPrecision; // 列精确度 eg 10 int(10)
    private String numericScale; // 列刻度 eg 2 decimal(18, 2)
    private String columnKey; // Key eg PRI 主键，MUL 普通索引，UNI 唯一索引，
    private String extra; // 特殊列 eg auto_increment 自增， DEFAULT_GENERATED 插入时间， DEFAULT_GENERATED on update CURRENT_TIMESTAMP 最后一次更新时间

    private String columnComment; // 列描述 eg 主键
    private String fieldComment; // 属性描述
    private String replenishComment; // 补充描述
    private boolean whetherAutoIncrementCol; // 是否是自增列
    private boolean whetherPkCol; // 是否是主键索引

    private boolean whetherSelfTimeCol; // 是否是自维护的时间列

    public static ColumnEntity init(@NotNullTag ConfigEntity configEntity, @NotNullTag ColumnMetaEO columnMetaEO) {
        EmptyAssert.allNotNull(configEntity, columnMetaEO);

        ColumnEntity columnEntity = new ColumnEntity();

        columnEntity.columnName = columnMetaEO.getColumnName();
        columnEntity.whetherVersionCol = configEntity.getBuildColumnConfig().getVersionSet().contains(columnEntity.columnName);
        columnEntity.whetherInsertTimeCol = configEntity.getBuildColumnConfig().getInsertTimeSet().contains(columnEntity.columnName);
        columnEntity.whetherUpdateTimeCol = configEntity.getBuildColumnConfig().getUpdateTimeSet().contains(columnEntity.columnName);
        columnEntity.fieldName = calculateFieldName(columnMetaEO.getColumnName());
        columnEntity.enumName = calculateEnumName(columnEntity.columnName);
        columnEntity.getterMethod = calculateGetterMethod(columnEntity.fieldName);
        columnEntity.setterMethod = calculateSetterMethod(columnEntity.fieldName);

        columnEntity.dataType = columnMetaEO.getDataType();
        columnEntity.columnType = columnMetaEO.getColumnType();
        columnEntity.fieldType = calculateFieldType(columnMetaEO.getDataType().trim().toLowerCase(), columnMetaEO.getNumericScale()).key;
        columnEntity.whetherBigDecimal = FieldTypeEnum.FIELD_TYPE_BIG_DECIMAL.key.equals(columnEntity.fieldType);
        columnEntity.whetherDate = FieldTypeEnum.FIELD_TYPE_DATE.key.equals(columnEntity.fieldType);
        columnEntity.isVarcharType = FieldTypeEnum.FIELD_TYPE_STRING.key.equals(columnEntity.fieldType);

        columnEntity.isNullable = columnMetaEO.getIsNullable();
        columnEntity.columnDefault = columnMetaEO.getColumnDefault();
        columnEntity.characterMaximumLength = columnMetaEO.getCharacterMaximumLength();
        columnEntity.numericPrecision = columnMetaEO.getNumericPrecision();
        columnEntity.numericScale = columnMetaEO.getNumericScale();
        columnEntity.columnKey = columnMetaEO.getColumnKey();
        columnEntity.extra = columnMetaEO.getExtra();

        columnEntity.columnComment = columnMetaEO.getColumnComment();
        columnEntity.fieldComment = calculateFieldComment(columnMetaEO);
        columnEntity.configReplenishComment();

        return columnEntity;
    }

    private static String calculateFieldName(String columnName) {
        if (!columnName.contains("_")) {
            if (columnName.length() > 1) {
                // 首字母小写，后续不变更大小写
                // UserName 字段，userName 属性
                return columnName.substring(0, 1).toLowerCase() + columnName.substring(1);
            } else {
                // A 字段，a 属性
                return columnName.toLowerCase();
            }
        }

        // 每一段，为空、空白字符串过滤掉，数字不过滤
        // C_SumNum_2017 字段，cSumNum2017 属性
        // C_SumNum_ 字段，cSumNum 属性
        // C_ 字段，c 属性
        String[] eachArray = columnName.split("_");
        EmptyAssert.isNotEmpty(eachArray);

        StringBuilder builder = new StringBuilder();
        for (String each : eachArray) {
            if (EmptyValidate.isBlank(each)) {
                continue;
            }
            if (each.length() > 1) {
                builder.append(each.substring(0, 1).toUpperCase()).append(each.substring(1));
            } else {
                builder.append(each.toUpperCase());
            }
        }
        String fieldName = builder.toString();
        EmptyAssert.isNotBlank(fieldName);
        if (fieldName.length() > 1) {
            fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
        } else {
            fieldName = fieldName.toLowerCase();
        }
        return fieldName;
    }

    private static String calculateEnumName(@NotBlankTag String columnName) {
        EmptyAssert.isNotBlank(columnName);

        StringBuilder builder = new StringBuilder();
        for (char c : columnName.toCharArray()) {
            // 65-90之间是大写，97-122是小写
            if (c >= 65 && c <= 90) {
                builder.append("_").append(c);
            } else {
                builder.append(c);
            }
        }
        return builder.toString().toUpperCase();
    }

    private static String calculateGetterMethod(@NotBlankTag String fieldName) {
        EmptyAssert.isNotBlank(fieldName);

        if (fieldName.length() > 1) {
            return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        } else {
            return "get" + fieldName.toUpperCase();
        }
    }

    private static String calculateSetterMethod(@NotBlankTag String fieldName) {
        EmptyAssert.isNotBlank(fieldName);

        if (fieldName.length() > 1) {
            return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        } else {
            return "set" + fieldName.toUpperCase();
        }
    }

    private static FieldTypeEnum calculateFieldType(String dataType, String numericScale) {
        ColumnTypeEnum columnTypeEnum = EnumConvertor.convertToFirst(ColumnTypeEnum.class, typeEnum -> typeEnum.key.equals(dataType));
        switch (columnTypeEnum) {
            case COLUMN_TYPE_BIGINT:
                return FieldTypeEnum.FIELD_TYPE_LONG;
            case COLUMN_TYPE_TINYINT:
            case COLUMN_TYPE_SMALLINT:
            case COLUMN_TYPE_INT:
                return FieldTypeEnum.FIELD_TYPE_INTEGER;
            case COLUMN_TYPE_BIT:
                return FieldTypeEnum.FIELD_TYPE_BOOLEAN;
            case COLUMN_TYPE_DECIMAL:
                return FieldTypeEnum.FIELD_TYPE_BIG_DECIMAL;
            case COLUMN_TYPE_DOUBLE:
                return FieldTypeEnum.FIELD_TYPE_DOUBLE;
            case COLUMN_TYPE_FLOAT:
                return FieldTypeEnum.FIELD_TYPE_FLOAT;
            case COLUMN_TYPE_NUMERIC:
                if ("0".equalsIgnoreCase(numericScale)) {
                    return FieldTypeEnum.FIELD_TYPE_LONG;
                } else {
                    return FieldTypeEnum.FIELD_TYPE_BIG_DECIMAL;
                }
            case COLUMN_TYPE_DATE:
            case COLUMN_TYPE_DATETIME:
            case COLUMN_TYPE_TIME:
            case COLUMN_TYPE_TIMESTAMP:
                return FieldTypeEnum.FIELD_TYPE_DATE;
            case COLUMN_TYPE_VARCHAR:
            case COLUMN_TYPE_CHAR:
            case COLUMN_TYPE_TEXT:
            case COLUMN_TYPE_LONGTEXT:
                return FieldTypeEnum.FIELD_TYPE_STRING;
            default:
                throw new IllegalStateException("error column type");
        }
    }

    private static String calculateFieldComment(ColumnMetaEO columnMetaEO) {
        String fieldComment;
        if (EmptyValidate.isBlank(columnMetaEO.getColumnComment())) {
            fieldComment = columnMetaEO.getColumnName();
        } else {
            fieldComment = columnMetaEO.getColumnComment().trim()
                    .replaceAll("\n", "") // 换行
                    .replaceAll("\r", "") // 回车
                    .replaceAll("\b", "") // 退格
                    .replaceAll("\f", ""); // 换页
            int index;
            if ((index = fieldComment.indexOf(";")) > 0) {
                fieldComment = fieldComment.substring(0, index);
            }
            if (fieldComment.length() > 60) {
                fieldComment = fieldComment.substring(0, 60) + " ...";
            }
        }

        return fieldComment;
    }

    private void configReplenishComment() {
        StringBuilder builder = new StringBuilder();
        if (EmptyValidate.isNotBlank(this.extra)) {
            builder.append("、");
            if ("auto_increment".equalsIgnoreCase(this.extra)) {
                builder.append("自增");
                this.whetherAutoIncrementCol = true;
            } else if ("DEFAULT_GENERATED".equalsIgnoreCase(this.extra)) {
                builder.append("默认第一次插入数据时间");
            } else if ("DEFAULT_GENERATED on update CURRENT_TIMESTAMP".equalsIgnoreCase(this.extra)) {
                builder.append("默认最后一次更新数据时间");
            } else {
                throw new RuntimeException("记录未知的 extra 类型：" + this.extra);
            }
        }

        if (EmptyValidate.isNotBlank(this.columnKey)) {
            builder.append("、");
            if ("PRI".equalsIgnoreCase(this.columnKey)) {
                builder.append("主键索引");
                this.whetherPkCol = true;
            } else if ("MUL".equalsIgnoreCase(this.columnKey)) {
                builder.append("普通索引");
            } else if ("UNI".equalsIgnoreCase(this.columnKey)) {
                builder.append("唯一索引");
            } else {
                throw new RuntimeException("记录未知的 columnKey 类型：" + this.columnKey);
            }
        }

        if (EmptyValidate.isNotBlank(this.columnType)) {
            builder.append("、").append(this.columnType);
        }

        if (EmptyValidate.isNotBlank(this.isNullable)) {
            builder.append("、");
            if ("YES".equalsIgnoreCase(this.isNullable)) {
                builder.append("可为空");
            } else if ("NO".equalsIgnoreCase(this.isNullable)) {
                builder.append("非空");
            } else {
                throw new RuntimeException("记录未知的 isNullable 类型：" + this.isNullable);
            }
        }

        builder.append("、");
        if (EmptyValidate.isNull(this.columnDefault)) {
            if ("auto_increment".equalsIgnoreCase(this.extra)) {
                builder.append("默认为自增");
            } else {
                builder.append("默认为null");
            }
        } else if (EmptyValidate.isBlank(this.columnDefault)) {
            builder.append("默认为空字符串");
        } else {
            if ("CURRENT_TIMESTAMP".equalsIgnoreCase(this.columnDefault)) {
                builder.append("默认为").append("当前时间戳");
                this.whetherSelfTimeCol = true;
            } else {
                builder.append("默认为").append(this.columnDefault);
            }
        }

        String replenishComment = builder.toString();
        EmptyAssert.isNotBlank(replenishComment);
        this.replenishComment = " [ " + replenishComment.substring(1) + " ] ";
    }
}

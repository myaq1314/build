package org.czh.build.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.czh.build.entity.eo.TableMetaEO;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.entity.IBaseEntity;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;
import org.czh.commons.validate.NumValidate;

/**
 * @author : czh
 * description :
 * date : 2021/8/29
 * email 916419307@qq.com
 */
@Getter
@ToString
@EqualsAndHashCode
public final class TableEntity implements IBaseEntity {

    private static final long serialVersionUID = -386173764349106553L;

    /**
     * 表 名称
     */
    private String tableName;

    /**
     * 表 描述信息
     */
    private String tableComment;

    /**
     * 实体 名称
     */
    private String beanName;

    /**
     * 实体 描述信息
     */
    private String beanComment;

    private TableEntity() {
    }

    public static TableEntity init(@NotNullTag TableMetaEO tableMetaEO) {
        EmptyAssert.isNotNull(tableMetaEO);

        TableEntity tableEntity = new TableEntity();
        tableEntity.tableName = tableMetaEO.getTableName();
        tableEntity.tableComment = tableMetaEO.getTableComment();
        tableEntity.beanName = calculateBeanName(tableMetaEO.getTableName());
        tableEntity.beanComment = calculateBeanComment(tableMetaEO);
        return tableEntity;
    }

    private static String calculateBeanName(String tableName) {
        EmptyAssert.isNotBlank(tableName);
        if (!tableName.contains("_")) {
            if (tableName.length() > 1) {
                // 首字母变大写，后续不变更大小写
                // studentTable 表，StudentTable Bean
                return tableName.substring(0, 1).toUpperCase() + tableName.substring(1);
            } else {
                // a 表，A Bean
                return tableName.toUpperCase();
            }
        }

        // 每一段，为空、空白字符串、数字，过滤掉
        // t_studentTable_info_2021 表， TStudentTableInfo Bean
        // t_student_ 表，TStudent
        String[] eachArray = tableName.split("_");
        EmptyAssert.isNotEmpty(eachArray);

        StringBuilder builder = new StringBuilder();
        for (String each : eachArray) {
            if (EmptyValidate.isBlank(each) || NumValidate.isInt(each = each.trim())) {
                continue;
            }
            if (each.length() > 1) {
                builder.append(each.substring(0, 1).toUpperCase()).append(each.substring(1));
            } else {
                builder.append(each.toUpperCase());
            }
        }
        String baseName = builder.toString();
        EmptyAssert.isNotBlank(baseName);
        return baseName;
    }

    private static String calculateBeanComment(TableMetaEO tableMetaEO) {
        if (EmptyValidate.isBlank(tableMetaEO.getTableComment())) {
            return tableMetaEO.getTableName();
        }

        String tableComment = tableMetaEO.getTableComment().trim()
                .replaceAll("\n", "") // 换行
                .replaceAll("\r", "") // 回车
                .replaceAll("\b", "") // 退格
                .replaceAll("\f", ""); // 换页
        int index;
        if ((index = tableComment.indexOf(";")) > 0) {
            tableComment = tableComment.substring(0, index);
        }
        if (tableComment.length() > 60) {
            return tableComment.substring(0, 60) + " ...";
        }
        return tableComment;
    }
}

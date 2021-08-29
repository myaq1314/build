package org.czh.build.entity.config;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.czh.commons.annotations.tag.NotEmptyTag;
import org.czh.commons.entity.IBaseEntity;
import org.czh.commons.utils.FieldUtil;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : czh
 * description :
 * date : 2021/8/28
 * email 916419307@qq.com
 */
@Getter
@ToString
@EqualsAndHashCode
public final class BuildTableConfig implements IBaseEntity {

    private static final long serialVersionUID = 737171332523173551L;

    /**
     * 要生成的表名称
     */
    private Set<String> tableNameSet;

    /**
     * 要生成的表名称匹配正则表达式
     */
    private Set<String> regexSet;

    private BuildTableConfig() {
    }

    public static BuildTableConfig init() {
        return new BuildTableConfig();
    }

    public BuildTableConfig configTableName(@NotEmptyTag String... tableNames) {
        EmptyAssert.isNotEmpty(tableNames);
        return config("tableNameSet", tableNames);
    }

    public BuildTableConfig configRegex(@NotEmptyTag String... regexs) {
        EmptyAssert.isNotEmpty(regexs);
        return config("regexSet", regexs);
    }

    private BuildTableConfig config(String fieldName, String... rules) {
        EmptyAssert.isNotEmpty(rules);

        Field field = FieldUtil.getField(BuildTableConfig.class, fieldName);
        Object fieldValue = FieldUtil.readField(this, field);
        if (EmptyValidate.isNull(fieldValue)) {
            fieldValue = new HashSet<String>();
            FieldUtil.writeField(this, field, fieldValue);
        }

        //noinspection unchecked
        Set<String> set = (Set<String>) fieldValue;
        for (String rule : rules) {
            if (EmptyValidate.isNotBlank(rule)) {
                set.add(rule);
            }
        }
        return this;
    }
}

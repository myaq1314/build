package org.czh.build.entity.config;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.czh.build.entity.ElementEntity;
import org.czh.build.enums.ProjectKey;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.utils.FieldUtil;
import org.czh.commons.utils.PropertyUtil;
import org.czh.commons.validate.EmptyAssert;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021/8/28
 * email 916419307@qq.com
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class FileConfig extends ElementEntity {

    private static final long serialVersionUID = -681717035861989834L;

    private FileConfig() {
    }

    public static FileConfig init(@NotNullTag ProjectKey projectKey) {
        EmptyAssert.isNotNull(projectKey);

        FileConfig fileConfig = new FileConfig();
        List<Field> fieldList = FieldUtil.queryFieldList(FileConfig.class, field -> !field.getName().equals("serialVersionUID"));
        for (Field field : fieldList) {
            FieldUtil.writeField(fileConfig, field, getProperty(projectKey, field.getName()));
        }

        return fileConfig;
    }

    private static String getProperty(ProjectKey projectKey, String fieldName) {
        String propertyValue = PropertyUtil.getProperty(projectKey.key + "-" + fieldName + "-file");
        EmptyAssert.isNotNull(propertyValue);
        return propertyValue;
    }
}
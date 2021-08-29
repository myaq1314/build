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
public final class PathConfig extends ElementEntity {

    private static final long serialVersionUID = -5756729201211715631L;

    private PathConfig() {
    }

    public static PathConfig init(@NotNullTag ProjectKey projectKey) {
        EmptyAssert.isNotNull(projectKey);

        PathConfig pathConfig = new PathConfig();
        List<Field> fieldList = FieldUtil.queryFieldList(PathConfig.class, field -> !field.getName().equals("serialVersionUID"));
        for (Field field : fieldList) {
            FieldUtil.writeField(pathConfig, field, getProperty(projectKey, field.getName()));
        }

        return pathConfig;
    }

    private static String getProperty(ProjectKey projectKey, String fieldName) {
        String propertyValue = PropertyUtil.getProperty(projectKey.key + "-" + fieldName + "-path");
        EmptyAssert.isNotNull(propertyValue);
        return propertyValue;
    }
}

package org.czh.build.entity.config;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.czh.build.enums.ProjectKey;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.entity.IBaseEntity;
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
@ToString
@EqualsAndHashCode
public final class DbConfig implements IBaseEntity {

    private static final long serialVersionUID = 8325764603682672900L;

    /**
     * 数据库URL
     */
    private String driverUrl;

    /**
     * 数据库用户
     */
    private String username;

    /**
     * 数据库密码
     */
    private String password;

    /**
     * 数据库
     */
    private String schema;

    private DbConfig() {
    }

    public static DbConfig init(@NotNullTag ProjectKey projectKey) {
        EmptyAssert.isNotNull(projectKey);

        DbConfig dbConfig = new DbConfig();
        List<Field> fieldList = FieldUtil.queryFieldList(DbConfig.class,
                field -> !field.getName().equals("serialVersionUID") && !field.getName().equals("schema"));
        for (Field field : fieldList) {
            FieldUtil.writeField(dbConfig, field, getProperty(projectKey, field.getName()));
        }
        dbConfig.schema = dbConfig.driverUrl
                .substring(dbConfig.driverUrl.lastIndexOf("/") + 1, dbConfig.driverUrl.indexOf("?"));

        return dbConfig;
    }

    private static String getProperty(ProjectKey projectKey, String fieldName) {
        String propertyValue = PropertyUtil.getProperty(projectKey.key + "-" + fieldName);
        EmptyAssert.isNotNull(propertyValue);
        return propertyValue;
    }

}

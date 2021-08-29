package org.czh.build.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
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
public final class AuthEntity implements IBaseEntity {

    private static final long serialVersionUID = -1594240074047231592L;

    /**
     * 作者名称
     */
    private String authName;

    /**
     * 作者邮箱
     */
    private String authEmail;

    private AuthEntity() {
    }

    public static AuthEntity init() {
        AuthEntity authEntity = new AuthEntity();
        List<Field> fieldList = FieldUtil.queryFieldList(AuthEntity.class, field -> !field.getName().equals("serialVersionUID"));
        for (Field field : fieldList) {
            FieldUtil.writeField(authEntity, field, getProperty(field.getName()));
        }
        return authEntity;
    }

    private static String getProperty(String fieldName) {
        String propertyValue = PropertyUtil.getProperty(fieldName);
        EmptyAssert.isNotNull(propertyValue);
        return propertyValue;
    }
}

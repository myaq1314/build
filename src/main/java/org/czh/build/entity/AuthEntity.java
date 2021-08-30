package org.czh.build.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.czh.commons.constant.DateConstant;
import org.czh.commons.entity.IBaseEntity;
import org.czh.commons.utils.DateUtil;
import org.czh.commons.utils.PropertyUtil;
import org.czh.commons.validate.EmptyAssert;

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

    /**
     * 构建日期，格式 yyyy/MM/dd
     */
    private String buildDate;

    private AuthEntity() {
    }

    public static AuthEntity init() {
        AuthEntity authEntity = new AuthEntity();
        authEntity.authName = getProperty("authName");
        authEntity.authEmail = getProperty("authEmail");
        authEntity.buildDate = DateUtil.getNowText(DateConstant.DATE_SLASH());
        return authEntity;
    }

    private static String getProperty(String fieldName) {
        String propertyValue = PropertyUtil.getProperty(fieldName);
        EmptyAssert.isNotNull(propertyValue);
        return propertyValue;
    }
}

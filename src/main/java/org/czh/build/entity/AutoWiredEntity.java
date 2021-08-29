package org.czh.build.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.czh.build.entity.config.ConfigEntity;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.utils.FieldUtil;
import org.czh.commons.validate.EmptyAssert;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021/8/29
 * email 916419307@qq.com
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class AutoWiredEntity extends ElementEntity {

    private static final long serialVersionUID = 8888100731542217060L;

    private AutoWiredEntity() {
    }

    public static AutoWiredEntity init(@NotNullTag ConfigEntity configEntity, @NotNullTag NameEntity nameEntity) {
        EmptyAssert.allNotNull(configEntity, nameEntity);

        AutoWiredEntity autoWiredEntity = new AutoWiredEntity();
        List<Field> fieldList = FieldUtil.queryFieldList(AutoWiredEntity.class, field -> !field.getName().equals("serialVersionUID"));
        for (Field field : fieldList) {
            String nameValue = FieldUtil.readField(nameEntity, field.getName());
            if (nameValue.length() > 1) {
                FieldUtil.writeField(autoWiredEntity, field, nameValue.substring(0, 1).toLowerCase() + nameValue.substring(1));
            } else {
                FieldUtil.writeField(autoWiredEntity, field, nameValue.toLowerCase());
            }
        }
        return autoWiredEntity;
    }
}
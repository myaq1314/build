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
public final class NameEntity extends ElementEntity {

    private static final long serialVersionUID = 2118963024259003563L;

    private NameEntity() {
    }

    public static NameEntity init(@NotNullTag ConfigEntity configEntity,
                                  @NotNullTag TableEntity tableEntity) {
        EmptyAssert.allNotNull(configEntity, tableEntity);

        NameEntity nameEntity = new NameEntity();
        List<Field> fieldList = FieldUtil.queryFieldList(NameEntity.class, field -> !field.getName().equals("serialVersionUID"));
        for (Field field : fieldList) {
            String preValue = FieldUtil.readField(configEntity.getPreConfig(), field.getName());
            String afterValue = FieldUtil.readField(configEntity.getAfterConfig(), field.getName());
            FieldUtil.writeField(nameEntity, field, String.format("%s%s%s", preValue, tableEntity.getBeanName(), afterValue));
        }
        return nameEntity;
    }
}

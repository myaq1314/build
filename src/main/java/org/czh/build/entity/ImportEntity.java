package org.czh.build.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
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
public final class ImportEntity extends ElementEntity {

    private static final long serialVersionUID = -3505294526787676332L;

    private ImportEntity() {
    }

    public static ImportEntity init(@NotNullTag PackageEntity packageEntity, @NotNullTag NameEntity nameEntity) {
        EmptyAssert.allNotNull(packageEntity, nameEntity);

        ImportEntity importEntity = new ImportEntity();
        List<Field> fieldList = FieldUtil.queryFieldList(ImportEntity.class, field -> !field.getName().equals("serialVersionUID"));
        for (Field field : fieldList) {
            String packageValue = FieldUtil.readField(packageEntity, field.getName());
            String nameValue = FieldUtil.readField(nameEntity, field.getName());
            FieldUtil.writeField(importEntity, field, String.format("%s.%s", packageValue, nameValue));
        }
        return importEntity;
    }
}

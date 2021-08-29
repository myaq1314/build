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
public final class PackageEntity extends ElementEntity {

    private static final long serialVersionUID = -6441206301024750644L;

    private PackageEntity() {
    }

    public static PackageEntity init(@NotNullTag ConfigEntity configEntity) {
        EmptyAssert.isNotNull(configEntity);

        PackageEntity packageEntity = new PackageEntity();
        List<Field> fieldList = FieldUtil.queryFieldList(PackageEntity.class, field -> !field.getName().equals("serialVersionUID"));
        for (Field field : fieldList) {
            FieldUtil.writeField(packageEntity, field, FieldUtil.readField(configEntity.getPackageConfig(), field.getName()));
        }
        return packageEntity;
    }
}

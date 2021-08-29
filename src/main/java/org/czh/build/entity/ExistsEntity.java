package org.czh.build.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.czh.build.enums.BooleanEnum;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.utils.FieldUtil;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.FlagAssert;

import java.io.File;
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
public final class ExistsEntity extends ElementEntity {

    private static final long serialVersionUID = 2235879318336222287L;

    private ExistsEntity() {
    }

    public static ExistsEntity init(@NotNullTag FileEntity fileEntity) {
        EmptyAssert.isNotNull(fileEntity);

        ExistsEntity existsEntity = new ExistsEntity();
        List<Field> fieldList = FieldUtil.queryFieldList(ExistsEntity.class, field -> !field.getName().equals("serialVersionUID"));
        for (Field field : fieldList) {
            String fileValue = FieldUtil.readField(fileEntity, field.getName());
            File file = new File(fileValue);
            if (file.exists()) {
                FlagAssert.isTrue(file.isFile());
                FieldUtil.writeField(existsEntity, field, BooleanEnum.TRUE.getName());
            } else {
                FieldUtil.writeField(existsEntity, field, BooleanEnum.FALSE.getName());
            }
        }
        return existsEntity;
    }
}
package org.czh.build.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.czh.build.entity.config.ConfigEntity;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.utils.FieldUtil;
import org.czh.commons.validate.EmptyAssert;

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
public final class FileEntity extends ElementEntity {

    private static final long serialVersionUID = 2235879318336222287L;

    private FileEntity() {
    }

    public static FileEntity init(@NotNullTag ConfigEntity configEntity,
                                  @NotNullTag PathEntity pathEntity,
                                  @NotNullTag NameEntity nameEntity) {
        EmptyAssert.allNotNull(configEntity, pathEntity, nameEntity);

        FileEntity fileEntity = new FileEntity();
        List<Field> fieldList = FieldUtil.queryFieldList(FileEntity.class, field -> !field.getName().equals("serialVersionUID"));
        for (Field field : fieldList) {
            String fileConfigValue = FieldUtil.readField(configEntity.getFileConfig(), field.getName());
            String pathValue = FieldUtil.readField(pathEntity, field.getName());
            String nameValue = FieldUtil.readField(nameEntity, field.getName());
            FieldUtil.writeField(fileEntity, field, String.format("%s%s%s%s", pathValue, File.separator, nameValue, fileConfigValue));
        }
        return fileEntity;
    }
}

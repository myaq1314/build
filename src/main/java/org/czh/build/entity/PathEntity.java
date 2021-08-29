package org.czh.build.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.czh.build.entity.config.ConfigEntity;
import org.czh.build.enums.BuildTypeEnum;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.utils.FieldUtil;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;
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
public final class PathEntity extends ElementEntity {

    private static final long serialVersionUID = -5272732064666714223L;

    private PathEntity() {
    }

    public static PathEntity init(@NotNullTag ConfigEntity configEntity) {
        EmptyAssert.isNotNull(configEntity);

        PathEntity pathEntity = new PathEntity();
        List<Field> fieldList = FieldUtil.queryFieldList(PathEntity.class, field -> !field.getName().equals("serialVersionUID"));
        for (Field field : fieldList) {
            FieldUtil.writeField(pathEntity, field, getPath(configEntity, field.getName()));
        }
        return pathEntity;
    }

    private static String getPath(ConfigEntity configEntity, String fieldName) {
        // /Users/czh/project/java/workspace/build_demo_project
        String projectPath = configEntity.getProjectConfig().getProjectPath();

        String buildTypeValue = FieldUtil.readField(configEntity.getBuildTypeConfig(), fieldName);
        String pathValue = FieldUtil.readField(configEntity.getPathConfig(), fieldName);
        String packageValue = FieldUtil.readField(configEntity.getPackageConfig(), fieldName);

        String path = projectPath + File.separator + pathValue + convertPackage2Path(packageValue);
        if (EmptyValidate.isNotNull(buildTypeValue) && !BuildTypeEnum.ignore.getName().equals(buildTypeValue)) {
            File directory = new File(path);
            FlagAssert.isTrue(!directory.exists() || directory.isDirectory());
            if (!directory.exists()) {
                //noinspection ResultOfMethodCallIgnored
                directory.mkdirs();
            }
        }

        return path;
    }

    private static String convertPackage2Path(String packageValue) {
        StringBuilder builder = new StringBuilder();

        if (!packageValue.contains(".")) {
            builder.append(File.separator).append(packageValue);
        } else {
            String[] eachPackageArray = packageValue.split("\\.");
            for (String eachPackage : eachPackageArray) {
                builder.append(File.separator).append(eachPackage);
            }
        }
        return builder.toString();
    }
}
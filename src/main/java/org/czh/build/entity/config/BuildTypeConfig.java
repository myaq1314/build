package org.czh.build.entity.config;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.czh.build.entity.ElementEntity;
import org.czh.build.enums.BuildTypeEnum;
import org.czh.build.enums.ElementEnum;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.utils.FieldUtil;
import org.czh.commons.validate.EmptyAssert;

import java.lang.reflect.Field;

/**
 * @author : czh
 * description :
 * date : 2021/8/28
 * email 916419307@qq.com
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class BuildTypeConfig extends ElementEntity {

    private static final long serialVersionUID = -8881956478943824145L;

    private BuildTypeConfig() {
    }

    public static BuildTypeConfig init() {
        BuildTypeConfig buildTypeConfig = new BuildTypeConfig();

        buildTypeConfig.enums = BuildTypeEnum.replace.getName();
        buildTypeConfig.baseEntity = BuildTypeEnum.replace.getName();
        buildTypeConfig.baseMapping = BuildTypeEnum.replace.getName();

        return buildTypeConfig;
    }

    public BuildTypeConfig configCreateType(@NotNullTag ElementEnum elementEnum,
                                            @NotNullTag BuildTypeEnum buildTypeEnum) {
        EmptyAssert.allNotNull(elementEnum, buildTypeEnum);

        Field field = FieldUtil.getField(BuildTypeConfig.class, elementEnum.getName());
        FieldUtil.writeField(this, field, buildTypeEnum.getName());

        return this;
    }
}

package org.czh.build.entity.config;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.czh.build.enums.InsertNameEnum;
import org.czh.build.enums.UpdateNameEnum;
import org.czh.build.enums.VersionNameEnum;
import org.czh.commons.entity.IBaseEntity;
import org.czh.commons.enums.parent.IBaseEnum;
import org.czh.commons.utils.convertor.EnumConvertor;

import java.util.Set;

/**
 * @author : czh
 * description :
 * date : 2021/8/30
 * email 916419307@qq.com
 */
@Getter
@ToString
@EqualsAndHashCode
public final class BuildColumnConfig implements IBaseEntity {

    private static final long serialVersionUID = -5874740529934544434L;

    private Set<String> versionSet;
    private Set<String> insertTimeSet;
    private Set<String> updateTimeSet;

    private BuildColumnConfig() {
    }

    public static BuildColumnConfig init() {
        BuildColumnConfig buildColumnConfig = new BuildColumnConfig();
        buildColumnConfig.versionSet = EnumConvertor.convertToSet(VersionNameEnum.class, IBaseEnum::getName);
        buildColumnConfig.insertTimeSet = EnumConvertor.convertToSet(InsertNameEnum.class, IBaseEnum::getName);
        buildColumnConfig.updateTimeSet = EnumConvertor.convertToSet(UpdateNameEnum.class, IBaseEnum::getName);
        return buildColumnConfig;
    }
}

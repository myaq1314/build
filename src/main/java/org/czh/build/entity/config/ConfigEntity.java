package org.czh.build.entity.config;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.czh.build.enums.ProjectKey;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.entity.IBaseEntity;
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
public final class ConfigEntity implements IBaseEntity {

    private static final long serialVersionUID = -6404134976384503064L;

    /**
     * 项目信息实体
     */
    private ProjectConfig projectConfig;

    /**
     * 数据库信息实体
     */
    private DbConfig dbConfig;

    /**
     * 路径组件配置信息实体
     */
    private PathConfig pathConfig;

    /**
     * 包路径组件配置信息实体
     */
    private PackageConfig packageConfig;

    /**
     * 名称前缀组件配置信息实体
     */
    private PreConfig preConfig;

    /**
     * 名称后缀组件配置信息实体
     */
    private AfterConfig afterConfig;

    /**
     * 文件种类组件配置信息实体
     */
    private FileConfig fileConfig;

    /**
     * 组件生成规则配置信息实体
     */
    private BuildTypeConfig buildTypeConfig;

    /**
     * 组件生成表规则配置信息实体
     */
    private BuildTableConfig buildTableConfig;

    /**
     * 组件生成字段规则配置信息实体
     */
    private BuildColumnConfig buildColumnConfig;

    /**
     * velocity模板配置信息实体
     */
    private VelocityConfig velocityConfig;

    private ConfigEntity() {
    }

    public static ConfigEntity init(@NotNullTag ProjectKey projectKey) {
        EmptyAssert.isNotNull(projectKey);

        ConfigEntity configEntity = new ConfigEntity();
        configEntity.projectConfig = ProjectConfig.init(projectKey);
        configEntity.dbConfig = DbConfig.init(projectKey);

        configEntity.pathConfig = PathConfig.init(projectKey);
        configEntity.packageConfig = PackageConfig.init(projectKey);
        configEntity.preConfig = PreConfig.init(projectKey);
        configEntity.afterConfig = AfterConfig.init(projectKey);
        configEntity.fileConfig = FileConfig.init(projectKey);

        configEntity.buildTypeConfig = BuildTypeConfig.init();
        configEntity.buildTableConfig = BuildTableConfig.init();
        configEntity.buildColumnConfig = BuildColumnConfig.init();

        configEntity.velocityConfig = VelocityConfig.init();

        return configEntity;
    }
}

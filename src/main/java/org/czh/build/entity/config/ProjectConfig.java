package org.czh.build.entity.config;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.czh.build.enums.ProjectKey;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.entity.IBaseEntity;
import org.czh.commons.utils.PropertyUtil;
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
public final class ProjectConfig implements IBaseEntity {

    private static final long serialVersionUID = -7268201427756896284L;

    /**
     * 项目Key
     */
    private ProjectKey projectKey;

    /**
     * 项目跟路径
     */
    private String projectPath;

    private ProjectConfig() {
    }

    public static ProjectConfig init(@NotNullTag ProjectKey projectKey) {
        EmptyAssert.isNotNull(projectKey);

        ProjectConfig projectConfig = new ProjectConfig();
        projectConfig.projectKey = projectKey;
        projectConfig.projectPath = PropertyUtil.getProperty(projectKey.key);
        return projectConfig;
    }
}

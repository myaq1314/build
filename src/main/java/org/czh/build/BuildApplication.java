package org.czh.build;

import org.czh.build.entity.config.ConfigEntity;
import org.czh.build.enums.BuildTypeEnum;
import org.czh.build.enums.ElementEnum;
import org.czh.build.enums.ProjectKey;
import org.czh.build.service.BuildService;

/**
 * @author : czh
 * description :
 * date : 2021/8/26
 * email 916419307@qq.com
 */
public class BuildApplication {

    public static void main(String[] args) {
        // todo 此处配置要生成的项目
        ConfigEntity configEntity = ConfigEntity.init(ProjectKey.BUILD_DEMO_PROJECT);

        // todo 此处配置要生成的组件
        configEntity.getBuildTypeConfig()
                // 不需要变动，必定为替换 replace
                .configCreateType(ElementEnum.enums, BuildTypeEnum.replace) // 字段枚举组件
                .configCreateType(ElementEnum.baseEntity, BuildTypeEnum.replace) // 基础Bean组件
                .configCreateType(ElementEnum.baseMapping, BuildTypeEnum.replace) // dao基本映射组件

                // 根据需要变动，可以为替换 replace，合并 merge，忽略 ignore
                .configCreateType(ElementEnum.entity, BuildTypeEnum.merge) // Bean组件
                .configCreateType(ElementEnum.dao, BuildTypeEnum.merge) // dao代理组件
                .configCreateType(ElementEnum.service, BuildTypeEnum.merge) // service服务接口组件
                .configCreateType(ElementEnum.serviceImpl, BuildTypeEnum.merge) // service服务实现组件
                .configCreateType(ElementEnum.mapping, BuildTypeEnum.merge) // dao映射组件
        ;

        // todo 此处配置要生成的表
        configEntity.getBuildTableConfig()
                // 配置完整表名称
                .configTableName(
                        "tb_example_detail",
                        "tb_example_info",
                        "tb_example_detail_view",

                        // 占位符 会自动将null过滤掉
                        null)
                // 配置表名称匹配规则
                .configRegex(
                        "^tb_[\\S]*?$", // 前缀匹配规则
                        "^[\\S]*?_info$", // 后缀匹配规则
                        "^tb_[\\S]*?_view$", // 前缀后缀匹配规则

                        // 占位符 会自动将null过滤掉
                        null);

        new BuildService().startBuild(configEntity);
    }

}

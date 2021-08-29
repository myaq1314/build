package org.czh.build.service;

import org.czh.build.entity.FinalEntity;
import org.czh.build.entity.config.ConfigEntity;
import org.czh.build.entity.eo.ColumnMetaEO;
import org.czh.build.entity.eo.TableMetaEO;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.validate.EmptyAssert;

import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021/8/28
 * email 916419307@qq.com
 */
public class BuildService {

    private final QueryService queryService = new QueryService();

    public void startBuild(@NotNullTag ConfigEntity configEntity) {
        EmptyAssert.isNotNull(configEntity);

        // 开始准备输出信息实体
        FinalEntity finalEntity = FinalEntity.init(configEntity);

        // 获取需要处理的表信息列表
        List<TableMetaEO> tableMetaEOList = queryService.queryValidTableMetaList(configEntity);
        for (TableMetaEO tableMetaEO : tableMetaEOList) {
            List<ColumnMetaEO> columnMetaEOList =
                    queryService.queryColumnMetaList(configEntity, tableMetaEO.getTableName());
            EmptyAssert.isNotEmpty(columnMetaEOList);

            // 开始配置表信息实体
            finalEntity.configTable(configEntity, tableMetaEO, columnMetaEOList);

            System.out.println(finalEntity);
        }
    }
}

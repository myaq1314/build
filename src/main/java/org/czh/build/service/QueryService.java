package org.czh.build.service;

import org.czh.build.dao.QueryDao;
import org.czh.build.entity.config.ConfigEntity;
import org.czh.build.entity.eo.ColumnMetaEO;
import org.czh.build.entity.eo.TableMetaEO;
import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author : czh
 * description :
 * date : 2021/8/29
 * email 916419307@qq.com
 */
public class QueryService {

    private final QueryDao queryDao = new QueryDao();

    public List<TableMetaEO> queryValidTableMetaList(@NotNullTag ConfigEntity configEntity) {
        EmptyAssert.isNotNull(configEntity);

        List<TableMetaEO> tableMetaEOList = this.queryDao.queryTableMetaList(configEntity);
        if (EmptyValidate.isEmpty(tableMetaEOList)) {
            return null;
        }

        Iterator<TableMetaEO> iterator = tableMetaEOList.iterator();
        Set<String> tableNameSet = configEntity.getBuildTableConfig().getTableNameSet();
        Set<String> regexSet = configEntity.getBuildTableConfig().getRegexSet();

        while (iterator.hasNext()) {
            TableMetaEO tableMetaEO = iterator.next();

            boolean containTableNameFlag = EmptyValidate.isEmpty(tableNameSet)
                    || tableNameSet.contains(tableMetaEO.getTableName());
            boolean containRegexMatchFlag = false;
            if (EmptyValidate.isEmpty(regexSet)) {
                containRegexMatchFlag = true;
            } else {
                for (String regex : regexSet) {
                    if (Pattern.compile(regex).matcher(tableMetaEO.getTableName()).find()) {
                        containRegexMatchFlag = true;
                        break;
                    }
                }
            }
            if (!containTableNameFlag || !containRegexMatchFlag) {
                iterator.remove();
            }
        }

        EmptyAssert.isNotEmpty(tableMetaEOList, "没有要生成的表");
        return tableMetaEOList;
    }

    public List<ColumnMetaEO> queryColumnMetaList(@NotNullTag ConfigEntity configEntity,
                                                  @NotBlankTag String tableName) {
        EmptyAssert.isNotNull(configEntity);
        EmptyAssert.isNotBlank(tableName);
        return this.queryDao.queryColumnMetaList(configEntity, tableName);
    }
}

package org.czh.build.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.czh.build.entity.config.ConfigEntity;
import org.czh.build.entity.eo.ColumnMetaEO;
import org.czh.build.entity.eo.TableMetaEO;
import org.czh.commons.annotations.tag.NotEmptyTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.entity.IBaseEntity;
import org.czh.commons.validate.EmptyAssert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021/8/29
 * email 916419307@qq.com
 */
@Getter
@ToString
@EqualsAndHashCode
public final class FinalEntity implements IBaseEntity {

    private static final long serialVersionUID = -5259367086829826715L;

    private AuthEntity authEntity;

    private PackageEntity packageEntity;

    private PathEntity pathEntity;
    private StatusEntity statusEntity;
    private TableEntity tableEntity;
    private NameEntity nameEntity;
    private AutoWiredEntity autoWiredEntity;
    private ImportEntity importEntity;
    private FileEntity fileEntity;
    private ExistsEntity existsEntity;
    private MergeEntity mergeEntity;
    private List<ColumnEntity> columnEntityList;
    private FinalEntity() {
    }

    public static FinalEntity init(@NotNullTag ConfigEntity configEntity) {
        EmptyAssert.isNotNull(configEntity);

        FinalEntity finalEntity = new FinalEntity();
        // 作者 信息实体，公用，根据 pom.xml文件 的 profile环境，读取 指定profile环境 下 auth.properties配置文件
        finalEntity.authEntity = AuthEntity.init();
        // 包路径 信息实体，公用，根据 包元素配置信息实体 PackageConfigEntity 生成
        finalEntity.packageEntity = PackageEntity.init(configEntity);
        // 路径 信息实体，公用，根据 路径元素配置信息实体 PathConfigEntity 生成
        finalEntity.pathEntity = PathEntity.init(configEntity);
        return finalEntity;
    }

    public void configTable(@NotNullTag ConfigEntity configEntity,
                            @NotNullTag TableMetaEO tableMetaEO,
                            @NotEmptyTag List<ColumnMetaEO> columnMetaEOList) {
        EmptyAssert.allNotNull(configEntity, tableMetaEO);
        EmptyAssert.isNotEmpty(columnMetaEOList);

        // 开始配置表名称信息实体
        configTableName(configEntity, tableMetaEO);
        // 开始配置字段信息实体
        configColumn(columnMetaEOList);
    }

    private void configTableName(ConfigEntity configEntity, TableMetaEO tableMetaEO) {
        this.tableEntity = TableEntity.init(tableMetaEO);
        this.statusEntity = StatusEntity.init();
        this.statusEntity.configTable(this.tableEntity);
        this.nameEntity = NameEntity.init(configEntity, this.tableEntity);
        this.autoWiredEntity = AutoWiredEntity.init(configEntity, this.nameEntity);
        this.importEntity = ImportEntity.init(this.packageEntity, this.nameEntity);
        this.fileEntity = FileEntity.init(configEntity, this.pathEntity, this.nameEntity);
        this.existsEntity = ExistsEntity.init(this.fileEntity);
        this.mergeEntity = MergeEntity.init(this.existsEntity, this.fileEntity);
    }

    private void configColumn(List<ColumnMetaEO> columnMetaEOList) {
        this.columnEntityList = new ArrayList<>(columnMetaEOList.size());

        for (ColumnMetaEO columnMetaEO : columnMetaEOList) {
            EmptyAssert.isNotNull(columnMetaEO);

            ColumnEntity columnEntity = ColumnEntity.init(columnMetaEO);
            this.statusEntity.configColumnStatus(columnEntity);
            this.columnEntityList.add(columnEntity);
        }
    }
}

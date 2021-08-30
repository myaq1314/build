package org.czh.build.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.entity.IBaseEntity;
import org.czh.commons.validate.EmptyAssert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021/8/31
 * email 916419307@qq.com
 */
@Getter
@ToString
@EqualsAndHashCode
public class ImportListEntity implements IBaseEntity {

    private List<String> enumsList;

    private List<String> queryBaseEntityList;
    private List<String> commonBaseEntityList;
    private List<String> primaryBaseEntityList;

    private List<String> entityList;

    private List<String> queryDaoList;
    private List<String> commonDaoList;
    private List<String> primaryDaoList;

    private List<String> queryServiceList;
    private List<String> commonServiceList;
    private List<String> primaryServiceList;

    private List<String> queryServiceImplList;
    private List<String> commonServiceImplList;
    private List<String> primaryServiceImplList;

    private ImportListEntity() {
    }

    public static ImportListEntity init(@NotNullTag PackageEntity packageEntity,
                                        @NotNullTag ImportEntity importEntity) {
        EmptyAssert.allNotNull(packageEntity, importEntity);

        ImportListEntity importListEntity = new ImportListEntity();

        importListEntity.enumsList = new ArrayList<>();
        Collections.addAll(importListEntity.enumsList,
                "lombok.Getter",
                "org.czh.commons.enums.parent.IColumnEnum",
                "lombok.AllArgsConstructor"
        );
        Collections.sort(importListEntity.enumsList);

        importListEntity.queryBaseEntityList = new ArrayList<>();
        Collections.addAll(importListEntity.queryBaseEntityList,
                "lombok.Data",
                "lombok.EqualsAndHashCode",
                "lombok.ToString",
                "org.czh.commons.entity.eo.BaseQueryEO"
        );
        Collections.sort(importListEntity.queryBaseEntityList);

        importListEntity.commonBaseEntityList = new ArrayList<>();
        Collections.addAll(importListEntity.commonBaseEntityList,
                "lombok.Data",
                "lombok.EqualsAndHashCode",
                "lombok.ToString",
                "org.czh.commons.entity.eo.BaseCommonEO"
        );
        Collections.sort(importListEntity.commonBaseEntityList);

        importListEntity.primaryBaseEntityList = new ArrayList<>();
        Collections.addAll(importListEntity.primaryBaseEntityList,
                "lombok.Data",
                "lombok.EqualsAndHashCode",
                "lombok.ToString",
                "org.czh.commons.entity.eo.BasePrimaryEO"
        );
        Collections.sort(importListEntity.primaryBaseEntityList);

        importListEntity.entityList = new ArrayList<>();
        if (!packageEntity.getBaseEntity().equals(packageEntity.getEntity())) {
            importListEntity.entityList.add(importEntity.getBaseEntity());
        }
        Collections.addAll(importListEntity.entityList,
                "lombok.Data",
                "lombok.EqualsAndHashCode",
                "lombok.NoArgsConstructor",
                "lombok.ToString"
        );
        Collections.sort(importListEntity.entityList);

        importListEntity.queryDaoList = new ArrayList<>();
        if (!packageEntity.getEntity().equals(packageEntity.getDao())) {
            importListEntity.queryDaoList.add(importEntity.getEntity());
        }
        Collections.addAll(importListEntity.queryDaoList,
                "org.apache.ibatis.annotations.Mapper",
                "org.czh.commons.dao.IBaseQueryDao"
        );
        Collections.sort(importListEntity.queryDaoList);

        importListEntity.commonDaoList = new ArrayList<>();
        if (!packageEntity.getEntity().equals(packageEntity.getDao())) {
            importListEntity.commonDaoList.add(importEntity.getEntity());
        }
        Collections.addAll(importListEntity.commonDaoList,
                "org.apache.ibatis.annotations.Mapper",
                "org.czh.commons.dao.IBaseCommonDao"
        );
        Collections.sort(importListEntity.commonDaoList);

        importListEntity.primaryDaoList = new ArrayList<>();
        if (!packageEntity.getEntity().equals(packageEntity.getDao())) {
            importListEntity.primaryDaoList.add(importEntity.getEntity());
        }
        Collections.addAll(importListEntity.primaryDaoList,
                "org.apache.ibatis.annotations.Mapper",
                "org.czh.commons.dao.IBasePrimaryDao"
        );
        Collections.sort(importListEntity.primaryDaoList);

        importListEntity.queryServiceList = new ArrayList<>();
        if (!packageEntity.getEntity().equals(packageEntity.getService())) {
            importListEntity.queryServiceList.add(importEntity.getEntity());
        }
        Collections.addAll(importListEntity.queryServiceList,
                "org.czh.commons.service.IBaseQueryService"
        );
        Collections.sort(importListEntity.queryServiceList);

        importListEntity.commonServiceList = new ArrayList<>();
        if (!packageEntity.getEntity().equals(packageEntity.getService())) {
            importListEntity.commonServiceList.add(importEntity.getEntity());
        }
        Collections.addAll(importListEntity.commonServiceList,
                "org.czh.commons.service.IBaseCommonService"
        );
        Collections.sort(importListEntity.commonServiceList);

        importListEntity.primaryServiceList = new ArrayList<>();
        if (!packageEntity.getEntity().equals(packageEntity.getService())) {
            importListEntity.primaryServiceList.add(importEntity.getEntity());
        }
        Collections.addAll(importListEntity.primaryServiceList,
                "org.czh.commons.service.IBasePrimaryService"
        );
        Collections.sort(importListEntity.primaryServiceList);

        importListEntity.queryServiceImplList = new ArrayList<>();
        if (!packageEntity.getEntity().equals(packageEntity.getServiceImpl())) {
            importListEntity.queryServiceImplList.add(importEntity.getEntity());
        }
        if (!packageEntity.getDao().equals(packageEntity.getServiceImpl())) {
            importListEntity.queryServiceImplList.add(importEntity.getDao());
        }
        Collections.addAll(importListEntity.queryServiceImplList,
                "org.springframework.stereotype.Service",
                "org.czh.commons.service.impl.BaseQueryServiceImpl"
        );
        Collections.sort(importListEntity.queryServiceImplList);

        importListEntity.commonServiceImplList = new ArrayList<>();
        if (!packageEntity.getEntity().equals(packageEntity.getServiceImpl())) {
            importListEntity.commonServiceImplList.add(importEntity.getEntity());
        }
        if (!packageEntity.getDao().equals(packageEntity.getServiceImpl())) {
            importListEntity.commonServiceImplList.add(importEntity.getDao());
        }
        Collections.addAll(importListEntity.commonServiceImplList,
                "org.springframework.stereotype.Service",
                "org.czh.commons.service.impl.BaseCommonServiceImpl"
        );
        Collections.sort(importListEntity.commonServiceImplList);

        importListEntity.primaryServiceImplList = new ArrayList<>();
        if (!packageEntity.getEntity().equals(packageEntity.getServiceImpl())) {
            importListEntity.primaryServiceImplList.add(importEntity.getEntity());
        }
        if (!packageEntity.getDao().equals(packageEntity.getServiceImpl())) {
            importListEntity.primaryServiceImplList.add(importEntity.getDao());
        }
        Collections.addAll(importListEntity.primaryServiceImplList,
                "org.springframework.stereotype.Service",
                "org.czh.commons.service.impl.BasePrimaryServiceImpl"
        );
        Collections.sort(importListEntity.primaryServiceImplList);

        return importListEntity;
    }
}

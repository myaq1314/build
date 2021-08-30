package org.czh.build.entity.config;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.czh.commons.entity.IBaseEntity;

/**
 * @author : czh
 * description :
 * date : 2021/8/30
 * email 916419307@qq.com
 */
@Getter
@ToString
@EqualsAndHashCode
public class VelocityConfig implements IBaseEntity {

    private String enums;

    private String queryBaseEntity;
    private String commonBaseEntity;
    private String primaryBaseEntity;

    private String entity;

    private String queryDao;
    private String commonDao;
    private String primaryDao;

    private String queryService;
    private String commonService;
    private String primaryService;

    private String queryServiceImpl;
    private String commonServiceImpl;
    private String primaryServiceImpl;

    private VelocityConfig() {
    }

    public static VelocityConfig init() {
        VelocityConfig velocityConfig = new VelocityConfig();

        velocityConfig.enums = "/vm/enums/Enums.java.vm";

        velocityConfig.queryBaseEntity = "/vm/entity/QueryBaseEntity.java.vm";
        velocityConfig.commonBaseEntity = "/vm/entity/CommonBaseEntity.java.vm";
        velocityConfig.primaryBaseEntity = "/vm/entity/PrimaryBaseEntity.java.vm";

        velocityConfig.entity = "/vm/entity/Entity.java.vm";

        velocityConfig.queryDao = "/vm/dao/QueryDao.java.vm";
        velocityConfig.commonDao = "/vm/dao/CommonDao.java.vm";
        velocityConfig.primaryDao = "/vm/dao/PrimaryDao.java.vm";

        velocityConfig.queryService = "/vm/service/QueryService.java.vm";
        velocityConfig.commonService = "/vm/service/CommonService.java.vm";
        velocityConfig.primaryService = "/vm/service/PrimaryService.java.vm";

        velocityConfig.queryServiceImpl = "/vm/service/QueryServiceImpl.java.vm";
        velocityConfig.commonServiceImpl = "/vm/service/CommonServiceImpl.java.vm";
        velocityConfig.primaryServiceImpl = "/vm/service/PrimaryServiceImpl.java.vm";

        return velocityConfig;
    }
}

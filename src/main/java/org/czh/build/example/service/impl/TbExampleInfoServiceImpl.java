package org.czh.build.example.service.impl;

import org.czh.build.example.dao.TbExampleInfoDao;
import org.czh.build.example.entity.eo.TbExampleInfoEO;
import org.czh.commons.service.impl.BaseCommonServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author : czh
 * description : [ 示例主表 ] 服务类基础实现类
 * date : 2021/08/31
 * email : 916419307@qq.com
 */
@Service
public final class TbExampleInfoServiceImpl
        extends BaseCommonServiceImpl<TbExampleInfoDao, TbExampleInfoEO>
        implements TbExampleInfoService {

}
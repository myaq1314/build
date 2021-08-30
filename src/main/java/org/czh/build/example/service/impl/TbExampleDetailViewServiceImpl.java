package org.czh.build.example.service.impl;

import org.czh.build.example.dao.TbExampleDetailViewDao;
import org.czh.build.example.entity.eo.TbExampleDetailViewEO;
import org.czh.build.example.service.TbExampleDetailViewService;
import org.czh.commons.service.impl.BaseCommonServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author : czh
 * description : [ VIEW ] 服务类基础实现类
 * date : 2021/08/31
 * email : 916419307@qq.com
 */
@Service
public final class TbExampleDetailViewServiceImpl
        extends BaseCommonServiceImpl<TbExampleDetailViewDao, TbExampleDetailViewEO>
        implements TbExampleDetailViewService {

}
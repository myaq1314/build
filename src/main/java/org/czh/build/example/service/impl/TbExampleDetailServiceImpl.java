package org.czh.build.example.service.impl;

import org.czh.build.example.dao.TbExampleDetailDao;
import org.czh.build.example.entity.eo.TbExampleDetailEO;
import org.czh.build.example.service.TbExampleDetailService;
import org.czh.commons.service.impl.BasePrimaryServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author : czh
 * description : [ 示例明细表 ] 服务类基础实现类
 * date : 2021/08/31
 * email : 916419307@qq.com
 */
@Service
public final class TbExampleDetailServiceImpl extends BasePrimaryServiceImpl<TbExampleDetailDao, TbExampleDetailEO>
        implements TbExampleDetailService {

}
package org.czh.build.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.czh.build.example.entity.eo.TbExampleDetailEO;
import org.czh.commons.dao.IBasePrimaryDao;

/**
 * @author : czh
 * description : [ 示例明细表 ] 数据访问对象基础接口
 * date : 2021/08/31
 * email : 916419307@qq.com
 */
@Mapper
public interface TbExampleDetailDao extends IBasePrimaryDao<TbExampleDetailEO> {

}

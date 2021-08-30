package org.czh.build.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.czh.build.example.entity.eo.TbExampleInfoEO;
import org.czh.commons.dao.IBaseCommonDao;

/**
 * @author : czh
 * description : [ 示例主表 ] 数据访问对象基础接口
 * date : 2021/08/31
 * email : 916419307@qq.com
 */
@Mapper
public interface TbExampleInfoDao extends IBaseCommonDao<TbExampleInfoEO> {

}

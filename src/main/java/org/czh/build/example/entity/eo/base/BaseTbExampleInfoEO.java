package org.czh.build.example.entity.eo.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.czh.commons.entity.eo.BaseCommonEO;

import java.util.Date;

/**
 * @author : czh
 * description : [ 示例主表 ] 实体对象基类
 * date : 2021/08/31
 * email : 916419307@qq.com
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class BaseTbExampleInfoEO extends BaseCommonEO {

    // UUID主键  [ 主键索引、varchar(32)、非空、默认为null ] 
    protected String id;

    // 名称  [ varchar(16)、非空、默认为空字符串 ] 
    protected String name;

    // 编码  [ char(8)、非空、默认为空字符串 ] 
    protected String code;

    // 逻辑标识，默认0有效，1逻辑删除  [ tinyint unsigned、非空、默认为0 ] 
    protected Integer delFlag;

    // 插入时间  [ 默认第一次插入数据时间、timestamp、非空、默认为当前时间戳 ] 
    protected Date insertTime;

    // 最后一次更新时间  [ 默认最后一次更新数据时间、datetime、非空、默认为当前时间戳 ] 
    protected Date updateTime;

}

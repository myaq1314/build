package org.czh.build.example.entity.eo.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.czh.commons.entity.eo.BasePrimaryEO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : czh
 * description : [ 示例明细表 ] 实体对象基类
 * date : 2021/08/31
 * email : 916419307@qq.com
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class BaseTbExampleDetailEO extends BasePrimaryEO {

    // 示例表ID  [ 普通索引、varchar(32)、非空、默认为空字符串 ] 
    protected String exampleId;

    // 类型  [ 唯一索引、char(8)、非空、默认为空字符串 ] 
    protected String type;

    // 宣传页  [ 普通索引、varchar(128)、非空、默认为空字符串 ] 
    protected String url;

    // 发布日期  [ 普通索引、date、非空、默认为1970-01-01 ] 
    protected Date publishDate;

    // 截止时间  [ datetime、非空、默认为1970-01-01 00:00:00 ] 
    protected Date cutOffTime;

    // 是否有效，默认1有效，0无效  [ bit(1)、非空、默认为b'1' ] 
    protected Boolean valid;

    // 价值，百分制，保留两位小数  [ decimal(5,2)、非空、默认为0.00 ] 
    protected BigDecimal worth;

    // 受众比例，百分数，保留两位小数  [ double(5,2) unsigned、非空、默认为0.00 ] 
    protected Double audiencesRate;

    // 进度，百分数，保留两位小数  [ float(5,2) unsigned、非空、默认为0.00 ] 
    protected Float progressRate;

    // 现场嘉宾数量  [ int unsigned、非空、默认为0 ] 
    protected Integer localeGuestNum;

    // 现场观众数量  [ int unsigned、非空、默认为0 ] 
    protected Integer localeAudienceNum;

    // 现场主持人数量  [ smallint、非空、默认为null ] 
    protected Integer localeCompereNum;

    // 嘉宾名单  [ text、非空、默认为null ] 
    protected String localeGuestNames;

    // 逻辑标识，默认0有效，1逻辑删除  [ tinyint unsigned、非空、默认为0 ] 
    protected Integer delFlag;

    // 插入时间  [ 默认第一次插入数据时间、timestamp、非空、默认为当前时间戳 ] 
    protected Date insertTime;

    // 最后一次更新时间  [ 默认最后一次更新数据时间、datetime、非空、默认为当前时间戳 ] 
    protected Date updateTime;

}

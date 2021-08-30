package org.czh.build.example.enums.column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.czh.commons.enums.parent.IColumnEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : czh
 * description : [ VIEW ] 字段枚举
 * date : 2021/08/31
 * email : 916419307@qq.com
 */
@Getter
@AllArgsConstructor
public enum TbExampleDetailViewEnum implements IColumnEnum {

    // UUID主键  [ varchar(32)、非空、默认为null ] 
    EXAMPLE_ID("example_id", "exampleId", String.class),

    // 名称  [ varchar(16)、非空、默认为空字符串 ] 
    NAME("name", "name", String.class),

    // 编码  [ char(8)、非空、默认为空字符串 ] 
    CODE("code", "code", String.class),

    // 类型  [ char(8)、可为空、默认为空字符串 ] 
    TYPE("type", "type", String.class),

    // 宣传页  [ varchar(128)、可为空、默认为空字符串 ] 
    URL("url", "url", String.class),

    // 发布日期  [ date、可为空、默认为1970-01-01 ] 
    PUBLISH_DATE("publish_date", "publishDate", Date.class),

    // 截止时间  [ datetime、可为空、默认为1970-01-01 00:00:00 ] 
    CUT_OFF_TIME("cut_off_time", "cutOffTime", Date.class),

    // 是否有效，默认1有效，0无效  [ bit(1)、可为空、默认为b'1' ] 
    VALID("valid", "valid", Boolean.class),

    // 价值，百分制，保留两位小数  [ decimal(5,2)、可为空、默认为0.00 ] 
    WORTH("worth", "worth", BigDecimal.class),

    // 受众比例，百分数，保留两位小数  [ double(5,2) unsigned、可为空、默认为0.00 ] 
    AUDIENCES_RATE("audiences_rate", "audiencesRate", Double.class),

    // 进度，百分数，保留两位小数  [ float(5,2) unsigned、可为空、默认为0.00 ] 
    PROGRESS_RATE("progress_rate", "progressRate", Float.class),

    // 现场嘉宾数量  [ int unsigned、可为空、默认为0 ] 
    LOCALE_GUEST_NUM("locale_guest_num", "localeGuestNum", Integer.class),

    // 现场观众数量  [ int unsigned、可为空、默认为0 ] 
    LOCALE_AUDIENCE_NUM("locale_audience_num", "localeAudienceNum", Integer.class),

    // 现场主持人数量  [ smallint、可为空、默认为null ] 
    LOCALE_COMPERE_NUM("locale_compere_num", "localeCompereNum", Integer.class),

    // 嘉宾名单  [ text、可为空、默认为null ] 
    LOCALE_GUEST_NAMES("locale_guest_names", "localeGuestNames", String.class),

    // 逻辑标识，默认0有效，1逻辑删除  [ tinyint unsigned、非空、默认为0 ] 
    DEL_FLAG("del_flag", "delFlag", Integer.class),

    // 插入时间  [ 默认第一次插入数据时间、timestamp、非空、默认为当前时间戳 ] 
    INSERT_TIME("insert_time", "insertTime", Date.class),

    // 最后一次更新时间  [ 默认最后一次更新数据时间、datetime、非空、默认为当前时间戳 ] 
    UPDATE_TIME("update_time", "updateTime", Date.class),

    // 占位符
    ;

    public final String column;
    public final String field;
    public final Class<?> type;

}

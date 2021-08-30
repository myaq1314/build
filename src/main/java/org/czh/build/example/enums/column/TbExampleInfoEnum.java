package org.czh.build.example.enums.column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.czh.commons.enums.parent.IColumnEnum;

import java.util.Date;

/**
 * @author : czh
 * description : [ 示例主表 ] 字段枚举
 * date : 2021/08/31
 * email : 916419307@qq.com
 */
@Getter
@AllArgsConstructor
public enum TbExampleInfoEnum implements IColumnEnum {

    // UUID主键  [ 主键索引、varchar(32)、非空、默认为null ] 
    ID("id", "id", String.class),

    // 名称  [ varchar(16)、非空、默认为空字符串 ] 
    NAME("name", "name", String.class),

    // 编码  [ char(8)、非空、默认为空字符串 ] 
    CODE("code", "code", String.class),

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

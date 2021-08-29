package org.czh.build.entity.eo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.czh.commons.entity.eo.IBaseEO;

/**
 * @author : czh
 * description :
 * date : 2021/8/28
 * email 916419307@qq.com
 */
@Data
@ToString
@EqualsAndHashCode
public final class TableMetaEO implements IBaseEO {

    private static final long serialVersionUID = -6887400146242291652L;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表描述信息
     */
    private String tableComment;

}

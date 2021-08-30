package org.czh.build.enums;

import org.czh.commons.enums.parent.IBaseEnum;

/**
 * @author : czh
 * description : 构建方式枚举
 * date : 2021/8/28
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public enum BuildTypeEnum implements IBaseEnum {

    replace, // 替换

    merge, // 合并

    ignore, // 忽略

    delete, // 删除

    // 占位符
    ;

}

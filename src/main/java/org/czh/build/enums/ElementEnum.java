package org.czh.build.enums;

import org.czh.commons.enums.parent.IBaseEnum;

/**
 * @author : czh
 * description : 组件枚举
 * date : 2021/8/28
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public enum ElementEnum implements IBaseEnum {

    /**
     * 字段枚举组件
     */
    enums,

    /**
     * 基础Bean组件
     */
    baseEntity,

    /**
     * Bean组件
     */
    entity,

    /**
     * dao代理组件
     */
    dao,

    /**
     * service服务接口组件
     */
    service,

    /**
     * service服务实现组件
     */
    serviceImpl,

    /**
     * dao基本映射组件
     */
    baseMapping,

    /**
     * dao映射组件
     */
    mapping,

    // 占位符
    ;
}

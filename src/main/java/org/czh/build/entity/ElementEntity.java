package org.czh.build.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.czh.commons.entity.IBaseEntity;

/**
 * @author : czh
 * description :
 * date : 2021/8/28
 * email 916419307@qq.com
 */
@Data
@ToString
@EqualsAndHashCode
public abstract class ElementEntity implements IBaseEntity {

    private static final long serialVersionUID = -3584265695155462948L;

    /**
     * 字段枚举组件
     */
    protected String enums;

    /**
     * 基础Bean组件
     */
    protected String baseEntity;

    /**
     * Bean组件
     */
    protected String entity;

    /**
     * dao代理组件
     */
    protected String dao;

    /**
     * service服务接口组件
     */
    protected String service;

    /**
     * service服务实现组件
     */
    protected String serviceImpl;

    /**
     * dao基本映射组件
     */
    protected String baseMapping;

    /**
     * dao映射组件
     */
    protected String mapping;
}

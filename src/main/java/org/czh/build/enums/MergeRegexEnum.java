package org.czh.build.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.czh.commons.enums.parent.IDictEnum;

/**
 * @author : czh
 * description :
 * date : 2021/8/29
 * email 916419307@qq.com
 */
@Getter
@AllArgsConstructor
public enum MergeRegexEnum implements IDictEnum<ElementEnum, String> {

    MERGE_ENTITY(ElementEnum.entity, ""),
    MERGE_DAO(ElementEnum.dao, ""),
    MERGE_SERVICE(ElementEnum.service, ""),
    MERGE_SERVICE_IMPL(ElementEnum.serviceImpl, ""),
    MERGE_MAPPING(ElementEnum.mapping, ""),

    // 扩展位
    ;

    public final ElementEnum key;
    public final String value;
}

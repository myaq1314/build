package org.czh.build.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.czh.commons.enums.parent.IKeyEnum;

/**
 * @author : czh
 * description : 项目编码
 * date : 2021/8/28
 * email 916419307@qq.com
 */
@Getter
@AllArgsConstructor
@SuppressWarnings("unused")
public enum ProjectKey implements IKeyEnum<String> {

    BUILD_DEMO_PROJECT("buildDemoProject"), // 示例项目

    // 占位符
    ;

    public final String key;

}

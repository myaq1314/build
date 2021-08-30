package org.czh.build.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.czh.build.enums.BooleanEnum;
import org.czh.build.enums.ElementEnum;
import org.czh.build.enums.MergeRegexEnum;
import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.utils.FieldUtil;
import org.czh.commons.utils.StreamUtil;
import org.czh.commons.utils.convertor.EnumConvertor;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;
import org.czh.commons.validate.EncodeAssert;
import org.czh.commons.validate.FileAssert;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author : czh
 * description :
 * date : 2021/8/29
 * email 916419307@qq.com
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class MergeEntity extends ElementEntity {

    private static final long serialVersionUID = 690901932790614029L;

    private MergeEntity() {
    }

    public static MergeEntity init(@NotNullTag ExistsEntity existsEntity,
                                   @NotNullTag FileEntity fileEntity) {
        EmptyAssert.allNotNull(existsEntity, fileEntity);

        MergeEntity mergeEntity = new MergeEntity();

        List<Field> fieldList = FieldUtil.queryFieldList(MergeEntity.class, field -> !field.getName().equals("serialVersionUID"));
        for (Field field : fieldList) {
            String existsValue = FieldUtil.readField(existsEntity, field.getName());
            if (EmptyValidate.isNotBlank(existsValue) && BooleanEnum.TRUE.getName().equals(existsValue)) {
                ElementEnum elementEnum = EnumConvertor.convertToFirst(ElementEnum.class, eachEnum -> eachEnum.getName().equals(field.getName()));
                MergeRegexEnum mergeRegexEnum = EnumConvertor.convertToFirst(MergeRegexEnum.class, eachEnum -> eachEnum.key.equals(elementEnum));
                if (EmptyValidate.isNull(mergeRegexEnum)) {
                    continue;
                }
                String fileValue = FieldUtil.readField(fileEntity, field.getName());
                String fileContent = readFile(fileValue);
                fileContent = Pattern.compile(mergeRegexEnum.value, Pattern.CASE_INSENSITIVE).matcher(fileContent).replaceAll("");
                FieldUtil.writeField(mergeEntity, field, fileContent);
            }
        }

        return mergeEntity;
    }

    private static String readFile(@NotBlankTag String filePath) {
        File file = FileAssert.isReadFile(filePath);
        EncodeAssert.isUtf8(file);

        StringBuilder builder = new StringBuilder();
        FileReader fr = null;
        try {
            fr = new FileReader(file);

            int len;
            char[] chars = new char[1024];
            while ((len = fr.read(chars)) != -1) {
                builder.append(new String(chars, 0, len));
            }
        } catch (IOException e) {
            throw new RuntimeException("读取文件异常");
        } finally {
            StreamUtil.close(fr);
        }
        return builder.toString();
    }
}
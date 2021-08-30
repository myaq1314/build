package org.czh.build.service;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.NullLogChute;
import org.czh.build.entity.FinalEntity;
import org.czh.build.entity.config.ConfigEntity;
import org.czh.build.enums.BuildTypeEnum;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.utils.FieldUtil;
import org.czh.commons.utils.StreamUtil;
import org.czh.commons.validate.EmptyAssert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021/8/30
 * email 916419307@qq.com
 */
public class WriteService {

    /**
     * Velocity引擎
     */
    private final VelocityEngine engine = new VelocityEngine();

    {
        engine.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM, new NullLogChute());
        engine.init();
    }

    public void startOutputFile(@NotNullTag ConfigEntity configEntity, @NotNullTag FinalEntity finalEntity) {
        EmptyAssert.allNotNull(configEntity, finalEntity);

        // 构建Velocity上下文环境
        VelocityContext velocityContext = buildVelocityContext(finalEntity);

        // 开始输出 字段枚举 文件
        outputEnumsFile(configEntity, finalEntity, velocityContext);
        // 开始输出 基础Bean 文件
        outputBaseEntityFile(configEntity, finalEntity, velocityContext);
        // 开始输出 Bean 文件
        outputEntityFile(configEntity, finalEntity, velocityContext);
        // 开始输出 dao代理 文件
        outputDaoFile(configEntity, finalEntity, velocityContext);
        // 开始输出 service服务接口 文件
        outputServiceFile(configEntity, finalEntity, velocityContext);
        // 开始输出 serviceImpl服务实现 文件
        outputServiceImplFile(configEntity, finalEntity, velocityContext);
        // 开始输出 dao基础映射 文件
        outputBaseMappingFile(configEntity, finalEntity, velocityContext);
        // 开始输出 dao映射 文件
        outputMappingFile(configEntity, finalEntity, velocityContext);
    }

    private VelocityContext buildVelocityContext(FinalEntity finalEntity) {
        VelocityContext velocityContext = new VelocityContext();

        List<Field> fieldList = FieldUtil
                .queryFieldList(FinalEntity.class, field -> !"serialVersionUID".equals(field.getName()));
        for (Field field : fieldList) {
            Object fieldValue = FieldUtil.readField(finalEntity, field);
            velocityContext.put(field.getName(), fieldValue);
        }
        return velocityContext;
    }

    /**
     * 输出 字段枚举 文件
     */
    private void outputEnumsFile(ConfigEntity configEntity,
                                 FinalEntity finalEntity,
                                 VelocityContext velocityContext) {
        // 如果为忽略，那么不做任何处理
        if (BuildTypeEnum.ignore.getName().equals(configEntity.getBuildTypeConfig().getEnums())) {
            return;
        }
        // 如果为删除，那么移除该文件
        if (BuildTypeEnum.delete.getName().equals(configEntity.getBuildTypeConfig().getEnums())) {
            String enumsFilePath = finalEntity.getFileEntity().getEnums();
            new File(enumsFilePath).deleteOnExit();
            return;
        }
        // 如果为替换和合并，那么重新生成该文件
        // 字段枚举 文件，不涉及合并，只有替换
        outputFile(velocityContext, finalEntity.getFileEntity().getEnums(), configEntity.getVelocityConfig().getEnums());
    }

    /**
     * 输出 基础Bean 文件
     */
    private void outputBaseEntityFile(ConfigEntity configEntity,
                                      FinalEntity finalEntity,
                                      VelocityContext velocityContext) {
        // 如果为忽略，那么不做任何处理
        if (BuildTypeEnum.ignore.getName().equals(configEntity.getBuildTypeConfig().getBaseEntity())) {
            return;
        }
        // 如果为删除，那么移除该文件
        if (BuildTypeEnum.delete.getName().equals(configEntity.getBuildTypeConfig().getBaseEntity())) {
            String baseEntityFilePath = finalEntity.getFileEntity().getBaseEntity();
            new File(baseEntityFilePath).deleteOnExit();
            return;
        }
        // 如果为替换和合并，那么重新生成该文件
        // 基础Bean 文件，不涉及合并，只有替换
        // 生成 视图 基础Bean 文件
        if (finalEntity.getStatusEntity().isWhetherView()) {
            outputFile(velocityContext, finalEntity.getFileEntity().getBaseEntity(), configEntity.getVelocityConfig().getQueryBaseEntity());
        } else if (finalEntity.getStatusEntity().isHasPkCol()) {
            outputFile(velocityContext, finalEntity.getFileEntity().getBaseEntity(), configEntity.getVelocityConfig().getPrimaryBaseEntity());
        } else {
            outputFile(velocityContext, finalEntity.getFileEntity().getBaseEntity(), configEntity.getVelocityConfig().getCommonBaseEntity());
        }
    }

    /**
     * 输出 Bean 文件
     */
    private void outputEntityFile(ConfigEntity configEntity,
                                  FinalEntity finalEntity,
                                  VelocityContext velocityContext) {
        // 如果为忽略，那么不做任何处理
        if (BuildTypeEnum.ignore.getName().equals(configEntity.getBuildTypeConfig().getEntity())) {
            return;
        }
        // 如果为删除，那么移除该文件
        if (BuildTypeEnum.delete.getName().equals(configEntity.getBuildTypeConfig().getEntity())) {
            String entityFilePath = finalEntity.getFileEntity().getEntity();
            new File(entityFilePath).deleteOnExit();
            return;
        }

        // 如果为替换和合并，那么重新生成该文件
        // 合并时，会将截取到的原文件的扩展内容，添加到新生成的文件中
        outputFile(velocityContext, finalEntity.getFileEntity().getEntity(), configEntity.getVelocityConfig().getEntity());
    }

    /**
     * 输出 dao代理 文件
     */
    private void outputDaoFile(ConfigEntity configEntity,
                               FinalEntity finalEntity,
                               VelocityContext velocityContext) {
        // 如果为忽略，那么不做任何处理
        if (BuildTypeEnum.ignore.getName().equals(configEntity.getBuildTypeConfig().getDao())) {
            return;
        }
        // 如果为删除，那么移除该文件
        if (BuildTypeEnum.delete.getName().equals(configEntity.getBuildTypeConfig().getDao())) {
            String daoFilePath = finalEntity.getFileEntity().getDao();
            new File(daoFilePath).deleteOnExit();
            return;
        }

        // 如果为替换和合并，那么重新生成该文件
        // 合并时，会将截取到的原文件的扩展内容，添加到新生成的文件中
        if (finalEntity.getStatusEntity().isWhetherView()) {
            outputFile(velocityContext, finalEntity.getFileEntity().getDao(), configEntity.getVelocityConfig().getQueryDao());
        } else if (finalEntity.getStatusEntity().isHasPkCol()) {
            outputFile(velocityContext, finalEntity.getFileEntity().getDao(), configEntity.getVelocityConfig().getPrimaryDao());
        } else {
            outputFile(velocityContext, finalEntity.getFileEntity().getDao(), configEntity.getVelocityConfig().getCommonDao());
        }
    }

    /**
     * 输出 service服务接口 文件
     */
    private void outputServiceFile(ConfigEntity configEntity,
                                   FinalEntity finalEntity,
                                   VelocityContext velocityContext) {
        // 如果为忽略，那么不做任何处理
        if (BuildTypeEnum.ignore.getName().equals(configEntity.getBuildTypeConfig().getService())) {
            return;
        }
        // 如果为删除，那么移除该文件
        if (BuildTypeEnum.delete.getName().equals(configEntity.getBuildTypeConfig().getService())) {
            String serviceFilePath = finalEntity.getFileEntity().getService();
            new File(serviceFilePath).deleteOnExit();
            return;
        }

        // 如果为替换和合并，那么重新生成该文件
        // 合并时，会将截取到的原文件的扩展内容，添加到新生成的文件中
        if (finalEntity.getStatusEntity().isWhetherView()) {
            outputFile(velocityContext, finalEntity.getFileEntity().getService(), configEntity.getVelocityConfig().getQueryService());
        } else if (finalEntity.getStatusEntity().isHasPkCol()) {
            outputFile(velocityContext, finalEntity.getFileEntity().getService(), configEntity.getVelocityConfig().getPrimaryService());
        } else {
            outputFile(velocityContext, finalEntity.getFileEntity().getService(), configEntity.getVelocityConfig().getCommonService());
        }
    }

    /**
     * 输出 serviceImpl服务实现 文件
     */
    private void outputServiceImplFile(ConfigEntity configEntity,
                                       FinalEntity finalEntity,
                                       VelocityContext velocityContext) {
        // 如果为忽略，那么不做任何处理
        if (BuildTypeEnum.ignore.getName().equals(configEntity.getBuildTypeConfig().getServiceImpl())) {
            return;
        }
        // 如果为删除，那么移除该文件
        if (BuildTypeEnum.delete.getName().equals(configEntity.getBuildTypeConfig().getServiceImpl())) {
            String serviceImplFilePath = finalEntity.getFileEntity().getServiceImpl();
            new File(serviceImplFilePath).deleteOnExit();
            return;
        }

        // 如果为替换和合并，那么重新生成该文件
        // 合并时，会将截取到的原文件的扩展内容，添加到新生成的文件中
        if (finalEntity.getStatusEntity().isWhetherView()) {
            outputFile(velocityContext, finalEntity.getFileEntity().getServiceImpl(), configEntity.getVelocityConfig().getQueryServiceImpl());
        } else if (finalEntity.getStatusEntity().isHasPkCol()) {
            outputFile(velocityContext, finalEntity.getFileEntity().getServiceImpl(), configEntity.getVelocityConfig().getPrimaryServiceImpl());
        } else {
            outputFile(velocityContext, finalEntity.getFileEntity().getServiceImpl(), configEntity.getVelocityConfig().getCommonServiceImpl());
        }
    }

    /**
     * 输出 dao基础映射 文件
     */
    private void outputBaseMappingFile(ConfigEntity configEntity,
                                       FinalEntity finalEntity,
                                       VelocityContext velocityContext) {

    }

    /**
     * 输出 dao映射 文件
     */
    private void outputMappingFile(ConfigEntity configEntity,
                                   FinalEntity finalEntity,
                                   VelocityContext velocityContext) {

    }

    private void outputFile(VelocityContext velocityContext, String filePath, String vmPath) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);
            InputStream resourceAsStream = getClass().getResourceAsStream(vmPath);
            InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
            engine.evaluate(velocityContext, fileWriter, "velocity", inputStreamReader);
        } catch (IOException e) {
            throw new RuntimeException("使用Velocity引擎构造内容时发生异常:", e);
        } finally {
            StreamUtil.close(fileWriter);
        }
    }
}

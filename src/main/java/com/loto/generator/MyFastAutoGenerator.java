package com.loto.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * Author：蓝田_Loto<p>
 * Date：2022-06-13 19:24<p>
 * PageName：FastAutoGenerator.java<p>
 * Function：
 */

public class MyFastAutoGenerator {
    public static void main(String[] args) {
        FastAutoGenerator
                // 数据源
                .create("jdbc:mysql://xxxx/MyProject_mall", "root", "xxxx")

                // 全局配置
                .globalConfig(builder -> {
                    builder.fileOverride()      // 覆盖已生成文件
                            .disableOpenDir()   // 禁用代码生成后打开输出目录
                            .outputDir(System.getProperty("user.dir") + "/src/main/java")  // 指定输出目录
                            .author("蓝田_Loto")   // 设置作者
                            //.enableKotlin()     // 开启 kotlin 模式
                            .enableSwagger()      // 开启 swagger 模式
                            .dateType(DateType.ONLY_DATE)                 // 时间策略（ONLY_DATE，SQL_PACK，TIME_PACK）
                            .commentDate("yyyy-MM-dd HH:mm:ss");   // 注释日期
                })

                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.loto.mybatisplus")   // 设置父包名
                            //.moduleName("")    // 设置父包模块名
                    ;
                })

                // 策略配置
                .strategyConfig(builder -> {
                    builder
                            //.addInclude("t_simple") // 设置需要生成的表名
                            //.addTablePrefix("t_", "c_") // 设置过滤表前缀
                            // 实体类策略配置
                            .entityBuilder()
                            .naming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略（默认下划线转驼峰命名）
                            .columnNaming(NamingStrategy.underline_to_camel)           // 数据库表字段映射到实体的命名策略
                            .enableLombok()  // 开启 lombok 模型
                            .addSuperEntityColumns("id")  // 添加父类公共字段
                            // controller 策略配置
                            .controllerBuilder()
                            .enableRestStyle()   // 开启生成 @RestController 控制器
                            // Mapper 策略配置
                            .mapperBuilder()
                            .enableMapperAnnotation()  // 开启 @Mapper 注解
                            .formatMapperFileName("%sMapper")  // t_user -> UserMapper
                            .formatXmlFileName("%sMapper")     // t_user -> UserMapper.xml
                    ;
                })
                .templateEngine(new FreemarkerTemplateEngine())  // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}

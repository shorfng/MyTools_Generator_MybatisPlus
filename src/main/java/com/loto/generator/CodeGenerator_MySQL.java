package com.loto.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

/**
 * PageName：CodeGenerator_MySQL.java<p>
 * Date：2025-05-30 09:27<p>
 * Function：MySQL
 *
 * @author 蓝田_Loto
 */

public class CodeGenerator_MySQL {
    public static void main(String[] args) {
        FastAutoGenerator
                // 数据源
                .create(
                        "jdbc:mysql://ip地址:端口号/数据库名?characterEncoding=utf8&useSSL=false&zeroDateTimeBehavior=convertToNull",
                        "用户名",
                        "密码"
                )

                // 全局配置
                .globalConfig(builder -> {
                    builder.author("蓝田_Loto") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .enableSpringdoc() // 开启 springdoc 模式
                            .outputDir(System.getProperty("user.dir") + "/src/main/java")  // 指定输出目录
                            .dateType(DateType.ONLY_DATE)               // 时间策略（ONLY_DATE，SQL_PACK，TIME_PACK）
                            .commentDate("yyyy-MM-dd HH:mm:ss")  // 注释日期
                            .disableOpenDir();   // 禁用代码生成后打开输出目录
                })

                // 数据库表字段类型转换
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT) {
                                // 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )

                // 包配置（不需要更改）
                .packageConfig(builder -> builder
                        .parent("com.loto.generator") // 设置父包名
                        .moduleName("demo")           // 设置父包模块名
                        // 设置mapperXml生成路径
                        .pathInfo(Collections.singletonMap(OutputFile.xml, "src/main/resources/mapper"))
                )

                // 策略配置
                .strategyConfig(builder ->
                        builder.addInclude("数据库表名1","数据库表名2") // 设置需要生成的表名
                                //.addTablePrefix("t_", "c_") // 设置过滤表前缀
                                //.likeTable()            // 包含表名
                                //.notLikeTable()         // 不包含表名
                                //.addExclude("xxx")      // 设置不需要生成的表名
                                //.addTableSuffix("xxx")  // 设置过滤表后缀
                                //.enableCapitalMode()    // 开启大写命名
                                //.enableSkipView()       // 开启跳过视图
                                //.disableSqlFilter()     // 禁用sql过滤
                                .entityBuilder().enableLombok() // 开启 lombok 模型
                )

                // 引擎模板
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}

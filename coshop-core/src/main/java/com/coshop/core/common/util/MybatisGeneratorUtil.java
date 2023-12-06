package com.coshop.core.common.util;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;


/**
 * @author dixiey
 * @version 1.0
 * @description: TODO
 * @date 2023/11/26 20:05
 */
public class MybatisGeneratorUtil {

    public static void main(String[] args) {

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/coshop-core/src/main/java");
        //作者
        gc.setAuthor("dixiey");
        //打开输出目录
        gc.setOpen(false);
        //xml开启 BaseResultMap
        gc.setBaseResultMap(true);
        //xml 开启BaseColumnList
        gc.setBaseColumnList(true);
        // 是否启用实体类属性的 Swagger2 注解
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);
        // 配置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/coshop?characterEncoding=utf-8&userSSL=false");
                dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.coshop.core.user") // 【生成到那个包下面，例如com.****.server】
                .setEntity("pojo")		//实体包
                .setMapper("mapper")	//mapper包
                .setService("service")	//Service包
                .setServiceImpl("service.impl") //ServiceImpl包
                .setController("controller");	//Controller包
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // 这里不需要更改
            }
        };

        // 使用 freemarker 模板引擎
        String templatePath = "/templates/mapper.xml.ftl";

        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/coshop-core/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper"
                        + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 配置相关策略
        StrategyConfig strategy = new StrategyConfig();
        //数据库 表 映射到实体的命名策略【数据库表有下划线，所以转为驼峰命名】
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //数据库 表字段 映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //lombok模型
        strategy.setEntityLombokModel(true);
        //生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        //生成普通的Controller
        // strategy.setControllerMappingHyphenStyle(true);
        strategy.setInclude("t_user".split(","));
        strategy.setControllerMappingHyphenStyle(true);
        //表前缀
        strategy.setTablePrefix("t_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();


    }


}

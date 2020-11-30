package org.stlife.code.gen;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.db.Entity;
import org.stlife.code.gen.common.PageResult;
import org.stlife.code.gen.entity.GenConfig;
import org.stlife.code.gen.entity.TableRequest;
import org.stlife.code.gen.service.CodeGenService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 代码生成service测试
 * </p>
 *
 * @package: com.xkcoding.codegen
 * @description: 代码生成service测试
 * @author: yangkai.shen
 * @date: Created in 2019-03-22 10:34
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CodeGenServiceTest {
    @Autowired
    private CodeGenService codeGenService;

    private TableRequest buildTableConfig(){
        TableRequest config = new TableRequest();
        config.setCurrentPage(0);
        config.setPageSize(10);
        config.setPrepend("jdbc:mysql://");
        config.setUrl("master:3306/stlife_rbac");
        config.setUsername("root");
        config.setPassword("cloud");
        return config;
    }
    @Test
    public void testTablePage() {
        TableRequest config = buildTableConfig();
        PageResult<Entity> pageResult = codeGenService.listTables(config);
        pageResult.getList().forEach(l -> log.info("Rs Entity:{}",l.toString()));
        log.info("【pageResult】= {}", pageResult);
    }

    @Test
    @SneakyThrows
    public void testGeneratorCode() {
        GenConfig config = new GenConfig();

        TableRequest request = new TableRequest();
        request.setPrepend("jdbc:mysql://");
        request.setUrl("127.0.0.1:3306/stlife_test");
        request.setUsername("root");
        request.setPassword("root");
        request.setTableName("shiro_user");
        config.setRequest(request);

        config.setModuleName("shiro");
        config.setAuthor("stlife");
        config.setComments("用户信息");
        config.setPackageName("org.stlife");
        config.setTablePrefix("shiro_");

        byte[] zip = codeGenService.generatorCode(config);
        OutputStream outputStream = new FileOutputStream(new File("./sources/" + request.getTableName() + ".zip"));
        IoUtil.write(outputStream, true, zip);
    }

    @Test
    @SneakyThrows
    public void testGeneratorCodeTables() {
        GenConfig config = new GenConfig();

        TableRequest table = buildTableConfig();
        List<TableRequest> tableList = new ArrayList<>();
        table.setTableName("sec_user");
        tableList.add(table);

        TableRequest table1 = new TableRequest();
        BeanUtil.copyProperties(table,table1);
        table1.setTableName("sec_role");
        tableList.add(table1);

        config.setTables(tableList);

        config.setModuleName("rbac");
        config.setAuthor("stlife");
        config.setComments("权限管理");
        config.setPackageName("org.stlife.rbac");
//        config.setTablePrefix("shiro_");

        byte[] zip = codeGenService.generatorCode(config);
        OutputStream outputStream = new FileOutputStream(new File("./sources/src.zip"));
        IoUtil.write(outputStream, true, zip);
    }

    /**
     * 描述：根据db生成所有的表
     * @author Stlife
     * @since 2020-11-30 22:20
     */
    @Test
    @SneakyThrows
    public void testGeneratorCodeByDb() {
        TableRequest table = buildTableConfig();
        PageResult<Entity> dbTables = codeGenService.listTables(table);

        List<TableRequest> tableList = new ArrayList<>();
        dbTables.getList().forEach(entity -> {
            TableRequest tableRequest = new TableRequest();
            BeanUtil.copyProperties(table,tableRequest);
            tableRequest.setTableName(entity.values().toArray()[0].toString());
            tableList.add(tableRequest);
        });

        GenConfig config = new GenConfig();
        config.setTables(tableList);
        config.setModuleName("rbac");
        config.setAuthor("stlife");
        config.setComments("信息管理");
        config.setPackageName("org.stlife.rbac");
        config.setTablePrefix("sec_");

        tableList.forEach(System.out::println);

        byte[] zip = codeGenService.generatorCode(config);
        OutputStream outputStream = new FileOutputStream(new File("./sources/src.zip"));
        IoUtil.write(outputStream, true, zip);
    }
}

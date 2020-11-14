package org.stlife.cmp.mybatis.plus.config;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>
 * 描述：TODO
 * </p>
 *
 * @author Stlife
 * @version 1.0
 * @since 2020-11-13 20:57
 */
@Configuration
@MapperScan(basePackages = {"org.stlife.cmp.mybatis.plus.mapper"})
@EnableTransactionManagement
public class MybatisPlusConfig {
    /**
     * 性能分析拦截器，不建议生产使用
     * 建议调试环境开启@Profile
     * 3.1.2版本后不推荐使用
     */
//    @Bean
//    public PerformanceInterceptor performanceInterceptor(){
//        PerformanceInterceptor perform = new PerformanceInterceptor();
//        perform.setFormat(true);
//        // 最大执行时间ms
//        perform.setMaxTime(100L);
//        return perform;
//    }

    /**
     * 分页插件
     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        return new PaginationInterceptor();
//    }

    /**
     * 乐观锁，@Version
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

    /**
     * 新多租户插件配置,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存万一出现问题
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {

            @Override
            public String getTenantIdColumn(){
                return "tenant";
            }

            @Override
            public Expression getTenantId() {
                // 一般从用户会话中获取
                return new StringValue("T01");
            }

            // 这是 default 方法,默认返回 false 表示所有表都需要拼多租户条件
            @Override
            public boolean ignoreTable(String tableName) {
                if("idm_role".equals(tableName)){
                    return true;
                }
                return false;
            }
        }));
        // 如果用了分页插件注意先 add TenantLineInnerInterceptor 再 add PaginationInnerInterceptor
        // 用了分页插件必须设置 MybatisConfiguration#useDeprecatedExecutor = false
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();

        // 针对特定类方法租户过滤
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }

}

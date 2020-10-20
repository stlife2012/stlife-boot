package org.stlife.buz.seata.configuration;

import io.seata.spring.annotation.GlobalTransactionScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SeataConfiguration
 * 描述：TODO
 *
 * @author Stlife
 * @version 1.0
 * @since 2020-10-05 15:05
 **/
@Configuration
public class SeataConfiguration {
    @Value("${spring.application.name}")
    private String applicationId;

    /**
     * 注册一个StatViewServlet
     *
     * @return global transaction scanner
     */
    @Bean
    public GlobalTransactionScanner globalTransactionScanner() {
        GlobalTransactionScanner globalTransactionScanner = new GlobalTransactionScanner(applicationId,
                "stlife_seata_tx_group");
        return globalTransactionScanner;
    }
}

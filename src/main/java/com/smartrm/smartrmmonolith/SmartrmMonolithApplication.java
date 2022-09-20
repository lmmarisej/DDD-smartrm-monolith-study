package com.smartrm.smartrmmonolith;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.smartrm.smartrmmonolith")
@MapperScan({"com.smartrm.smartrmmonolith.device.infrastructure.mapper",
        "com.smartrm.smartrmmonolith.user.infrastructure.mapper",
        "com.smartrm.smartrmmonolith.payment.infrastructure.mapper",
        "com.smartrm.smartrmmonolith.trade.infrastructure.mapper",
        "com.smartrm.smartrmmonolith.infracore.idgenerator.impl.mapper",
        "com.smartrm.smartrmmonolith.operation.infrastructure.mapper"})
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class SmartrmMonolithApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SmartrmMonolithApplication.class, args);
    }
    
}

package com.bytepipe.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditConfiguration {

    @Bean
    public SpringSecurityAuditorAware springSecurityAuditorAware(){
        return new SpringSecurityAuditorAware();
    }
}

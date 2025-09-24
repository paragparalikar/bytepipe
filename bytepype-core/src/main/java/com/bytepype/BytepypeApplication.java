package com.bytepype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableCaching
@EnableJpaRepositories
@SpringBootApplication
@EnableConfigurationProperties
public class BytepypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BytepypeApplication.class);
    }

    @Bean
    @ConfigurationProperties("bytepype.brand")
    public Brand brand(){
        return new Brand();
    }

}

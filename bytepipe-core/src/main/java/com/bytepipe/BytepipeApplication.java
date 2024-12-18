package com.bytepipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@EnableJpaRepositories
@SpringBootApplication
@EnableConfigurationProperties
public class BytepipeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BytepipeApplication.class);
    }

    @Bean
    @ConfigurationProperties("bytepipe.brand")
    public Brand brand(){
        return new Brand();
    }

}

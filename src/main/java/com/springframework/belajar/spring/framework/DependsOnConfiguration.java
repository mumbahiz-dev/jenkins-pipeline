package com.springframework.belajar.spring.framework;

import com.springframework.belajar.spring.framework.data.Bar;
import com.springframework.belajar.spring.framework.data.Foo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;

@Slf4j
@Configuration
public class DependsOnConfiguration {

    @Lazy
    @Bean
    @DependsOn(value = {
            "bar"
    })
    public Foo foo(){
        log.info("Create new foo");
        return new Foo();
    }

    @Bean
    public Bar bar(){
        log.info("Create new bar");
        return new Bar();
    }
}


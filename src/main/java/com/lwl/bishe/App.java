package com.lwl.bishe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@MapperScan("com.lwl.bishe.dao.mapper")
@ComponentScan(basePackages="com.lwl")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

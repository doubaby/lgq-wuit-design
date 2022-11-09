package com.lgq.House;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = "com.lgq.House.mapper")
@ComponentScan("com.lgq")
public class HouseApp {
    public static void main(String[] args) {
        SpringApplication.run(HouseApp.class, args);
    }
}

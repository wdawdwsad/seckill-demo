package com.lxy.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lxy.seckill.mapper")
public class SeckillDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillDemoApplication.class, args);
    }

}

package com.earl.chaos.chaos_admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.earl.chaos.chaos_admin.mapper")
public class ChaosAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChaosAdminApplication.class, args);
    }

}

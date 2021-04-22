package com.school.master;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * redis心跳检测
 */

@EnableScheduling
@SpringBootApplication
public class MasterAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(MasterAdminApplication.class, args);
    }
}

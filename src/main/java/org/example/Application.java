package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用启动类
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
        System.out.println("===================================");
        System.out.println("应用启动成功!");
        System.out.println("注册接口: POST http://localhost:8080/api/user/register");
        System.out.println("登录接口: POST http://localhost:8080/api/user/login");
        System.out.println("===================================");
    }
}

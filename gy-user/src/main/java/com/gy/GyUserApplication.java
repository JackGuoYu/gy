package com.gy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gy.mapper")
public class GyUserApplication {
  public static void main(String[] args) {
      SpringApplication.run(GyUserApplication.class, args);
  }
}

package com.gy;

import com.gy.client.user.UserClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.gy.mapper")
@EnableDiscoveryClient
@EnableFeignClients(clients = {UserClient.class})
@EnableCircuitBreaker
public class GyPayApplication {
  public static void main(String[] args) {
      SpringApplication.run(GyPayApplication.class, args);
  }
}

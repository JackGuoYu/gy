package com.gy;

import com.gy.client.pay.PayClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.gy.mapper")
@EnableDiscoveryClient
@EnableFeignClients(clients = {PayClient.class})
@EnableCircuitBreaker
public class GyUserApplication {
  public static void main(String[] args) {
      SpringApplication.run(GyUserApplication.class, args);
  }
}

package com.example.nativedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public class NativeDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(NativeDemoApplication.class, args);
  }



}

package com.aliya.hashmap.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.info.BuildProperties;

import java.util.HashMap;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    private BuildProperties buildProperties;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        HashMap hashMap = new HashMap();

    }

}

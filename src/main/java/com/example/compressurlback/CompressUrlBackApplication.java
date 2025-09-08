package com.example.compressurlback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.compressurlback.model") // Scan entity package
@EnableJpaRepositories("com.example.compressurlback.UrlDao") // Scan repository package
public class CompressUrlBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompressUrlBackApplication.class, args);
        System.out.println("Hello World For Compression");
    }
}

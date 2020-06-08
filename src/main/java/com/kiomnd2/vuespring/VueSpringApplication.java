package com.kiomnd2.vuespring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class VueSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(VueSpringApplication.class, args);
    }

}

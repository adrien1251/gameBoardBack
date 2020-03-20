package com.adrien1251.gameboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class BoardGameAppli {

    public static void main(String... args){
        SpringApplication.run(BoardGameAppli.class, args);
    }

}

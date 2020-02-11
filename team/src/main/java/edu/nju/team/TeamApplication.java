package edu.nju.team;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"edu.nju.config", "edu.nju.service", "edu.nju.dao", "edu.nju.controller"})
@SpringBootApplication
@EnableEurekaClient
public class TeamApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamApplication.class, args);
    }

}

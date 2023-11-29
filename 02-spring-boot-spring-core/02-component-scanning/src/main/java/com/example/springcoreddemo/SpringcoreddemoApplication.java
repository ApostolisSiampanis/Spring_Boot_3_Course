package com.example.springcoreddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*@SpringBootApplication(
        scanBasePackages = {"com.example.springcoreddemo",
                            "com.example.util"})
*/
@SpringBootApplication
public class SpringcoreddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcoreddemoApplication.class, args);
    }

}

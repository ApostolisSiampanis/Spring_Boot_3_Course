package com.example.springcoreddemo.config;

import com.example.springcoreddemo.common.Coach;
import com.example.springcoreddemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean("aquatic")
    public Coach swimCoach() {
        return new SwimCoach();
    }
}

package com.christiandias.cursomc.configu;

import com.christiandias.cursomc.services.DBService;
import com.christiandias.cursomc.services.EmailService;
import com.christiandias.cursomc.services.MockEmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws Exception {

        dbService.instantiateTestDatabase();

        return true;
    }

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }

}
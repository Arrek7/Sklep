package com.comarch.szkolenia.sklep.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = {
        "com.comarch.szkolenia.sklep.authenticator",
        "com.comarch.szkolenia.sklep.core",
        "com.comarch.szkolenia.sklep.database",
        "com.comarch.szkolenia.sklep.gui"
})
public class AppConfiguration {

    @Bean
    public Scanner scanner() {return new Scanner(System.in);}

}

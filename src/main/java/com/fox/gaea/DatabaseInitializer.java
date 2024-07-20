package com.fox.gaea;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner
{
    @Override
    public void run(String... args) throws Exception{
        System.out.println("DatabaseInitializer.run()");
    }
}

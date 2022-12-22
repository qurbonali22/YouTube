package com.example.YouTube.service;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Slf4j
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        log.info("CommandLineAppStartupRunner ishini boshladi");
        Flyway.configure().baselineOnMigrate(true).dataSource(dataSource).load().migrate();
    }


}

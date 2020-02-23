package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App implements CommandLineRunner {
    NamedParameterJdbcTemplate jdbcTemplate;

    App(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... strings) throws Exception {
        String sql = "SELECT :a + :b";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("a", 100)
                .addValue("b", 200);
        Integer result = jdbcTemplate.queryForObject(sql, param, Integer.class);
        System.out.println("result = " + result);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

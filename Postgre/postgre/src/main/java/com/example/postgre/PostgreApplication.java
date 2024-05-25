package com.example.postgre;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@Log
public class PostgreApplication implements CommandLineRunner {

	private final DataSource dataSource;
	public PostgreApplication(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	public static void main(String[] args) {
		SpringApplication.run(PostgreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Database Source: " + dataSource);
		final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("select 1");
	}
}

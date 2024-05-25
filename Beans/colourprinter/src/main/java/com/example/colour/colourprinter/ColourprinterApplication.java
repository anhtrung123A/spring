package com.example.colour.colourprinter;

import com.example.colour.colourprinter.services.ColourPrinter;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class ColourprinterApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ColourprinterApplication.class, args);
	}

	private ColourPrinter colourPrinter;
	public ColourprinterApplication(ColourPrinter colourPrinter)
	{
		this.colourPrinter = colourPrinter;
	}
	@Override
	public void run(String... args) throws Exception {
	log.info(colourPrinter.print());
	}
}

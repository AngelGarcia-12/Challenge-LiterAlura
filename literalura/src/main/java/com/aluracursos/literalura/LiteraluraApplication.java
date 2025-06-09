package com.aluracursos.literalura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aluracursos.literalura.view.MenuApp;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	private final MenuApp MENUAPP;

	public LiteraluraApplication(MenuApp menuApp) {
		this.MENUAPP = menuApp;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MENUAPP.menuMain();
	}
}

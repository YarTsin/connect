package com.example.connect;

import com.example.connect.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Точка входа (запуска)
 * Проверяем подключение к 2 источникам данных
 *
 */
@SpringBootApplication
public class Main implements CommandLineRunner {

  @Autowired
  private TestService testService;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("-----------------");
        System.out.println("Проект запущен");
        System.out.println("-----------------");

        testService.test();
    }
}

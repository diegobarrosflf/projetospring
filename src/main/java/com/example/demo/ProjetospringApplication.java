package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domain.Categoria;
import com.example.demo.repositories.CategoriaRepository;

@SpringBootApplication
public class ProjetospringApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetospringApplication.class, args);
					
	}
	
	@Override
	public void run(String... args)throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escrtório");
		
		this.categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		
		
	}

}

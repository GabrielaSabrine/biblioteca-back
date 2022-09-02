package com.bilioteca.biblioteca;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bilioteca.biblioteca.domain.Chamado;
import com.bilioteca.biblioteca.domain.Leitor;
import com.bilioteca.biblioteca.domain.Livros;
import com.bilioteca.biblioteca.domain.enums.Perfil;
import com.bilioteca.biblioteca.domain.enums.Prioridade;
import com.bilioteca.biblioteca.domain.enums.Status;
import com.bilioteca.biblioteca.repositories.ChamadoRepository;
import com.bilioteca.biblioteca.repositories.LeitorRepository;
import com.bilioteca.biblioteca.repositories.LivrosRepository;

@SpringBootApplication
public class BibliotecaApplication implements CommandLineRunner {

	@Autowired
	private LivrosRepository livrosRepository;
	@Autowired
	private LeitorRepository leitorRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
     Livros t1 = new Livros(null, "Biblioteca", "6378652677", "gabrielasabrine@gmail.com", "123");
     t1.addPerfil(Perfil.ADMIN);

     Leitor c1 = new Leitor(null, "Victor", "85674537622", "victor.icoma@soulcodeacademy.org", null);
     
     Chamado h1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado01", "Primeiro chamado", t1, c1);
	
	livrosRepository.saveAll(Arrays.asList(t1));
	leitorRepository.saveAll(Arrays.asList(c1));
	chamadoRepository.saveAll(Arrays.asList(h1));
	}
	

}

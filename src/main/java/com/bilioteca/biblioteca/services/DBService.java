package com.bilioteca.biblioteca.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bilioteca.biblioteca.domain.Chamado;
import com.bilioteca.biblioteca.domain.Leitor;
import com.bilioteca.biblioteca.domain.Livros;
import com.bilioteca.biblioteca.domain.enums.Perfil;
import com.bilioteca.biblioteca.domain.enums.Prioridade;
import com.bilioteca.biblioteca.domain.enums.Status;
import com.bilioteca.biblioteca.repositories.ChamadoRepository;
import com.bilioteca.biblioteca.repositories.PessoaRepository;

@Service
public class DBService {

	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public void instanciaDB() {

		 Leitor c1 = new Leitor(null, "Victor", "85674537622", "victor.icoma@soulcodeacademy.org", null);
		Livros t2 = new Livros(null, "Richard Stallman", "903.347.070-56", "stallman@mail.com", encoder.encode("123"));
		Livros t3 = new Livros(null, "Claude Elwood Shannon", "271.068.470-54", "shannon@mail.com", encoder.encode("123"));
		Livros t4 = new Livros(null, "Tim Berners-Lee", "162.720.120-39", "lee@mail.com", encoder.encode("123"));
		Livros t5 = new Livros(null, "Linus Torvalds", "778.556.170-27", "linus@mail.com", encoder.encode("123"));

		Leitor c1 = new Leitor(null, "Albert Einstein", "111.661.890-74", "einstein@mail.com", encoder.encode("123"));
		Leitor c2 = new Leitor(null, "Marie Curie", "322.429.140-06", "curie@mail.com", encoder.encode("123"));
		Leitor c3 = new Leitor(null, "Charles Darwin", "792.043.830-62", "darwin@mail.com", encoder.encode("123"));
		Leitor c4 = new Leitor(null, "Stephen Hawking", "177.409.680-30", "hawking@mail.com", encoder.encode("123"));
		Leitor c5 = new Leitor(null, "Max Planck", "081.399.300-83", "planck@mail.com", encoder.encode("123"));
 
		Chamado h1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 1", "Teste chamado 1", t2, c1);
		Chamado h2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 2", "Teste chamado 2", t2, c2);
		Chamado h3 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 3", "Teste chamado 3", t2, c3);
		Chamado h4 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 4", "Teste chamado 4", t3, c3);
		Chamado h5 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 5", "Teste chamado 5", t2, c1);
		Chamado h6 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 7", "Teste chamado 6", t2, c5);

		pessoaRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5, c1, c2, c3, c4, c5));
		chamadoRepository.saveAll(Arrays.asList(h1, h2, h3, h4, h5, h6 ));
	}
}

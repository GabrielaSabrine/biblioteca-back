package com.bilioteca.biblioteca.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.bilioteca.biblioteca.domain.dtos.LivrosDTO;
import com.bilioteca.biblioteca.domain.enums.Perfil;

	public class Livros extends Pessoa {
		private static final long serialVersionUID = 1L;

		@JsonIgnore
		@OneToMany(mappedBy = "livros")
		private List<Chamado> chamados = new ArrayList<>();

		public Livros() {
			super();
			addPerfil(Perfil.LEITOR);
		}

		public Livros(Integer id, String nome, String cpf, String email, String senha) {
			super(id, nome, cpf, email, senha);
			addPerfil(Perfil.LEITOR);
		}
		
		public Livros(LivrosDTO obj) {
			super();
			this.id = obj.getId();
			this.nome = obj.getNome();
			this.cpf = obj.getCpf();
			this.email = obj.getEmail();
			this.senha = obj.getSenha();
			this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
			this.dataCriacao = obj.getDataCriacao();
		}

		public List<Chamado> getChamados() {
			return chamados;
		}

		public void setChamados(List<Chamado> chamados) {
			this.chamados = chamados;
		}

	}


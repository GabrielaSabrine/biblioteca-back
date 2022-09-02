package com.bilioteca.biblioteca.domain;

	import java.util.ArrayList;
	import java.util.List;
	import java.util.stream.Collectors;

	import javax.persistence.Entity;
	import javax.persistence.OneToMany;

	import com.fasterxml.jackson.annotation.JsonIgnore;
	import com.bilioteca.biblioteca.domain.dtos.LeitorDTO;
	import com.bilioteca.biblioteca.domain.enums.Perfil;

	@Entity
	public class Leitor extends Pessoa {
		private static final long serialVersionUID = 1L;

		@JsonIgnore
		@OneToMany(mappedBy = "leitor")
		private List<Chamado> chamados = new ArrayList<>();

		public Leitor() {
			super();
			addPerfil(Perfil.LEITOR);
		}

		public Leitor(Integer id, String nome, String cpf, String email, String senha) {
			super(id, nome, cpf, email, senha);
			addPerfil(Perfil.LEITOR);
		}

		public Leitor(LeitorDTO obj) {
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



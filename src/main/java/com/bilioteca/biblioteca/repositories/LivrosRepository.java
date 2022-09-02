package com.bilioteca.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilioteca.biblioteca.domain.Livros;

public interface LivrosRepository extends JpaRepository<Livros, Integer>{

}

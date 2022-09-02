package com.bilioteca.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilioteca.biblioteca.domain.Leitor;

public interface LeitorRepository extends JpaRepository<Leitor, Integer>{

}

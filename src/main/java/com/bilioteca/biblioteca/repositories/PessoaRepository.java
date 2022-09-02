package com.bilioteca.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bilioteca.biblioteca.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}

package com.bilioteca.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilioteca.biblioteca.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}

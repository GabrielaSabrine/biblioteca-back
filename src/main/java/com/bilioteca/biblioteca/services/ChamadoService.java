package com.bilioteca.biblioteca.services;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bilioteca.biblioteca.domain.Chamado;
import com.bilioteca.biblioteca.domain.Leitor;
import com.bilioteca.biblioteca.domain.Livros;
import com.bilioteca.biblioteca.domain.dtos.ChamadoDTO;
import com.bilioteca.biblioteca.domain.enums.Prioridade;
import com.bilioteca.biblioteca.domain.enums.Status;
import com.bilioteca.biblioteca.repositories.ChamadoRepository;
import com.bilioteca.biblioteca.services.exceptions.ObjectnotFoundException;

import javassist.tools.rmi.ObjectNotFoundException;


@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;
	@Autowired
	private LivrosService livrosService;
	@Autowired
	private LeitorService leitorService;

	public Chamado findById(Integer id) {
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! ID: " + id));
	}

	public List<Chamado> findAll() {
		return repository.findAll();
	}

	public Chamado create(ChamadoDTO obj) {
		return repository.save(newChamado(obj));
	}

	public Chamado update(Integer id, @Valid ChamadoDTO objDTO) {
		objDTO.setId(id);
		Chamado oldObj = findById(id);
		oldObj = newChamado(objDTO);
		return repository.save(oldObj);
	}

	private Chamado newChamado(ChamadoDTO obj) {
		Livros livros = livrosService.findById(obj.getLivros());
		Leitor leitor= leitorService.findById(obj.getLeitor());
		
		Chamado chamado = new Chamado();
		if(obj.getId() != null) {
			chamado.setId(obj.getId());
		}
		
		if(obj.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}
		
		chamado.setLivros(livros);
		chamado.setLeitor(leitor);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacoes());
		return chamado;
	}

}

















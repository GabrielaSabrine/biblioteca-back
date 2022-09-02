package com.bilioteca.biblioteca.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bilioteca.biblioteca.domain.Chamado;
import com.bilioteca.biblioteca.domain.Leitor;
import com.bilioteca.biblioteca.domain.Livros;
import com.bilioteca.biblioteca.domain.Pessoa;
import com.bilioteca.biblioteca.domain.dtos.ChamadoDTO;
import com.bilioteca.biblioteca.domain.dtos.LeitorDTO;
import com.bilioteca.biblioteca.domain.dtos.LivrosDTO;
import com.bilioteca.biblioteca.domain.enums.Prioridade;
import com.bilioteca.biblioteca.domain.enums.Status;
import com.bilioteca.biblioteca.repositories.ChamadoRepository;
import com.bilioteca.biblioteca.repositories.LeitorRepository;
import com.bilioteca.biblioteca.repositories.LivrosRepository;
import com.bilioteca.biblioteca.repositories.PessoaRepository;
import com.bilioteca.biblioteca.services.exceptions.DataIntegrityViolationException;

import javassist.tools.rmi.ObjectNotFoundException;


@Service
public class LivrosService {

	@Autowired
	private LivrosRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Livros findById(Integer id) {
		Optional<Livros> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Livros> findAll() {
		return repository.findAll();
	}

	public Livros create(LivrosDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		validaPorCpfEEmail(objDTO);
		Livros newObj = new Livros(objDTO);
		return repository.save(newObj);
	}
 
	public Livros update(Integer id, @Valid LivrosDTO objDTO) {
		objDTO.setId(id);
		Livros oldObj = findById(id);
		
		if(!objDTO.getSenha().equals(oldObj.getSenha())) 
			objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		
		validaPorCpfEEmail(objDTO);
		oldObj = new Livros(objDTO);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Livros obj = findById(id);

		if (obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
		}

		repository.deleteById(id);
	}

	private void validaPorCpfEEmail(LivrosDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}

		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}

}

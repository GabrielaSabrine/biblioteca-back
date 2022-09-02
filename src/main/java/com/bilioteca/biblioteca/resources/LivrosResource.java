package com.bilioteca.biblioteca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bilioteca.biblioteca.domain.Livros;
import com.bilioteca.biblioteca.domain.dtos.LivrosDTO;
import com.bilioteca.biblioteca.services.LivrosService;

import antlr.collections.List;

@RestController
@RequestMapping(value = "/livross")
public class LivrosResource {

	@Autowired
	private LivrosService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<LivrosDTO> findById(@PathVariable Integer id) {
		Livros obj = service.findById(id);
		return ResponseEntity.ok().body(new LivrosDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<LivrosDTO>> findAll() {
		List<Livros> list = service.findAll();
		List<LivrosDTO> listDTO = list.stream().map(obj -> new LivrosDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PreAuthorize ("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<LivrosDTO> create(@Valid @RequestBody LivrosDTO objDTO) {
		Livros newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize ("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<LivrosDTO> update(@PathVariable Integer id, @Valid @RequestBody LivrosDTO objDTO) {
		Livros obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new LivrosDTO(obj));
	}
	
	@PreAuthorize ("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<LivrosDTO> delete(@PathVariable Integer id) {
		service.delete(id); 
		return ResponseEntity.noContent().build();
	}

}
 



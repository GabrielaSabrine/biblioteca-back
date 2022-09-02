package com.bilioteca.biblioteca.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bilioteca.biblioteca.domain.Leitor;
import com.bilioteca.biblioteca.domain.dtos.LeitorDTO;
import com.bilioteca.biblioteca.services.LeitorService;

@RestController
@RequestMapping(value = "/leitors")
public class LeitorResource {

	@Autowired
	private LeitorService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<LeitorDTO> findById(@PathVariable Integer id) {
		Leitor obj = service.findById(id);
		return ResponseEntity.ok().body(new LeitorDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<LeitorDTO>> findAll() {
		List<Leitor> list = service.findAll();
		List<LeitorDTO> listDTO = list.stream().map(obj -> new LeitorDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<LeitorDTO> create(@Valid @RequestBody LeitorDTO objDTO) {
		Leitor newObj  = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<LeitorDTO> update(@PathVariable Integer id, @Valid @RequestBody LeitorDTO objDTO) {
		Leitor obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new LeitorDTO(obj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<LeitorDTO> delete(@PathVariable Integer id) {
		service.delete(id); 
		return ResponseEntity.noContent().build();
	}

}

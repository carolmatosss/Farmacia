package com.farmacia.farmacia.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.farmacia.farmacia.model.CategoriasModel;
import com.farmacia.farmacia.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin (origins = "*" , allowedHeaders= "*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	//Metodo Get
	@GetMapping
	public ResponseEntity <List<CategoriasModel>> getAll(){
		return ResponseEntity.ok(categoriaRepository.findAll());

	}

	//Metodo Post 
	@PostMapping
	public ResponseEntity <CategoriasModel> post(@Valid @RequestBody CategoriasModel categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));

	}

	@PutMapping("/{id}")
	public ResponseEntity <CategoriasModel> put(@PathVariable CategoriasModel categoria){
		return categoriaRepository.findById(categoria.getId()).map(resposta -> ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus
	public void delete (@PathVariable Long id) {
		Optional <CategoriasModel> categoria = categoriaRepository.findById(id);

		if (categoria.isEmpty())
			throw new ResponseStatusException (HttpStatus.NOT_FOUND);

		categoriaRepository.deleteById(id);;

	}

	@GetMapping ("/tipo/{tipo}")
	public ResponseEntity <List<CategoriasModel>> getByTipo(@PathVariable String tipo){
		return ResponseEntity.ok(categoriaRepository.findAllByTipoContainingIgnoreCase(tipo));
	}
	
	@GetMapping ("/formato/{formato}")
	public ResponseEntity <List<CategoriasModel>> getByFormato(@PathVariable String formato){
		return ResponseEntity.ok(categoriaRepository.findAllByFormatoContainingIgnoreCase(formato));
	}
}

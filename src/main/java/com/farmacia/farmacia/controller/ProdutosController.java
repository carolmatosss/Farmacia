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

import com.farmacia.farmacia.model.ProdutosModel;
import com.farmacia.farmacia.repository.ProdutosRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin (origins = "*", allowedHeaders ="*")
public class ProdutosController {

	@Autowired
	private ProdutosRepository produtosRepository;

	//Método Get
	@GetMapping
	public ResponseEntity <List<ProdutosModel>>getAll(){
		return ResponseEntity.ok(produtosRepository.findAll());	
	}

	//Metodo Post
	@PostMapping
	public ResponseEntity<ProdutosModel> post (@Valid @RequestBody ProdutosModel produtos){
		return ResponseEntity.status(HttpStatus.CREATED).body(produtosRepository.save(produtos));

	}

	//Metodo Put
	@PutMapping
	public ResponseEntity <ProdutosModel> put (@Valid @RequestBody ProdutosModel produtos){
		return produtosRepository.findById(produtos.getId()).map(resposta -> ResponseEntity.status(HttpStatus.OK).body(produtosRepository.save(produtos)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}


	//Metodo Delete
	@ResponseStatus
	@DeleteMapping ("/{id}")
	public void delete (@PathVariable Long id) {
		Optional<ProdutosModel> produtos = produtosRepository.findById(id);

		if (produtos.isEmpty())
			throw new ResponseStatusException (HttpStatus.NOT_FOUND);

		produtosRepository.deleteById(id);
	}

	//Metodo Get por nome
	@GetMapping ("/{nome}")
	public ResponseEntity <List<ProdutosModel>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(produtosRepository.findAllByNomeContainingIgnoreCase(nome));

	}

	//Metodo Get por marca
	@GetMapping("/{marca}")
	public ResponseEntity <List<ProdutosModel>> getByMarca(@PathVariable String marca){

		return ResponseEntity.ok(produtosRepository.findAllByMarcaContainingIgnoreCase(marca));
	}


}
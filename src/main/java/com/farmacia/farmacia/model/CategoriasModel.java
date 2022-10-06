package com.farmacia.farmacia.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "Categorias")
public class CategoriasModel {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id; 

	@NotBlank
	@Size (min = 5 , max = 100)
	private String tipo; 

	@NotBlank 
	@Size (min = 3 , max = 200)
	private String formato;
	
	@OneToMany (mappedBy = "categoria", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties ("categoria")
	private List<ProdutosModel> produto;

	
	public List<ProdutosModel> getProduto() {
		return produto;
	}

	public void setProduto(List<ProdutosModel> produto) {
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}  

	


}

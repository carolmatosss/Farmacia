package com.farmacia.farmacia.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name= "Produtos")
public class ProdutosModel {

	//Tabela Produtos 

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY) 
	private Long id; 

	@NotBlank
	@Size (min = 5 , max = 255)
	private String nome; 

	@NotBlank
	@Size (min = 5 , max = 255)
	private String marca;

	@UpdateTimestamp
	private LocalDateTime data; 

	@NotBlank
	private String imagem;
	
	
	
	public CategoriasModel getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriasModel categoria) {
		this.categoria = categoria;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull(message = "Preço é obrigatório!")
    @Positive(message = "O preço deve ser maior do que zero!")
	private BigDecimal preco;

	@NotBlank
	private int quantidade;
	
	@ManyToOne
	@JsonIgnoreProperties ("produtos")
	private CategoriasModel categoria;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


}


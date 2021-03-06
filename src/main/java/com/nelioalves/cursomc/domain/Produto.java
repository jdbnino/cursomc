package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double preco;
	
	// Um produto tem várias categorias
//	@JsonBackReference  // Do outro lado da associação (categoria) buscou os objetos agora não será mais buscado, para não causar loop infinito
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="PRODUTO_CATEGORIA", 
			   joinColumns= @JoinColumn(name="produto_id"),
			   inverseJoinColumns = @JoinColumn(name="categoria_id")
	)	
	private List<Categoria> categorias = new ArrayList<>(); // Categoria não entra no construtor já foi iniciada com o new

	// Fazer o mesmo processo no pedido - pedido --> produto
	@JsonIgnore   // Ignora o itens do pedido no produto, o que importa e itens do pedido ver o produto que esta referenciado nele
	@OneToMany(mappedBy="id.produto")  // Item do pedido é mapeado pelo id do produto
	private Set<ItemPedido> itens = new HashSet<>();  // A classe Set garante que não terá itens repedidos no mesmo pedido
	
	public Produto(Integer id, String nome, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	
	// Associa os pedidos na lista
	@JsonIgnore // Tudo que começa com get é serializado. Necessário ignorar esta função para não gerar referencia ciclica. 
	public List<Pedido> getPedidos() {
		List<Pedido> lista = new ArrayList<>();
		for (ItemPedido x : itens) { 
			lista.add(x.getPedido()); // Associa os pedidos na lista
		}
		
		return lista;
	}
	
	
	public Produto () {		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	
	public Set<ItemPedido> getItens() {
		return itens;
	}
	
	
	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}

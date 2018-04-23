package com.nelioalves.cursomc.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonIgnore   // Não será serializado por ser chave composta
	@EmbeddedId // Id do tipo embutido em um auxiliar
	private ItemPedidoPK id = new ItemPedidoPK();
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	public ItemPedido() {
		
	}
	
	// Recebe também pedido e produto por ser a chave de ligação do item
	public ItemPedido( Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		super();
		id.setPedido(pedido);  // Passa o pedido para o id
		id.setProduto(produto);  // Passa o produto para o id
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	// Retorna a chave estrangeira
	@JsonIgnore   // Necessario ignorar para não gerar referencia ciclica
	public Pedido getPedido() {
		return id.getPedido();
	}
	
	// @JsonIgnore   // Necessario ignorar para não gerar referencia ciclica
	// O produto é necessário aparecer no json não pode ser ignore
	public Produto getProduto() {
		return id.getProduto();
	}

	//  ***** fim chave estrangeira
	
	public ItemPedidoPK getId() {
		return id;
	}
	
	public void setId(ItemPedidoPK id) {
		this.id = id;
	}
	
	public Double getDesconto() {
		return desconto;
	}
	
	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
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
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}


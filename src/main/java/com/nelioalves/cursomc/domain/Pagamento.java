package com.nelioalves.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.nelioalves.cursomc.domain.enums.EstadoPagamento;

@Entity
// @Inheritance( strategy=InheritanceType.SINGLETABLE)  // Cria uma tabela unica unindo as sub classes - gera muito campo null
@Inheritance( strategy=InheritanceType.JOINED)  // Cria uma tabela para cada sub classe
public abstract class Pagamento   implements Serializable {  // A classe fica abstrata para não permitir instanciar ( será instanciado somente as sub classes)
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;   // Id do pagamento tem que ser o mesmo do id do pedido correspondente
	
	// Transformou o enum em código, caso contrario retorna o numero dele no array
	private Integer estado;  // Ira pegar o id do enum e não sua posição no array
	
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId                             // Tera o mesmo id do pedido no pagamento
	private Pedido pedido;
	
	public Pagamento() {
		
	}
	
	public Pagamento( Integer id, EstadoPagamento estado, Pedido pedido ) {
		super();
		this.id = id;
		// Transformou o enum em código, caso contrario retorna o numero dele no array
		this.estado = estado.getCod();  // Irá pegar o indice do enum e não sua posição no array
		this.pedido = pedido;
	}
	
	public Integer getId( ) {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	// Transformou o enum em código, caso contrario retorna o numero dele no array
	public EstadoPagamento getEstado() {
//		return estado;
		return EstadoPagamento.toEnum(estado);
	}
	
	// Transformou o enum em código, caso contrario retorna o numero dele no array
	public void setEstado (EstadoPagamento estado) {
		this.estado = estado.getCod();
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	
	public void setPedido( Pedido pedido) {
		this.pedido = pedido;
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
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}

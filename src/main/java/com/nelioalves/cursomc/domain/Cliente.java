package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nelioalves.cursomc.domain.enums.TipoCliente;

@Entity
public class Cliente  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String email;
	private String cpfOuCnpj;
	
//	private TipoCliente tipo;
	private Integer tipo;  // Internamente fica com inteiro para externo fica a classe TipoCliente
	
//	@JsonManagedReference   // Evita a referencia ciclica recebe os endereços
	@OneToMany(mappedBy="cliente")	
	private List<Endereco> enderecos = new ArrayList<>();

	// Set - Coleção de strings que não aceita repetição de seus dados  --  entidade fraca
	//    optou por não criar uma classe para telefone por ser um capo somente (telefone)
	@ElementCollection // Cria uma tabela de entidade fraca
	@CollectionTable(name = "TELEFONE") // Cria a tabela de entidade fraca
	private Set<String> telefones = new HashSet<>();
	
//	@JsonBackReference   // Não retorna os pedidos do cliente para não gerar referencia ciclica o pedido já tem os dados do cliente
	@JsonIgnore
	@OneToMany(mappedBy="cliente")  // Um cliente possui muitos pedido
	private List<Pedido> pedidos = new ArrayList<>();
	
	public Cliente() {
		
	}


	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo.getCod();
	}


	public Integer getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}


	public String getEmail() {
		return email;
	}


	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}


	// Implementação manual
	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	// Implementação manual
	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}


	public Set<String> getTelefones() {
		return telefones;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}


	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}


	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	
	
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}

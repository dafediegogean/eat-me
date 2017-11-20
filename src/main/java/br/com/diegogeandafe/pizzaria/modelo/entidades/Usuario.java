package br.com.diegogeandafe.pizzaria.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class Usuario {
	
	@NotNull @NotEmpty
	@Column(unique=true)
	private String login;
	
	@NotNull @NotEmpty
	private String senha;
	
	//atributo nao gera coluna na tabela
	@Transient
	@NotNull @NotEmpty
	private String confirmacaoSenha;
	
	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}

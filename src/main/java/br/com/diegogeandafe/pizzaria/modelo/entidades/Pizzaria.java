package br.com.diegogeandafe.pizzaria.modelo.entidades;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Pizzaria implements UserDetails {

	private static final long serialVersionUID = 3362710239580495419L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	@NotNull
	private Usuario usuario;
	
	@NotNull
	private Calendar dataCadastro;
	
	@NotNull @NotEmpty 
	private String nome;
	
	@NotNull @NotEmpty
	private String endereco;
	
	@ElementCollection
	private Set<String> email;
	
	@ElementCollection
	private Set<String> telefone;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Permissao> permissoes;
	
	@OneToMany(mappedBy="dono")
	private Set<Pizza> pizzas;
	
	public Long getId() {
		return id;
	}
	
	public Set<Permissao> getPermissoes() {
		return permissoes;
	}
	
	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Set<String> getEmail() {
		return email;
	}

	public Set<String> getTelefone() {
		return telefone;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setEmail(Set<String> email) {
		this.email = email;
	}

	public void setTelefone(Set<String> telefone) {
		this.telefone = telefone;
	}
	
	public void setPermissoes(Set<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	
	public Set<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(Set<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	//carrega lista de permissao e verifica o tipo de permissao
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> autorizacoes = new ArrayList<GrantedAuthority>();
		
		//pega as a lista de autorizacoes que foi carregada e adiciona como nova
		for(Permissao permissao : getPermissoes()) {
			autorizacoes.add(new SimpleGrantedAuthority(permissao.getNome()));
		}
		//retorna lista de autorizacoes
		return autorizacoes;
	}

	@Override
	public String getPassword() {
		return usuario.getSenha();
	}

	@Override
	public String getUsername() {
		return usuario.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuario.getLogin() == null) ? 0 : usuario.getLogin().hashCode());
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
		Pizzaria other = (Pizzaria) obj;
		if (usuario.getLogin() == null) {
			if (other.getUsuario().getLogin() != null)
				return false;
		} else if (!usuario.getLogin().equals(other.getUsuario().getLogin()))
			return false;
		return true;
	}
	
}
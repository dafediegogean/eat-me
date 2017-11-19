package br.com.diegogeandafe.pizzaria.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.diegogeandafe.pizzaria.modelo.entidades.Usuario;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {

	public Usuario findOneByLogin(String login);
	
}

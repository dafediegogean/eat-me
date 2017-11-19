package br.com.diegogeandafe.pizzaria.modelo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.diegogeandafe.pizzaria.modelo.repositorios.UsuarioRepositorio;

@Service
public class ServicoAutenticacao implements UserDetailsService {
	
	@Autowired private UsuarioRepositorio usuarioRepositorio;
	
	//return login autenticado
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		return usuarioRepositorio.findOneByLogin(login);
	}

}

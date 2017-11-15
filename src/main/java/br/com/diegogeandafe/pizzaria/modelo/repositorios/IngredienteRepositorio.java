package br.com.diegogeandafe.pizzaria.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;

import br.com.diegogeandafe.pizzaria.modelo.entidades.Ingrediente;

public interface IngredienteRepositorio extends CrudRepository<Ingrediente, Long> {
	
	
	
}

package br.com.diegogeandafe.pizzaria.modelo.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.diegogeandafe.pizzaria.modelo.entidades.Ingrediente;
import br.com.diegogeandafe.pizzaria.modelo.entidades.Pizzaria;

public interface IngredienteRepositorio extends CrudRepository<Ingrediente, Long> {
	
	public List<Ingrediente> findAllByDono(Pizzaria dono);
	
	public Ingrediente findByIdAndDono(Long id, Pizzaria dono);
	
}

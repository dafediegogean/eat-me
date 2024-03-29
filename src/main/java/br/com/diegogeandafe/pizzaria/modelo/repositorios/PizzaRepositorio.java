package br.com.diegogeandafe.pizzaria.modelo.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.diegogeandafe.pizzaria.modelo.entidades.Pizza;
import br.com.diegogeandafe.pizzaria.modelo.entidades.Pizzaria;

@Repository
public interface PizzaRepositorio extends CrudRepository<Pizza, Long> {
	
	List<Pizza> findAllByDono(Pizzaria dono);
	
	Pizza findByIdAndDono(Long id, Pizzaria dono);

	List<Pizza> findAll();
		
}

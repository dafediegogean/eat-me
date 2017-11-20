package br.com.diegogeandafe.pizzaria.modelo.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diegogeandafe.pizzaria.modelo.entidades.Pizza;
import br.com.diegogeandafe.pizzaria.modelo.entidades.Pizzaria;
import br.com.diegogeandafe.pizzaria.modelo.repositorios.PizzaRepositorio;

@Service
public class ServicoPizza {
	
	@Autowired private ServicoPizzaria servicoPizzaria;
	@Autowired private PizzaRepositorio repositorio;
	
	//seta dono com o usuario logado
	//no caso getPizzariaLogada que e o usuario logado
	public void salvar(Pizza pizza) {
		pizza.setDono(servicoPizzaria.getPizzariaLogada());
		repositorio.save(pizza);
	}
	
	//lista ingredientes
	public Iterable<Pizza> listar(){
		Pizzaria dono = servicoPizzaria.getPizzariaLogada();
		return repositorio.findAllByDono(dono);
	}
	
	//busca id e objeto usuario (dono) logado 
	public Pizza buscar(long id) {
		Pizzaria dono = servicoPizzaria.getPizzariaLogada();
		return repositorio.findByIdAndDono(id, dono);
	}
	
	//se objeto retornado ingrediente for diferente de null e da pizzaria logado
	public void remover(long id) {
		Pizza pizza = this.buscar(id);
		if(pizza!=null) repositorio.delete(pizza);
		
	}
	//faz pesquisa e lista todas as pizzas cadastradas de cada uma pizzaria
	public List<String> listarNomesPizzasDisponiveis(){
		List<Pizza> pizzas = repositorio.findAll();
		
		List<String> nomesPizzas = pizzas.stream().map((pizza)->{
			return pizza.getNome();
		}).sorted().collect(Collectors.toList());
		
		return nomesPizzas;
	}
	
}

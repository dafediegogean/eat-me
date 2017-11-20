package br.com.diegogeandafe.pizzaria.modelo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diegogeandafe.pizzaria.modelo.entidades.Ingrediente;
import br.com.diegogeandafe.pizzaria.modelo.entidades.Pizzaria;
import br.com.diegogeandafe.pizzaria.modelo.repositorios.IngredienteRepositorio;

@Service
public class ServicoIngrediente {
	
	@Autowired private ServicoPizzaria servicoPizzaria;
	@Autowired private IngredienteRepositorio repositorio;
	
	//seta dono com o usuario logado
	//no caso getPizzariaLogada que e o usuario logado
	public void salvar(Ingrediente ingrediente) {
		ingrediente.setDono(servicoPizzaria.getPizzariaLogada());
		repositorio.save(ingrediente);
	}
	
	//lista ingredientes
	public Iterable<Ingrediente> listar(){
		Pizzaria dono = servicoPizzaria.getPizzariaLogada();
		return repositorio.findAllByDono(dono);
	}
	
	//busca id e objeto usuario (dono) logado 
	public Ingrediente buscar(long id) {
		Pizzaria dono = servicoPizzaria.getPizzariaLogada();
		return repositorio.findByIdAndDono(id, dono);
	}
	
	//se objeto retornado ingrediente for diferente de null e da pizzaria logado
	public void remover(long id) {
		Ingrediente ingrediente = this.buscar(id);
		if(ingrediente!=null) repositorio.delete(ingrediente);
		
	}
	
}

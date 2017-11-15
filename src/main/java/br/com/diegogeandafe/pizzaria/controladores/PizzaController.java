package br.com.diegogeandafe.pizzaria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.diegogeandafe.pizzaria.modelo.repositorios.PizzaRepositorio;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
	
	@Autowired private PizzaRepositorio pizzaRepositorio;
	
	@RequestMapping("/quantas")
	@ResponseBody
	public String quantasPizzas() {
		return "Atualmente ha "+pizzaRepositorio.count()+" cadastradas!";
	}
	
}

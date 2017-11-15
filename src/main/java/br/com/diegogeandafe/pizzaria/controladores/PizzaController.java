package br.com.diegogeandafe.pizzaria.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.diegogeandafe.pizzaria.excecoes.IngredienteInvalidoException;
import br.com.diegogeandafe.pizzaria.modelo.entidades.Ingrediente;
import br.com.diegogeandafe.pizzaria.modelo.entidades.Pizza;
import br.com.diegogeandafe.pizzaria.modelo.enumeracoes.CategoriaDePizza;
import br.com.diegogeandafe.pizzaria.modelo.repositorios.IngredienteRepositorio;
import br.com.diegogeandafe.pizzaria.modelo.repositorios.PizzaRepositorio;
import br.com.diegogeandafe.pizzaria.propertyeditors.IngredientePropertyEditor;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
	
	@Autowired private IngredientePropertyEditor ingredientePropertyEditor;
	
	@Autowired private PizzaRepositorio pizzaRepositorio;
	@Autowired private IngredienteRepositorio ingredienteRepositorio;
	
	@RequestMapping(method=RequestMethod.GET)
	private String listarPizzas(Model model){
		//pega lista do repositorio e passa para a view uma lista de pizza
		model.addAttribute("pizzas", pizzaRepositorio.findAll());
		//popula combo com array de categoria
		model.addAttribute("categorias",CategoriaDePizza.values());
		//popula combo com array de ingredientes
		model.addAttribute("ingredientes", ingredienteRepositorio.findAll());
		return "pizza/listagem";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String salvarPizza(Model model,
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult){
			
			//valida objeto esta correto, se hasErros constar, chama IngredienteInvalidoException, se nao constar salva o ingrediente
			if(bindingResult.hasErrors()) {	
				throw new IngredienteInvalidoException();
			}else {
				pizzaRepositorio.save(pizza);
			}
			
			//pega lista atraves do repositorio
			model.addAttribute("pizzas", pizzaRepositorio.findAll());	
			//popula combo com array de categoria
			model.addAttribute("categorias", CategoriaDePizza.values());	
			return "pizza/tabela-pizzas";
	}
	
	//retorna view com pizzas atualizadas
	@RequestMapping("/quantas")
	@ResponseBody
	public String quantasPizzas() {
		return "Atualmente ha "+pizzaRepositorio.count()+" cadastradas!";
	}
	
	//recebe webdatabinder para quando receber um objeto do tipo ingrediente
	//quando tiver alguem querendo ser inserido um id em um ingrediente utilizar o ingredientePropertyEditor
	//cada id que receber ira cair no setAsText transformando um texto em um id procura o ingrediente e 
	//seta um objeto
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Ingrediente.class, ingredientePropertyEditor);
	}
	
}

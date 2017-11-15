package br.com.diegogeandafe.pizzaria.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.diegogeandafe.pizzaria.modelo.entidades.Ingrediente;
import br.com.diegogeandafe.pizzaria.modelo.enumeracoes.CategoriaDeIngrediente;
import br.com.diegogeandafe.pizzaria.modelo.repositorios.IngredienteRepositorio;


// /app/ingredientes (metodo GET) -> listarIngredientes
// /app/ingredientes (metodo POST) -> salvarIngrediente
@Controller
@RequestMapping("/ingredientes")
public class IngredienteController {
	
	@Autowired
	private IngredienteRepositorio ingredienteRepositorio;

	@RequestMapping(method=RequestMethod.GET)
	public String listarIngredientes(Model model) {
		//traz todos os dados
		//objeto ingredientes de Iterable e semelhante a uma lista
		Iterable<Ingrediente> ingredientes = ingredienteRepositorio.findAll();
	
		model.addAttribute("titulo","Listagem de Ingredientes");
		//pega lista atraves do repositorio
		model.addAttribute("ingredientes", ingredientes);	
		
		//popula combo com array de categoria
		model.addAttribute("categorias", CategoriaDeIngrediente.values());
		return "ingrediente/listagem";
	}
	//WEB-INF/ingredientes/listagem.jsp
	
	@RequestMapping(method=RequestMethod.POST)
	public String salvarIngrediente(
			@Valid @ModelAttribute Ingrediente ingrediente,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		
		//valida objeto esta correto
		if(bindingResult.hasErrors()) {	
			FieldError error = bindingResult.getFieldErrors().get(0);
			redirectAttributes.addFlashAttribute("mensagemErro", "Não foi possível salvar o Ingrediente. "+error.getField()+" "+error.getDefaultMessage());
		}else {
			ingredienteRepositorio.save(ingrediente);
			redirectAttributes.addFlashAttribute("mensagemInfo", "O Ingrediente foi salvo corretamente.");
		}
		
		return "redirect:/app/ingredientes";
	}
	
	
}

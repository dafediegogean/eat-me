package br.com.diegogeandafe.pizzaria.propertyeditors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.diegogeandafe.pizzaria.modelo.entidades.Ingrediente;
import br.com.diegogeandafe.pizzaria.modelo.repositorios.IngredienteRepositorio;

//propertyeEditor classe que  executa o método antes de entrar no controlador
//vem com o byte de array do proprio property editor
@Component
public class IngredientePropertyEditor extends PropertyEditorSupport {
	
	@Autowired private IngredienteRepositorio ingredienteRepositorio;
	
	//transforma  texto em id procurar um ingrediente e setar o objeto
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long idIngrediente = Long.parseLong(text);
		Ingrediente ingrediente =  ingredienteRepositorio.findOne(idIngrediente);
		setValue(ingrediente);
		
	}
	
}

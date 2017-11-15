package br.com.diegogeandafe.pizzaria.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//quando lancar excecao ingrediente invalido exception retorna pro usuario bad request
@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class IngredienteInvalidoException extends RuntimeException {
	
	private static final long serialVersionUID = 3852286150939046755L;

}

package br.com.diegogeandafe.pizzaria.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//quando lancar excecao pizza invalida exception retorna pro usuario bad request
@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class PizzaInvalidoException extends Exception {

	private static final long serialVersionUID = -7746394383125154784L;

}

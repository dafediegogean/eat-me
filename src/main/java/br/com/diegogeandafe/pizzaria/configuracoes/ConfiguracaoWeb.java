package br.com.diegogeandafe.pizzaria.configuracoes;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages="br.com.diegogeandafe.pizzaria")
public class ConfiguracaoWeb extends WebMvcConfigurerAdapter {
	
	//configuracao do viewResolvers concatenando a view que bater na url
	// /WEB-FIN/ingredientes.jsp
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".jsp");
		//modo de renderizacao da tela, modelo jstl
		viewResolver.setViewClass(JstlView.class);
		registry.viewResolver(viewResolver);
	}
	
}

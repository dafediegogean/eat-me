package br.com.diegogeandafe.pizzaria.configuracoes;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
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
	
	//seta e passa como parametro lang ao escolher linguagem pela url
	//lang?=pt_BR ou lang?=en_US
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
		localeInterceptor.setParamName("lang");
		
		registry.addInterceptor(localeInterceptor);
	}
	
	//seta linguagem padrao da aplicacao
	@Bean
	public LocaleResolver localeResolver(){
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(new Locale("pt_BR"));
		return resolver;
	}
	
	//seta a path das properties de mensagens I18n internacionalizacao
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/I18n/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	
	//seta redirecionamento para index e login
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/*").setViewName("cliente/index");
		registry.addViewController("/login").setViewName("cliente/login");
	}
	
	//aciona o dispatcher para gerenciar js, css etc
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}
	


}
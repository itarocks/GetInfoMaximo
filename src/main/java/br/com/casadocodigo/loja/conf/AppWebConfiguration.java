package br.com.casadocodigo.loja.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controller.HomeController;
import br.com.casadocodigo.loja.daos.ProdutoDao;
import br.com.casadocodigo.loja.infra.FileSaver;

@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class, ProdutoDao.class, FileSaver.class})
public class AppWebConfiguration {

	@Bean
	public InternalResourceViewResolver internalResouceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
		
	}
	
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageResource = 
				new ReloadableResourceBundleMessageSource();
		messageResource.setBasename("WEB-INF/messages");
		messageResource.setDefaultEncoding("UTF-8");
		messageResource.setCacheSeconds(1);
		return messageResource;
	
	}
	
	
	@Bean	
		public FormattingConversionService mvcConversionService() {
		    DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(true);
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("dd/mm/yyyy"));
		registrar.registerFormatters(conversionService);
		
		return conversionService;
	}
	
	
	@Bean
	public MultipartResolver multipartResolver() {
		
		return new StandardServletMultipartResolver();
		
	}
	
}

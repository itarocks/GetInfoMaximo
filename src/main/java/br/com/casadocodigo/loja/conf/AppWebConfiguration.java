package br.com.casadocodigo.loja.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

import br.com.casadocodigo.loja.controller.HomeController;
import br.com.casadocodigo.loja.daos.ProdutoDao;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.model.CarrinhoCompras;

@EnableWebMvc
@ComponentScan(basePackageClasses = { HomeController.class, ProdutoDao.class, FileSaver.class, CarrinhoCompras.class })
@EnableCaching
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public InternalResourceViewResolver internalResouceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposedContextBeanNames("carrinhoCompras");
		return resolver;

	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
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

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CacheManager cacheManager() {

		CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(5,
				TimeUnit.MINUTES);
		GuavaCacheManager manager = new GuavaCacheManager();
		manager.setCacheBuilder(builder);

		return manager;
	}

	@Bean
	public ViewResolver contentNegotiationViewResolver(ContentNegotiationManager manager) {

		List<ViewResolver> viewResolvers = new ArrayList<>();
		viewResolvers.add(internalResouceViewResolver());
		viewResolvers.add(new JsonViewResolver());
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setViewResolvers(viewResolvers);
		resolver.setContentNegotiationManager(manager);
		return resolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LocaleChangeInterceptor());
	}

	@Bean
	public LocaleResolver localeResolver() {

		return new CookieLocaleResolver();

	}
	
	@Bean
	public MailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
	//	mailSender.setUsername("");
	//	mailSender.setPassword("");
		mailSender.setPort(587);
		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", true);
		mailProperties.put("mail.smtp.starttls.enable", true);
									
		mailSender.setJavaMailProperties(mailProperties);
		return mailSender;
		
	}

}

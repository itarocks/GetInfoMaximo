package br.com.casadocodigo.controllers;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.casadocodigo.loja.conf.AppWebConfiguration;
import br.com.casadocodigo.loja.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.conf.SecurityConfiguration;
import br.com.casadocodigo.loja.daos.ProdutoDao;

@RunWith(SpringJUnit4ClassRunner.class)
//aponta onde estão os arquivos de configuração
@WebAppConfiguration
@ContextConfiguration(classes = { JPAConfiguration.class, AppWebConfiguration.class,
		DataSourceConfigurationTest.class, SecurityConfiguration.class })
@ActiveProfiles("teste")
public class ProdutosControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvk;
	
	@Autowired
	private Filter springSecurityFilterChain;

	@Before
	public void setup() {
		this.mockMvk = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(springSecurityFilterChain).build();
	}

	@Test
	public void shouldReturnToHomePage() throws Exception {

		mockMvk.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("produtos"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/home.jsp"));

	}

	@Test
	public void somentAdminDeveAcessarProdutosForm() throws Exception {
		
		mockMvk.perform(MockMvcRequestBuilders.get("/produtos/form")
				.with(SecurityMockMvcRequestPostProcessors
						.user("user@casadocodigo.com.br").password("123456").roles("ADMIN")))
		.andExpect(MockMvcResultMatchers.status().is(403));
	
	}

}

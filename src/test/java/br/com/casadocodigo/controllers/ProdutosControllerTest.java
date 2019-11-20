package br.com.casadocodigo.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.casadocodigo.loja.daos.ProdutoDao;

@RunWith(SpringJUnit4ClassRunner.class)
//aponta onde estão os arquivos de configuração
@WebAppConfiguration
@ContextConfiguration(classes = { JPAConfiguration.class,AppWebConfiguration.class,DataSourceConfigurationTest.class })
@ActiveProfiles("teste")
public class ProdutosControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvk;

	@Before
	public void setup() {
		this.mockMvk = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void shouldReturnToHomePage() throws Exception {

		mockMvk.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("produtos"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/home.jsp"));
	

	}

}

package br.com.casadocodigo.loja.controller;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDao;
import br.com.casadocodigo.loja.daos.UsuarioDao;
import br.com.casadocodigo.loja.model.Produto;
import br.com.casadocodigo.loja.model.Role;
import br.com.casadocodigo.loja.model.Usuario;

@Controller
public class HomeController {

	@Autowired
	private ProdutoDao produtoDao;

	@Autowired
	private UsuarioDao usuarioDao;

	@RequestMapping("/")
	@Cacheable(value = "produtoHome")
	public ModelAndView index() {

		List<Produto> produtos = produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}

	@Transactional
	@ResponseBody
	@RequestMapping("/url-magica-maluca-dajsdiasjidjasidjiasjdiajsdiasjdiasjdiasjda")
	public String urlMagicaMaluca() {
		Usuario usuario = new Usuario();
		usuario.setNome("ADMIN");
		usuario.setEmail("admina@casadocodigo.com.br");
		usuario.setSenha("$2a$10$pYYC9HdQSHxoxSf0HW9jqeBiVpxxe0s2dZFL8EjzJ8RUNhwL6uRSS");
        usuario.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
		usuarioDao.gravar(usuario);
		return "Url Mágica Executada";
	}
}

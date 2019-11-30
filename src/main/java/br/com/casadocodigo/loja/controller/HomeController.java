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
		usuario.setEmail("admin@casadocodigo.com.br");
		usuario.setSenha("$04$qP517gz1KNVEJUTCkUQCY.JzEoXzHFjLAhPQjrg5iP6Z/UmWjvUhq");
        usuario.setRoles(Arrays.asList(new Role("ADMIN")));
		usuarioDao.gravar(usuario);
		return "Url Mágica Executada";
	}
}

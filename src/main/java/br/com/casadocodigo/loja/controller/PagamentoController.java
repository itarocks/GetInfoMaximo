package br.com.casadocodigo.loja.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.model.CarrinhoCompras;
import br.com.casadocodigo.loja.model.DadosPagamento;
import br.com.casadocodigo.loja.model.Usuario;

@RequestMapping("/pagamento")
@Controller
public class PagamentoController {

	@Autowired
	private CarrinhoCompras carrinho;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MailSender sender;

	@RequestMapping(value = "/finalizar", method = RequestMethod.POST)
	public Callable<ModelAndView> finalizar(@AuthenticationPrincipal Usuario usuario, RedirectAttributes model ) {
		
		

		return () -> {
			
			if(carrinho.getQuantidade() <= 0) {
				model.addFlashAttribute("falha", "Carrinho de Compra não pode estar vazio");
				return new ModelAndView("redirect:/carrinho");
			}
			
			String uri = "http://book-payment.herokuapp.com/payment";

			try {
				String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()),
						String.class);
				System.out.println(response);
    //Metodoi foi comentado devido ao fato de não ter um servidor de email
	//			enviaEmailCompraProduto(usuario);
				model.addFlashAttribute("sucesso", response);
				return new ModelAndView("redirect:/produtos");

			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				System.out.println();
				model.addFlashAttribute("falha", "Valor maior que o permitido");
				return new ModelAndView("redirect:/produtos");
			}

		};
	}

	private void enviaEmailCompraProduto(Usuario usuario) {
		// TODO Auto-generated method stub
		SimpleMailMessage emailMessage = new SimpleMailMessage();
		emailMessage.setSubject("Compra Finalizada com sucesso");
		//emailMessage.setTo(usuario.getEmail());
		emailMessage.setTo("rochaitamarr@gmail.com");
		emailMessage.setText("Compra aprovada com sucesso no valor de " + carrinho.getTotal());
		emailMessage.setFrom("compras@casadocodigo.com.br");
		
		sender.send(emailMessage);
	}

}

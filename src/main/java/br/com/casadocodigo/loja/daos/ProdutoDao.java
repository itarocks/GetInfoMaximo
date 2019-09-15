package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.model.Produto;

@Repository
@Transactional
public class ProdutoDao {

	@PersistenceContext
	private EntityManager manager;

	public void gravar(Produto produto) {

		try{
		   manager.persist(produto);
		}catch(Exception e) {
			System.out.println("O erro é " + e);
		}

	}


	public List<Produto> listar() {
		// TODO Auto-generated method stub
		return manager.createQuery("select p from Produto p", Produto.class).getResultList();
	}


	public Produto find(Integer id) {
		// TODO Auto-generated method stub
		return manager.createQuery("select distinct(p) from Produto p  join fetch p.precos preco where p.id = :id", Produto.class)
				.setParameter("id", id)
				.getSingleResult();
	}

}

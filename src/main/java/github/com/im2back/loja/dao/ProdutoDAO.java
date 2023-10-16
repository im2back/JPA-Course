package github.com.im2back.loja.dao;

import github.com.im2back.loja.model.produto.Produto;
import jakarta.persistence.EntityManager;

public class ProdutoDAO {
	
	private EntityManager em;
	
	 public ProdutoDAO (EntityManager em) {
		 this.em = em;
	 }
	 public void cadastrar(Produto produto) {
		 this.em.persist(produto);
	 }
}

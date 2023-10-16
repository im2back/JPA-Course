package github.com.im2back.loja.testes;

import java.math.BigDecimal;

import github.com.im2back.loja.dao.ProdutoDAO;
import github.com.im2back.loja.model.produto.Categoria;
import github.com.im2back.loja.model.produto.Produto;
import github.com.im2back.loja.util.JpaUTIL;
import jakarta.persistence.EntityManager;

public class CadastroDeProduto {
	
	public static void main(String[] args) {
		Produto celular  = new Produto("samsumg","celular bom",new BigDecimal(400), Categoria.CELULARES); 

		//comandos para inserção no banco de dados
		
		EntityManager em = JpaUTIL.getEntityManager();
		
		ProdutoDAO dao = new ProdutoDAO(em);
		
		em.getTransaction().begin();
		dao.cadastrar(celular);//em.persist(celular);
		em.getTransaction().commit();
		em.close();
	}

}

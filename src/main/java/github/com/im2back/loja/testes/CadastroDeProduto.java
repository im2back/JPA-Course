package github.com.im2back.loja.testes;

import java.math.BigDecimal;

import github.com.im2back.loja.dao.CategoriaDAO;
import github.com.im2back.loja.dao.ProdutoDAO;
import github.com.im2back.loja.model.produto.Categoria;
import github.com.im2back.loja.model.produto.Produto;
import github.com.im2back.loja.util.JpaUTIL;
import jakarta.persistence.EntityManager;

public class CadastroDeProduto {
	
	public static void main(String[] args) {
		
		Categoria categoria = new Categoria("celulares");
		
		Produto celular  = new Produto("samsumg","celular bom",new BigDecimal(400), categoria); 

		//comandos para inserção no banco de dados
		
		
		EntityManager em = JpaUTIL.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		CategoriaDAO categoriaDao = new CategoriaDAO(em);
			
		em.getTransaction().begin();
		categoriaDao.cadastrar(categoria);
		produtoDao.cadastrar(celular);  //em.persist(celular);
		em.getTransaction().commit();
		em.close();
	}

}

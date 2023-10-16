package github.com.im2back.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import github.com.im2back.loja.dao.CategoriaDAO;
import github.com.im2back.loja.dao.ProdutoDAO;
import github.com.im2back.loja.model.produto.Categoria;
import github.com.im2back.loja.model.produto.Produto;
import github.com.im2back.loja.util.JpaUTIL;
import jakarta.persistence.EntityManager;

public class CadastroDeProduto {
	
	public static void main(String[] args) {	
		cadastrarProduto();
		EntityManager em = JpaUTIL.getEntityManager();
        ProdutoDAO produtoDao = new ProdutoDAO(em);
        
        Produto p = produtoDao.buscarPorId(1l);
        System.out.println(p.getPreco());
        

		List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("celulares");
		todos.forEach(p2 -> System.out.println(p2.getNome()));
	}

	private static void cadastrarProduto() {
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

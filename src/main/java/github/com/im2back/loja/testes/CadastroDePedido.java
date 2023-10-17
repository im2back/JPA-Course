package github.com.im2back.loja.testes;

import java.math.BigDecimal;

import github.com.im2back.loja.dao.CategoriaDAO;
import github.com.im2back.loja.dao.ClienteDAO;
import github.com.im2back.loja.dao.PedidoDAO;
import github.com.im2back.loja.dao.ProdutoDAO;
import github.com.im2back.loja.model.Categoria.Categoria;
import github.com.im2back.loja.model.cliente.Cliente;
import github.com.im2back.loja.model.pedidos.ItemPedido;
import github.com.im2back.loja.model.pedidos.Pedido;
import github.com.im2back.loja.model.produto.Produto;
import github.com.im2back.loja.util.JpaUTIL;
import jakarta.persistence.EntityManager;

public class CadastroDePedido {
	
	public static void main(String[] args) {
		
		popularDataBase();
		
		EntityManager em = JpaUTIL.getEntityManager();
		
		/*Recuperando  um produto e um cliente que ja foram préviamente cadastrados na nossa Data-Base pelo método popularDataBase(); */
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		Produto produto = produtoDao.buscarPorId(1l);
		
		ClienteDAO clienteDao = new ClienteDAO(em);
		Cliente cliente = clienteDao.buscarPorId(1l);
		/*Recuperando  um produto e um cliente que ja foram préviamente cadastrados na nossa Data-Base pelo método popularDataBase(); */
		
		em.getTransaction().begin();
		
		/*Cadastrando um novo pedido, passando como parametro o cliente resgatado da DataBase*/	
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(2, pedido, produto));
		
		PedidoDAO pedidoDAO = new PedidoDAO(em);
		pedidoDAO.cadastrar(pedido);
		
		
		em.getTransaction().commit();
	}


	private static void popularDataBase() {
		
		
		//Instanciando as entidades
		Categoria categoria = new Categoria("celulares");
		Produto celular  = new Produto("samsumg","celular bom",new BigDecimal(400), categoria); 
		Cliente cliente = new Cliente("Jefferson", "00769203213");		
		
		EntityManager em = JpaUTIL.getEntityManager();
		
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		CategoriaDAO categoriaDao = new CategoriaDAO(em);
		ClienteDAO clienteDAO = new ClienteDAO(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(categoria);
		produtoDao.cadastrar(celular);  //em.persist(celular);
		clienteDAO.cadastrar(cliente);
		
		em.getTransaction().commit();
		em.close();
	}
}

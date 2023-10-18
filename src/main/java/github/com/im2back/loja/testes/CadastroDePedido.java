package github.com.im2back.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import github.com.im2back.loja.dao.CategoriaDAO;
import github.com.im2back.loja.dao.ClienteDAO;
import github.com.im2back.loja.dao.PedidoDAO;
import github.com.im2back.loja.dao.ProdutoDAO;
import github.com.im2back.loja.model.Categoria.Categoria;
import github.com.im2back.loja.model.cliente.Cliente;
import github.com.im2back.loja.model.pedidos.ItemPedido;
import github.com.im2back.loja.model.pedidos.Pedido;
import github.com.im2back.loja.model.produto.Produto;
import github.com.im2back.loja.model.relatorios.RelatorioDeVendasVo;
import github.com.im2back.loja.util.JpaUTIL;
import jakarta.persistence.EntityManager;

public class CadastroDePedido {
	
	public static void main(String[] args) {
		
		popularDataBase();
		
		EntityManager em = JpaUTIL.getEntityManager();
		
		/*Recuperando  um produto e um cliente que ja foram préviamente cadastrados na nossa Data-Base pelo método popularDataBase(); */
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		ClienteDAO clienteDao = new ClienteDAO(em);
		
		Cliente cliente = clienteDao.buscarPorId(1l);
		Produto produto = produtoDao.buscarPorId(1l);
		Produto produto2 = produtoDao.buscarPorId(2l);
		Produto produto3 = produtoDao.buscarPorId(3l);
		/*Recuperando  um produto e um cliente que ja foram préviamente cadastrados na nossa Data-Base pelo método popularDataBase(); */
		
		em.getTransaction().begin();
		
		/*Cadastrando um novo pedido, passando como parametro o cliente resgatado da DataBase*/	
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));
		pedido.adicionarItem(new ItemPedido(40, pedido, produto2));
		
		Pedido pedido2 = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(2, pedido2, produto3));
		
		
		PedidoDAO pedidoDAO = new PedidoDAO(em);
		pedidoDAO.cadastrar(pedido);
		pedidoDAO.cadastrar(pedido2);
		
		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDAO.valorTotalVendido();
		System.out.println("VALOR TOTAL" + totalVendido);
		
		List<RelatorioDeVendasVo> relatorio = pedidoDAO.relatioDeVendas();

		relatorio.forEach(System.out::println);
		
	}


	private static void popularDataBase() {
		
		
		//Instanciando as entidades
		Categoria celular = new Categoria("SMARTFONES");
		Categoria game = new Categoria("GAMES");
		Categoria pc = new Categoria("COMPUTADORES");
		
		Produto samsumg  = new Produto("samsumg","celular bom",new BigDecimal(400), celular); 
		Produto ps5 = new Produto("ps5","game bom",new BigDecimal(800), game);
		Produto windows  = new Produto("windows","celular bom",new BigDecimal(100),pc); 
		
		Cliente cliente = new Cliente("Jefferson", "00769203213");		
		
		EntityManager em = JpaUTIL.getEntityManager();
		
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		CategoriaDAO categoriaDao = new CategoriaDAO(em);
		ClienteDAO clienteDAO = new ClienteDAO(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celular);
		categoriaDao.cadastrar(game);
		categoriaDao.cadastrar(pc);
		
		
		produtoDao.cadastrar(samsumg);  //em.persist(celular);
		produtoDao.cadastrar(ps5);
		produtoDao.cadastrar(windows);
		clienteDAO.cadastrar(cliente);
		
		em.getTransaction().commit();
		em.close();
	}
}

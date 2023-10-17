package github.com.im2back.loja.dao;

import github.com.im2back.loja.model.pedidos.Pedido;
import jakarta.persistence.EntityManager;

public class PedidoDAO {
	
	private EntityManager em;
	
	 public PedidoDAO (EntityManager em) {
		 this.em = em;
	 }
	 public void cadastrar(Pedido pedido) {
		 this.em.persist(pedido);
	 }
	
}

package github.com.im2back.loja.dao;

import java.math.BigDecimal;

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
	  public BigDecimal valorTotalVendido() {
			String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
			return em.createQuery(jpql, BigDecimal.class)
					.getSingleResult();
		}
}

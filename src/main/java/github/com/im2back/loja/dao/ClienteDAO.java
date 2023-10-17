package github.com.im2back.loja.dao;

import github.com.im2back.loja.model.cliente.Cliente;
import jakarta.persistence.EntityManager;

public class ClienteDAO {
	
	private EntityManager em;
	
	 public ClienteDAO (EntityManager em) {
		 this.em = em;
	 }
	 public void cadastrar(Cliente pedido) {
		 this.em.persist(pedido);
	 }
	public Cliente buscarPorId(Long id) {
		return em.find(Cliente.class, id);
	}
	
}

package github.com.im2back.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import github.com.im2back.loja.model.pedidos.Pedido;
import github.com.im2back.loja.model.relatorios.RelatorioDeVendasVo;
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
	  
	  /*Elaboroando uma consulta que retornará um relatorio de vendas, esse relatório conterá informações de tabelas distintas: 
	   Nome do Produto(String)|| Quantidade vendida(int) || Ultima venda (LocalDate)*/
	  public List<RelatorioDeVendasVo> relatioDeVendas(){
			String jpql = "SELECT new github.com.im2back.loja.model.relatorios.RelatorioDeVendasVo ("
					+ "produto.nome,"
			+"SUM(item.quantidade),"		
			+"MAX(pedido.data))"
			+"FROM Pedido pedido "
			+"JOIN pedido.items item "
			+"JOIN item.produto produto "
			+"GROUP BY produto.nome "
			+"ORDER BY item.quantidade DESC";
			
			return em.createQuery(jpql,RelatorioDeVendasVo.class).getResultList();
		}
	  
	  /*Consulta utlizando parameteos dinamicos
	   * public List<Cliente> buscarClientes(String nome, LocalDate dataNascimento) {
		    String jpql = "SELECT c FROM Cliente c WHERE ";
		    if (nome != null && !nome.trim().isEmpty()) {
		        jpql += "AND c.nome = :nome ";
		    }
		    if (dataNascimento != null) {
		        jpql += " AND c.dataNascimento = :dataNascimento ";
		    }
		    TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
		    if (nome != null && !nome.trim().isEmpty()) {
		        query.setParameter("nome", nome);
		    }
		    if (dataNascimento != null) {
		        query.setParameter("dataNascimento", dataNascimento);
		    }
		    return query.getResultList();
		} */
}

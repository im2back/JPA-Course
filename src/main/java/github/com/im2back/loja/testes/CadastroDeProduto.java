package github.com.im2back.loja.testes;

import java.math.BigDecimal;

import github.com.im2back.loja.model.produto.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CadastroDeProduto {
	
	public static void main(String[] args) {
		Produto celular  = new Produto(); 
		celular.setNome("samsumg s20");
		celular.setDescricao("muito bom aparelho");
		celular.setPreco(new BigDecimal(400));
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");
		EntityManager em =  factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(celular);
		em.getTransaction().commit();
		em.close();
	}

}

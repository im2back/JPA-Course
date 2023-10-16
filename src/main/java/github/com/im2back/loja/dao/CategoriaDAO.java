package github.com.im2back.loja.dao;

import github.com.im2back.loja.model.produto.Categoria;
import jakarta.persistence.EntityManager;
import lombok.NoArgsConstructor;

public class CategoriaDAO {
	
	private EntityManager em;
	
	 public CategoriaDAO (EntityManager em) {
		 this.em = em;
	 }
	 public void cadastrar(Categoria categoria) {
		 this.em.persist(categoria);
	 }
}

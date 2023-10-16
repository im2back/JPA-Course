package github.com.im2back.loja.dao;

import github.com.im2back.loja.model.Categoria.Categoria;
import jakarta.persistence.EntityManager;

public class CategoriaDAO {
	
	private EntityManager em;
	
	 public CategoriaDAO (EntityManager em) {
		 this.em = em;
	 }
	 public void cadastrar(Categoria categoria) {
		 this.em.persist(categoria);
	 }
}

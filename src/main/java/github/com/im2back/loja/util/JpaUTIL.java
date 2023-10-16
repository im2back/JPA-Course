package github.com.im2back.loja.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUTIL {
	
	 private  static final  EntityManagerFactory FACTORY =  Persistence.createEntityManagerFactory("loja");
	
	 public static EntityManager getEntityManager() {
		 return FACTORY.createEntityManager();
	 }
}

package github.com.im2back.loja.model.cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@jakarta.persistence.Embedded
	private DadosPessoais dadosPessoais;
	
	public Cliente(String nome, String cpf) {
		super();
		this.dadosPessoais = new DadosPessoais(nome, cpf);  
		
	}

}

package github.com.im2back.loja.model.cliente;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@Embeddable
@NoArgsConstructor
public class DadosPessoais {
	private String nome;
	private String cpf;
	
	/*a classe embedenble serve para extender e organizar melhor o codigo de uma classe grande*/
	
}

package github.com.im2back.loja.model.produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Livro extends Produto {
	private String autor;
	private Integer numeroDePaginas;
	
}

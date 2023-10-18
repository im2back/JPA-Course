package github.com.im2back.loja.model.pedidos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import github.com.im2back.loja.model.cliente.Cliente;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="pedidos")
@Getter
@Setter
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate data = LocalDate.now();
	
	private BigDecimal valorTotal = new BigDecimal(0);
	
	/*Por padrão, ao fazer uma consulta na db utilizando jqpl, a entidade que tem um relacionamento do tipo
	 "ToOne" sempre será carregada junto, mesmo que não seje solicitada* para evitar problemas de performance
	 por carregamentos de informalções desnecessarias, o parametro (fetch = FetchType.LAZY) ao lado da @Anotacao */
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
	
	/*ao fazer o mapeamento dos dois lados, isso torna um relacionamento bidirecional. Para evitar que o JPA interprete errado e 
	crie uma tabela desnecessaria  vamos acressentar um atributo chamado (mappedBy = "pedido") e passando como string o nome do atributo que esta 
	mapeado "outro lado do relacionamento" que esta mapeado*/
	
	@OneToMany(mappedBy = "pedido",cascade = CascadeType.ALL) /*cascade = CascadeType.ALL se eu salvar umpedido automaticamente ele salva o item*/
	private List<ItemPedido> items = new ArrayList<>();
	
	public Pedido(Cliente cliente) {
	
		this.cliente = cliente;
		
	} 
	
	public void adicionarItem(ItemPedido item) {
		item.setPedido(this);//item conhece o pedido
		this.items.add(item);// pedido conhece o item
		this.valorTotal = this.valorTotal.add(item.valorTotal());
	}

	
}

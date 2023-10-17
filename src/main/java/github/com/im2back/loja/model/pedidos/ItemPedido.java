package github.com.im2back.loja.model.pedidos;

import java.math.BigDecimal;

import github.com.im2back.loja.model.produto.Produto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "itens_pedido") 
@NoArgsConstructor
@Getter
@Setter
public class ItemPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal precoUnitario;
	
	private int quantidade;
	/*Até aqui a classe é escrita como qualquer outra*/
	
	/*Agora vamos colocar as instancias de Pedido e do Produto associados a esa classe e colocar as anotações cardinais, neste caso também
	 vamos fazer o mapeamento na outra classe também por ex: na classe Pedido no atributo private   @OneToMany List<ItemPedido> items */
	@ManyToOne
	private Pedido pedido;
	
	@ManyToOne
	private Produto produto;
	
	public ItemPedido(int quantidade, Pedido pedido, Produto produto) {
		super();
		this.quantidade = quantidade;
		this.pedido = pedido;
		this.precoUnitario = produto.getPreco();
		this.produto = produto;
	}
	
	public BigDecimal valorTotal() {
		
		int quantidade = this.quantidade;
		BigDecimal quantidadeDecimal = new BigDecimal(quantidade);
		BigDecimal preco = this.precoUnitario;		
		BigDecimal mult = preco.multiply(quantidadeDecimal);
		
		return mult;
	}
}

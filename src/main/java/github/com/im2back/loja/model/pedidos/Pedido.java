package github.com.im2back.loja.model.pedidos;

import java.math.BigDecimal;
import java.time.LocalDate;

import github.com.im2back.loja.model.cliente.Cliente;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
	
	private BigDecimal valorTotal;
	
	@ManyToOne
	private Cliente cliente;
	
	public Pedido(Cliente cliente) {
	
		this.cliente = cliente;
		
	} 

	
}

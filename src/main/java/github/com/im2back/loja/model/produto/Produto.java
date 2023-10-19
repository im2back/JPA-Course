package github.com.im2back.loja.model.produto;
import java.math.BigDecimal;
import java.time.LocalDate;

import github.com.im2back.loja.model.Categoria.Categoria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // anotação referente a herança

@Entity  //Anotação que indica que há uma tabela no banco de dados que representa essa classe

@Table(name = "produtos")  /*Essa anotação especifica o nome da tabela (que foi criada la na database) que representará essa entidade. 
Embora o nome da entidade seja "Produto" o nome da tabela será "produtos". Caso eu não utilize essa anotação, o JPA criará automaticamente uma 
tabela como o mesmo nome da nossa entidade, nesse caso "Produto"*/

@Getter
@Setter

@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {
	/*Dentro da classe teremos os atributos que representarão as colunas da nosssa tabela*/
	/*A anotação @Column segue o mesmo principio da anotação @Table, porém a anotação @Column irá especificar o nome da coluna caso ao criar a tabela 
	 tenhamos colocado uma coluna com um nome diferente do atributo que a representa*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;/*A anotação @Id irá informar que o nosso atributo "Long id" será conciderado a chave primaria da tabela e a anotação 
@GeneratedValue indica que esse valor será gerado automaticamentede acordo com a nossa estrategia passada como parametro --> (strategy = GenerationType.IDENTITY)*/
	
	private String nome;
	
	@Column(name="desc")
	private String descricao;
	
	private BigDecimal preco;
	
	private LocalDate  dataCadastro = LocalDate.now();
	
	/*Situalção 1 - @Enumerated(EnumType.STRING) Anotação usada para salvar(no banco de dados) nosso tipo enumerado como uma STRING 
	private Categoria  categoria; */
	
	/*Situação 2 - Temos a tabela produto relacionada com a tabela categoria, assim sendo temos que definir a cardinalidade que neste caso é  : 
	  Um produto esta vinculado a uma categoria, porém uma categoria pode estar vinculada a varios produtos  (Produto * ----- 1 Categoria)
	  a anotação que define essa relação é a @ManyToOne(muitos para um*----1)        */
	@ManyToOne(fetch = FetchType.LAZY)
	private Categoria  categoria;


	public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
	}
	
	

}

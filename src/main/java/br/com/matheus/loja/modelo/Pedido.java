package br.com.matheus.loja.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valorTotal = BigDecimal.ZERO;
    private LocalDateTime data = LocalDateTime.now();

    //para cardinalidade para 1 nos selects a jpa faz um join com essa table por padrao
    //eager e laze, por padrao eager, carrega os relacionamento to one

    //todo relacionamento to One deve ser laze, é uma boa pratica

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    //relacionamento bidirecional
    //para esse tipo de relacionamento é necessario usar
    // o mappedBy pq senao a jpa criara uma nova table
    //cascade, toda acao na table pedido reflete na itens_pedido efeito cascata

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public Pedido(){}

    public Pedido(Cliente cliente){
        this.cliente = cliente;
    }

    public void adicionarPedido(ItemPedido item){
        item.setPedido(this);
        itens.add(item);
        this.valorTotal = this.valorTotal.add(item.getValor());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }




}

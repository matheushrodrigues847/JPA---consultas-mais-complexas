package br.com.matheus.loja.DAO;

import br.com.matheus.loja.modelo.Cliente;
import br.com.matheus.loja.modelo.Pedido;
import br.com.matheus.loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PedidoDAO {
    private EntityManager em;

    public PedidoDAO(EntityManager em){
        this.em = em;
    }

    public void cadastrar(Pedido pedido){
        em.persist(pedido);
    }

    public BigDecimal valorTotalVendido(){
        String jpql = "select sum(p.valorTotal) from Pedido p";
        return em.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    //consulta planejada JOIN FETCH
    public Pedido buscarPedicoComClientePeloId(long id){
        return em.createQuery("select p from Pedido p join fetch p.cliente where p.id = :id", Pedido.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    public List<Object[]> relatorioDeVendas(){
        String jpql = "select ip.produto.nome, sum(ip.quantidade), max(ip.pedido.data)" +
                "from ItemPedido ip group by ip.produto.nome order by ip.quantidade";
        return em.createQuery(jpql, Object[].class).getResultList();

    }

    public List<RelatorioDeVendasVo> relatorioDeVendasComSelectNew(){
        String jpql = "select new br.com.matheus.loja.vo.RelatorioDeVendasVo(produto.nome, sum(item.quantidade), max(pedido.data))" +
                "from Pedido pedido join pedido.itens item join item.produto produto " +
                "group by produto.nome order by item.quantidade";


        return em.createQuery(jpql, RelatorioDeVendasVo.class).getResultList();

    }

    public List<Object[]> relatorioDeVendasComInnerJoin2(){
        String jpql = "select produto.nome, sum(item.quantidade), max(pedido.data)" +
                "from ItemPedido item join item.pedido pedido join item.produto produto " +
                "group by produto.nome order by item.quantidade";
        return em.createQuery(jpql, Object[].class).getResultList();

    }

//    public List<Object[]> relatorioDeVendasComInnerJoin(){
//        String jpql = "select produto.nome, sum(item.quantidade), max(pedido.data)" +
//                "from Pedido pedido join pedido.itens item join item.produto produto " +
//                "group by produto.nome order by item.quantidade";
//        return em.createQuery(jpql, Object[].class).getResultList();
//
//    }
}

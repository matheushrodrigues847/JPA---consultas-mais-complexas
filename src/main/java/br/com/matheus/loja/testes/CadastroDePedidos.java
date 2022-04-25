package br.com.matheus.loja.testes;

import br.com.matheus.loja.DAO.ClienteDAO;
import br.com.matheus.loja.DAO.PedidoDAO;
import br.com.matheus.loja.DAO.ProdutoDAO;
import br.com.matheus.loja.modelo.*;
import br.com.matheus.loja.util.BancoUtil;
import br.com.matheus.loja.util.JPAutil;
import br.com.matheus.loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class CadastroDePedidos {

    //atalhos intellij
    // alt + j
    //ctrl + alt + l
    public static void main(String[] args){
        EntityManager em = JPAutil.getEntityManager();

        em.getTransaction().begin();

        BancoUtil.popularBancoDeDados(em);

        em.getTransaction().commit();

        em.getTransaction().begin();

        ClienteDAO clienteDAO = new ClienteDAO(em);
        PedidoDAO pedidoDAO = new PedidoDAO(em);
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        Cliente cliente = clienteDAO.buscarPorId(1l);
        Produto produto = produtoDAO.buscaPorId(1l);
        Produto produto2 = produtoDAO.buscaPorId(2l);

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarPedido(new ItemPedido(10, produto, pedido));
        pedido.adicionarPedido(new ItemPedido(20, produto2, pedido));
        pedidoDAO.cadastrar(pedido);


        em.getTransaction().commit();

        System.out.println(pedidoDAO.valorTotalVendido());
        System.out.println(pedido);

        long inicio = System.currentTimeMillis();

        //o select new facilita muito para fazer relatorios, uma vez que nao e preciso
        //criar uma nova interface. Só é preciso criar um vo value object
        List<RelatorioDeVendasVo> lista = pedidoDAO.relatorioDeVendasComSelectNew();



        lista.forEach(System.out::println);

//        for(Object[] obj : lista){
//            System.out.println(obj[0]);
//            System.out.println(obj[1]);
//            System.out.println(obj[2]);
//        }
        long fim = System.currentTimeMillis();
        System.out.println(fim - inicio);

        //10
        //17
        //14
        em.close();
    }


}

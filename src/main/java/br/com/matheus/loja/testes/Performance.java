package br.com.matheus.loja.testes;

import br.com.matheus.loja.DAO.ClienteDAO;
import br.com.matheus.loja.DAO.PedidoDAO;
import br.com.matheus.loja.DAO.ProdutoDAO;
import br.com.matheus.loja.modelo.Cliente;
import br.com.matheus.loja.modelo.ItemPedido;
import br.com.matheus.loja.modelo.Pedido;
import br.com.matheus.loja.modelo.Produto;
import br.com.matheus.loja.util.BancoUtil;
import br.com.matheus.loja.util.JPAutil;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

public class Performance {
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
        Pedido pedido2 = pedidoDAO.buscarPedicoComClientePeloId(1l);
        em.close();

//        pedido2 = em.merge(pedido2);
//        pedido2.setData(LocalDateTime.now());

        //para um mapeamento laze, deve se usar o join fetch numa nova querie
        //dessa forma mesmo que esteja fechada a entity a consulta podera ser feita
        System.out.println(pedido2.getCliente().getNome());

    }
}

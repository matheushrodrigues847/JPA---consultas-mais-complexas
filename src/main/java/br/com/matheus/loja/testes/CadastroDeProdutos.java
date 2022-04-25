package br.com.matheus.loja.testes;

import br.com.matheus.loja.DAO.CategoriaDAO;
import br.com.matheus.loja.DAO.ProdutoDAO;
import br.com.matheus.loja.modelo.Categoria;
import br.com.matheus.loja.modelo.Produto;
import br.com.matheus.loja.util.BancoUtil;
import br.com.matheus.loja.util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProdutos {
    public static void main(String[] args)  {
        EntityManager em = JPAutil.getEntityManager();
        em.getTransaction().begin();
        BancoUtil.popularBancoDeDados(em);
        em.getTransaction().commit();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        List<Produto> lista = produtoDAO.namedQuery("informatica");
        lista.forEach(System.out::println);

        if(lista.isEmpty()) System.out.println("\n Lista vazia");
        em.close();
    }

    private static void cadastrarProduto() {
//        Categoria informatica = new Categoria("informatica");
//        Produto notebook = new Produto("notebook", "256gb SSD", new BigDecimal("3000"), informatica);
//
//        //padrao factory e DAO
//        EntityManager em = JPAutil.getEntityManager();
//
//        ProdutoDAO produtoDAO = new ProdutoDAO(em);
//        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
//
//
//        //com um framework nao é necessario controlar a transacao com begin, commit  e close
//        em.getTransaction().begin();
//        categoriaDAO.cadastrar(informatica);
//        produtoDAO.cadastrar(notebook);
//
//        em.getTransaction().commit();
//        em.close();
//        em.remove(notebook);
    }
}

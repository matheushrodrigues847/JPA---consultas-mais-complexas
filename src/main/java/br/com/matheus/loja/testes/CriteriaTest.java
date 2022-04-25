package br.com.matheus.loja.testes;

import br.com.matheus.loja.DAO.ProdutoDAO;
import br.com.matheus.loja.modelo.Produto;
import br.com.matheus.loja.util.BancoUtil;
import br.com.matheus.loja.util.JPAutil;

import javax.persistence.EntityManager;
import java.util.List;

public class CriteriaTest {
    public static void main(String[] args){
        EntityManager em = JPAutil.getEntityManager();

        em.getTransaction().begin();

        BancoUtil.popularBancoDeDados(em);

        em.getTransaction().commit();


        ProdutoDAO produtoDAO = new ProdutoDAO(em);

//        List<Produto> produtos = produtoDAO.buscaComParametroSemCriteria("s20", null, null);
        List<Produto> produtos = produtoDAO.buscaComParametrosComCriteria("notebook", null, null);
        produtos.forEach(p -> System.out.println(p.getDescricao()));
    }
}

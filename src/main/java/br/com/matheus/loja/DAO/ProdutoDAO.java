package br.com.matheus.loja.DAO;

import br.com.matheus.loja.modelo.Cliente;
import br.com.matheus.loja.modelo.Produto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ProdutoDAO {
    private EntityManager em;

    public ProdutoDAO(EntityManager em){
        this.em = em;
    }

    //baixo acoplamento com o banco de dados
    //menos verboso em comparacao com o jdbc
    public void cadastrar(Produto produto){
        em.persist(produto);
    }

    public Produto buscaPorId(Long id){

        return em.find(Produto.class, id);
    }

    public List<Produto> listar(){
        String jpql = "SELECT p FROM Produto p";
        return em.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> buscarPorNome(String nome){
        String jpql = "SELECT p FROM Produto p WHERE p.nome = ?1";
        return em.createQuery(jpql, Produto.class)
                .setParameter(1, nome)
                .getResultList();
    }

    public List<Produto> buscarPorNomeDaCategoria(String nome){
        //join muito menos verboso -- p.categoria
        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = ?1";
        return em.createQuery(jpql, Produto.class)
                .setParameter(1, nome)
                .getResultList();
    }

    public List<Produto> namedQuery(String nome){
        //a query fica na entidade

        return em.createNamedQuery("Produto.produtoPorCategoria", Produto.class)
                .setParameter(1, nome)
                .getResultList();
    }

    public BigDecimal buscarPrecoComNomeDoProduto(String nome){
        //join muito menos verboso -- p.categoria
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = ?1";
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter(1, nome)
                .getSingleResult();
    }

    public List<Produto> buscaComParametroSemCriteria(String nome, BigDecimal preco, LocalDateTime data){
        String jpql = "SELECT p from Produto p WHERE 1=1";

        if(nome != null && !nome.trim().isEmpty()){
            jpql += " AND p.nome = :nome";
        }
        if(data != null){
            jpql += " AND p.dataHora = :data";
        }
        if(preco != null){
            jpql += "AND p.preco = :preco";
        }

        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);

        if(nome != null && !nome.trim().isEmpty()){
            query.setParameter("nome", nome);
        }
        if(data != null){
            query.setParameter("data", data);
        }
        if(preco != null){
            query.setParameter("preco", preco);
        }

        return query.getResultList();
    }

    public List<Produto> buscaComParametrosComCriteria(String nome, BigDecimal preco, LocalDateTime data){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class);

        //como o select sera o mesmo do from nao e preciso definir o query.select

        Predicate and = builder.and();
        if(nome != null && !nome.trim().isEmpty()){
            and = builder.and(and, builder.equal(from.get("nome"), nome));
        }
        if(preco != null){
            and = builder.and(and, builder.equal(from.get("preco"), preco));
        }
        if(data!= null){
            and = builder.and(and, builder.equal(from.get("data"), data));
        }

        query.where(and);

        return em.createQuery(query).getResultList();
    }
//    public List<Produto> buscarPorNomeLike(String nome){
//        String jpql = "SELECT p FROM Produto p WHERE p.nome LIKE '%:nome%'";
//        return em.createQuery(jpql, Produto.class)
//                .setParameter("%" + nome + "%", nome)
//                .getResultList();
//    }
}

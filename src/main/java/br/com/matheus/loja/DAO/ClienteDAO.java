package br.com.matheus.loja.DAO;

import br.com.matheus.loja.modelo.Cliente;

import javax.persistence.EntityManager;

public class ClienteDAO {
    private EntityManager em;

    public ClienteDAO(EntityManager em){
        this.em = em;
    }

    public void cadastrar(Cliente cliente){
        em.persist(cliente);
    }

    public Cliente buscarPorId(long id) {
        return em.find(Cliente.class,id);
    }
}

package br.com.matheus.loja.util;

import br.com.matheus.loja.DAO.CategoriaDAO;
import br.com.matheus.loja.DAO.ClienteDAO;
import br.com.matheus.loja.DAO.ProdutoDAO;
import br.com.matheus.loja.modelo.Categoria;
import br.com.matheus.loja.modelo.Cliente;
import br.com.matheus.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public abstract class BancoUtil {

    public static void popularBancoDeDados(EntityManager em) {

        Categoria informatica = new Categoria("informatica");
        Categoria celulares = new Categoria("celulares");

        Produto notebook = new Produto("notebook", "256gb SSD", new BigDecimal("3000"), informatica);
        Produto s20 = new Produto("s20", "256gb 8g ram", new BigDecimal("2000"), celulares);

        Cliente cliente = new Cliente("matheus","123");


        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);



        categoriaDAO.cadastrar(informatica);
        categoriaDAO.cadastrar(celulares);

        produtoDAO.cadastrar(notebook);
        produtoDAO.cadastrar(s20);

        clienteDAO.cadastrar(cliente);

    }
}

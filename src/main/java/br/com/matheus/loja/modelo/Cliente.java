package br.com.matheus.loja.modelo;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded //embutida nessa entidade
    private DadosPessoais dadosPessoais;
    //dados pessoais nao é uma entidade, só esta ocorrendo a separacao para organizar
    //sera uma coluna da table clientes

    public Cliente(String nome, String cpf) {
        this.dadosPessoais = new DadosPessoais(nome, cpf);
    }

    public Cliente(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.dadosPessoais.getNome();
    }

    public void setNome(String nome) {
        this.dadosPessoais.setNome(nome);
    }

    public String getCpf() {
        return this.dadosPessoais.getCpf();
    }

    public void setCpf(String cpf) {
        this.dadosPessoais.setCpf(cpf);
    }


}

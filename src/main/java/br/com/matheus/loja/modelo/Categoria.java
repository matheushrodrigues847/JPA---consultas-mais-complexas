package br.com.matheus.loja.modelo;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    @EmbeddedId //cahve primaria composta
    private CategoriaId id;
    public Categoria(String nome) {
        this.id = new CategoriaId(nome, "random");
    }

    public Categoria() {

    }


    public String getNome() {
        return this.id.getNome();
    }

    public void setNome(String nome) {
        this.id.setNome(nome);
    }

}

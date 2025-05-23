package br.com.creatinastore.Creatina;

import br.com.creatinastore.Categoria.Categoria;
import br.com.creatinastore.Disponibilidade.Disponibilidade;
import br.com.creatinastore.Peso.Peso;
import br.com.creatinastore.Produto.Produto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class Creatina extends Produto {
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "peso_id")
    private Peso peso;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToOne(mappedBy = "creatina", cascade = CascadeType.ALL, orphanRemoval = true)
    private Disponibilidade disponibilidade;

    public Disponibilidade getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Disponibilidade disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Peso getPeso() {
        return peso;
    }

    public void setPeso(Peso peso) {
        this.peso = peso;
    }
}


package br.com.creatinastore.Produto;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

import br.com.creatinastore.Componente.Componente;
import br.com.creatinastore.DefaultEntity.DefaultEntity;
import br.com.creatinastore.Fornecedor.Fornecedor;
import br.com.creatinastore.Marca.Marca;

@MappedSuperclass
public abstract class Produto extends DefaultEntity{

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @ManyToMany
    @JoinTable(
        name = "produto_componentes",
        joinColumns = @JoinColumn(name = "produto_id"),
        inverseJoinColumns = @JoinColumn(name = "componente_id")
    )
    private List<Componente> componentes;

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Marca getMarca() {
        return marca;
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    
}

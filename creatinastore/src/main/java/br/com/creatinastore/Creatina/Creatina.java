package br.com.creatinastore.Creatina;

import br.com.creatinastore.Peso.Peso;
import br.com.creatinastore.Produto.Produto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class Creatina extends Produto {
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "peso_id")
    private Peso peso;

    public Peso getPeso() {
        return peso;
    }

    public void setPeso(Peso peso) {
        this.peso = peso;
    }
}


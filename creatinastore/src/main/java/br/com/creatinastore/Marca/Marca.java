package br.com.creatinastore.Marca;

import br.com.creatinastore.DefaultEntity.DefaultEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "marcas")
public class Marca extends DefaultEntity {

    @Column(nullable = false, unique = true)
    private String nome;

    private String paisOrigem;

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }
}

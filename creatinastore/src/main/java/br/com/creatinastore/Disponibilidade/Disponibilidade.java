package br.com.creatinastore.Disponibilidade ;

import java.time.LocalDate;

import br.com.creatinastore.Creatina.Creatina;
import br.com.creatinastore.DefaultEntity.DefaultEntity;
import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Disponibilidade extends DefaultEntity {

    @NotNull
    private Integer quantidadeEstoque;

    @NotBlank
    private String lote;

    @NotNull
    private LocalDate validade;

    @OneToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Creatina creatina;

    //Getters and Setters
    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public Creatina getCreatina() {
        return creatina;
    }

    public void setCreatina(Creatina creatina) {
        this.creatina = creatina;
    }

}

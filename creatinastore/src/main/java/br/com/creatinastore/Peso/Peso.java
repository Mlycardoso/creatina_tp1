package br.com.creatinastore.Peso;

import java.math.BigDecimal;

import br.com.creatinastore.DefaultEntity.DefaultEntity;
import br.com.creatinastore.UnidadePeso.UnidadePeso;
import br.com.creatinastore.UnidadePeso.UnidadePesoConverter;
import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pesos")
public class Peso extends DefaultEntity{

    @NotNull
    private BigDecimal valor;

    @NotNull
    @Convert(converter = UnidadePesoConverter.class)
    private UnidadePeso unidade;

    // Getters e Setters
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public UnidadePeso getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadePeso unidade) {
        this.unidade = unidade;
    }
        
}

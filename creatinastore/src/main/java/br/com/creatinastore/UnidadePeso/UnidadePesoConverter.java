package br.com.creatinastore.UnidadePeso;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UnidadePesoConverter implements AttributeConverter<UnidadePeso, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UnidadePeso unidade) {
        return unidade == null ? null : unidade.getId();
    }

    @Override
    public UnidadePeso convertToEntityAttribute(Integer id) {
        return UnidadePeso.valueOfId(id);
    }
}


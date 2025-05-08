package br.com.creatinastore.UnidadePeso;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UnidadePeso {
    MG(1, "miligramas"),
    G(2, "gramas"),
    KG(3, "kilogramas");

    private final int id;
    private final String nomeCompleto;

    UnidadePeso(int id, String nomeCompleto) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
    }

    public int getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public static UnidadePeso valueOfId(Integer id) {
        if (id == null) return null;
        for (UnidadePeso u : values()) {
            if (u.getId() == id) return u;
        }
        return null;
    }
}


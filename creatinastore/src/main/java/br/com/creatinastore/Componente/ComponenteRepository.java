package br.com.creatinastore.Componente;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ComponenteRepository implements PanacheRepository<Componente> {
    public Componente findByNome(String nome) {
        return find("nome", nome).firstResult();
    }
}


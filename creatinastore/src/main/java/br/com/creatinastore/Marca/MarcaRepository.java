package br.com.creatinastore.Marca;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MarcaRepository implements PanacheRepository<Marca> {
    public boolean existsByNome(String nome) {
        return count("nome", nome) > 0;
    }

    public Marca findByNome(String nome) {
        return find("nome", nome).firstResult();
    }
}

package br.com.creatinastore.Creatina;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import br.com.creatinastore.Categoria.Categoria;
import br.com.creatinastore.Categoria.CategoriaRepository;
import br.com.creatinastore.Componente.Componente;
import br.com.creatinastore.Componente.ComponenteRepository;
import br.com.creatinastore.Creatina.DTO.CreatinaRequestDTO;
import br.com.creatinastore.Creatina.DTO.CreatinaResponseDTO;
import br.com.creatinastore.Disponibilidade.Disponibilidade;
import br.com.creatinastore.Disponibilidade.DisponibilidadeRepository;
import br.com.creatinastore.Fornecedor.Fornecedor;
import br.com.creatinastore.Fornecedor.FornecedorRepository;
import br.com.creatinastore.Marca.Marca;
import br.com.creatinastore.Marca.MarcaRepository;
import br.com.creatinastore.Peso.Peso;
import br.com.creatinastore.UnidadePeso.UnidadePeso;

@ApplicationScoped
public class CreatinaService {

    @Inject
    CreatinaRepository repository;

    @Inject
    MarcaRepository marcaRepository;

    @Inject
    CategoriaRepository categoriaRepository;

    @Inject
    FornecedorRepository fornecedorRepository;

    @Inject
    ComponenteRepository componenteRepository;

    @Inject
    DisponibilidadeRepository disponibilidadeRepository;

    public List<CreatinaResponseDTO> findAll() {
        return repository.listAll()
                                   .stream()
                                   .map(e -> CreatinaResponseDTO.fromEntity(e))
                                   .toList();
    }

    public CreatinaResponseDTO findById(Long id) {
        Creatina creatina = repository.findById(id);

        if(creatina == null)
            throw new NotFoundException("Creatina com ID " + id + " não encontrado.");

        return CreatinaResponseDTO.fromEntity(creatina);
    }

    @Transactional
    public CreatinaResponseDTO create(CreatinaRequestDTO dto) {
        Creatina creatina = new Creatina();

        Marca marca = marcaRepository.findByIdOptional(dto.marcaId())
        .orElseThrow(() -> new NotFoundException("Marca não encontrada"));

        Categoria categoria = categoriaRepository.findByIdOptional(dto.categoriaId())
            .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));

        List<Componente> componentes = componenteRepository.list("id in ?1", dto.componentesIds());
        if(componentes == null)
            throw new NotFoundException("Componentes não encontrados");

        Fornecedor fornecedor = fornecedorRepository.findByIdOptional(dto.fornecedorId())
            .orElseThrow(() -> new NotFoundException("Fornecedor não encontrado"));

        creatina.setNome(dto.nome());
        creatina.setPreco(dto.preco());

        Peso peso = new Peso();
        peso.setValor(dto.valorPeso());
        peso.setUnidade(UnidadePeso.valueOfId(dto.idUnidadePeso()));
        creatina.setPeso(peso);
        
        creatina.setMarca(marca);
        creatina.setCategoria(categoria);
        creatina.setFornecedor(fornecedor);
        creatina.setComponentes(componenteRepository.list("id in ?1", dto.componentesIds()));

        repository.persist(creatina);

        Disponibilidade disponibilidade = new Disponibilidade();
        disponibilidade.setCreatina(creatina);
        creatina.setDisponibilidade(disponibilidade);
        disponibilidade.setQuantidadeEstoque(dto.disponibilidade().quantidadeEstoque());
        disponibilidade.setLote(dto.disponibilidade().lote());
        disponibilidade.setValidade(dto.disponibilidade().validade());
        disponibilidade.setDataCadastro(LocalDateTime.now());
        disponibilidadeRepository.persist(disponibilidade);

        return CreatinaResponseDTO.fromEntity(creatina);
    }

    @Transactional
    public CreatinaResponseDTO update(Long id, CreatinaRequestDTO dto) {
        Creatina creatina = repository.findById(id);

        if(creatina == null)
            throw new NotFoundException("Creatina com ID " + id + " não encontrado.");

        if(creatina != null){
            creatina.setNome(dto.nome());
            creatina.setPreco(dto.preco());
            creatina.setMarca(marcaRepository.findById(dto.marcaId()));
            creatina.setCategoria(categoriaRepository.findById(dto.categoriaId()));
            creatina.setFornecedor(fornecedorRepository.findById(dto.fornecedorId()));

            creatina.setComponentes(componenteRepository.list("id in ?1", dto.componentesIds()));
        }

        return CreatinaResponseDTO.fromEntity(creatina);
    }

    @Transactional
    public boolean delete(Long id) {
        Creatina creatina = repository.findById(id);

        if(creatina == null)
            throw new NotFoundException("Creatina com ID " + id + " não encontrado.");
            
        return repository.deleteById(id);
    }
}

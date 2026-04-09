
package dicasdev.model.services;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import dicasdev.model.domain.Dica;
import dicasdev.model.repositories.IDicasRepository;

public class DicasService {

    private static final Logger logger = Logger.getLogger(DicasService.class.getName());
    
    private final IDicasRepository repository;
    
    public DicasService(IDicasRepository repository) {
        this.repository = repository;
    }
    
    public void salvar(Dica dica) {
        validarDica(dica);
        repository.criar(dica);
    }
    
    public void excluir(Integer id) {
        validarId(id);
        try {
            repository.apagar(id);
        } catch (Exception e) {
            logger.severe("Erro ao excluir dica: " + e.getMessage());
        }
        
    }
    
    public Dica buscarPorId(Integer id) {
        return repository.buscarPorId(id);
    }
    
    public Dica atualizar(Dica dica) {
        validarDica(dica);
        Dica dicaExistente = repository.buscarPorId(dica.id);
        if (Objects.isNull(dicaExistente)) {
            throw new IllegalArgumentException("Dica não encontrada");
        }
        return repository.atualizar(dica);
    }
    
    public List<Dica> bucarTodas() {
        return repository.buscarTodas();
    }

    private void validarDica(Dica dica) {
        if (Objects.isNull(dica)) {
            throw new IllegalArgumentException("Dica não pode ser nula");
        }
        if (Objects.isNull(dica.titulo) || dica.titulo.isEmpty()) {
            throw new IllegalArgumentException("Título da dica não pode ser nulo ou vazio");
        }
        if (Objects.isNull(dica.descricao) 
            || dica.descricao.isEmpty() 
            || dica.descricao.length() > 500) {
            throw new IllegalArgumentException("Descrição da dica não pode ser nula ou vazia");
        }
    }
    private void validarId(Integer id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser maior que zero");
        }
    }
            
}

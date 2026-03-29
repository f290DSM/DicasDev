
package dicasdev.model.services;

import java.util.List;

import dicasdev.model.domain.Dica;
import dicasdev.model.repositories.IDicasRepository;

public class DicasService {
    
    private final IDicasRepository repository;
    
    public DicasService(IDicasRepository repository) {
        this.repository = repository;
    }
    
    public void salvar(Dica dica) {
        //TODO: Implementar validação
        repository.criar(dica);
    }
    
    public void excluir(Integer id) {
        //TODO: Implementar validação
        repository.apagar(id);
    }
    
    public Dica buscarPorId(Integer id) {
        //TODO: Implementar validação
        return repository.buscarPorId(id);
    }
    
    public Dica atualizar(Dica dica) {
        //TODO: Implementar validação
        return repository.atualizar(dica);
    }
    
    public List<Dica> bucarTodas() {
        return repository.buscarTodas();
    }
            
}

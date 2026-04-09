package dicasdev.controller;

import java.util.List;

import dicasdev.model.domain.Dica;
import dicasdev.model.services.DicasService;

public class DicasController {
    private final DicasService service;
    
    public DicasController(DicasService service) {
        this.service = service;
    }
    
    public void salvar(Dica dica) {
        service.salvar(dica);
    }
    
    public void excluir(Integer id) {
        service.excluir(id);
    }
    
    public Dica buscarPorId(Integer id) {
        return service.buscarPorId(id);
    }
    
    public Dica atualizar(Dica dica) {
        return service.atualizar(dica);
    }
    
    public List<Dica> bucarTodas() {
        return service.bucarTodas();
    }
}

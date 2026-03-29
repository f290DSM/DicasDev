package dicasdev.model.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dicasdev.model.domain.Dica;
import dicasdev.model.repositories.IDicasRepository;

public class EmMemoriaDicasRepository implements IDicasRepository {
    
    private List<Dica> dicas = new ArrayList<>();

    @Override
    public Dica criar(Dica dica) {
        dicas.add(dica);
        return dica;
    }

    @Override
    public void apagar(Integer id) {
        Dica dica = dicas.stream()
                .filter(d -> Objects.equals(d.id, id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Dica não encontrada"));
        dicas.remove(dica);
    }

    @Override
    public List<Dica> buscarTodas() {
        return dicas;
    }

    @Override
    public Dica buscarPorId(Integer id) {
        return dicas.stream()
                .filter(d -> d.id == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Dica não encontrada"));
    }

    @Override
    public Dica atualizar(Dica dica) {
        Dica dicaExistente = buscarPorId(dica.id);
        if (Objects.nonNull(dicaExistente)) {
            dicaExistente.titulo = dica.titulo;
            dicaExistente.descricao = dica.descricao;
        }
        return dicaExistente;
    }
    
}

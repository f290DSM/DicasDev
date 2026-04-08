package dicasdev.model.repositories;

import java.util.List;

import dicasdev.model.domain.Dica;


public interface IDicasRepository {
    Dica criar(Dica dica);
    void apagar(Integer id);
    List<Dica> buscarTodas();
    Dica buscarPorId(Integer id);
    Dica atualizar(Dica dica);
}

package dicasdev.model.repositories.impl;

import java.util.List;

import dicasdev.model.dao.DicasDAO;
import dicasdev.model.domain.Dica;
import dicasdev.model.repositories.IDicasRepository;

public class MySqlDicasRepository implements IDicasRepository {

    private DicasDAO dao;

    public MySqlDicasRepository(DicasDAO dao) {
        this.dao = dao;
    }

    @Override
    public Dica criar(Dica dica) {
        return dao.criar(dica);
    }

    @Override
    public void apagar(Integer id) throws Exception {
        dao.apagar(id);
    }

    @Override
    public List<Dica> buscarTodas() {
        return dao.buscarTodas();
    }

    @Override
    public Dica buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    @Override
    public Dica atualizar(Dica dica) {
        return dao.atualizar(dica);
    }

}

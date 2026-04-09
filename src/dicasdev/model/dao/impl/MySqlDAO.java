package dicasdev.model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import dicasdev.model.dao.DicasDAO;
import dicasdev.model.domain.Dica;

public class MySqlDAO implements DicasDAO {

    /**
     * Conexão com o banco de dados via injeção de dependência
     */
    private final Connection connection;

    /**
     * Logger para registrar eventos e erros
     */
    private static final Logger logger = Logger.getLogger(MySqlDAO.class.getName());

    public MySqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Dica criar(Dica dica) {
        logger.info("Criando dica: " + dica);
        final String query = "INSERT INTO fatec.dicas (titulo, descricao) VALUES ('%s', '%s')";

        // Uso de TryWithResources para garantir que o Statement seja fechado
        try (Statement stm = connection.createStatement()) {
            stm.execute(String.format(query, dica.titulo, dica.descricao));
        } catch (Exception e) {
            logger.severe("Erro ao criar dica: " + e.getMessage());
            return null;
        }
        return null;
    }

    @Override
    public void apagar(Integer id) throws Exception {
        logger.info("Apagando dica com id: " + id);
        String query = "DELETE FROM fatec.dicas WHERE id = " + id;
        try (Statement stm = connection.createStatement()) {
            stm.execute(query);
            logger.info("Dica excluída com sucesso. ID: " + id);
        } catch (Exception e) {
            logger.severe("Erro ao apagar dica: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Dica> buscarTodas() {
        List<Dica> dicas = new ArrayList<>();
        String query = "SELECT id, titulo, descricao FROM fatec.dicas;";
        try (Statement stm = connection.createStatement();
                ResultSet rst = stm.executeQuery(query)) {
            while (rst.next()) {
                var dica = new Dica();
                dica.id = rst.getInt("id");
                dica.titulo = rst.getString("titulo");
                dica.descricao = rst.getString("descricao");
                dicas.add(dica);
            }
        } catch (Exception e) {
            logger.severe("Erro ao buscar dicas: " + e.getMessage());
        }
        return dicas;
    }

    @Override
    public Dica buscarPorId(Integer id) {
        Dica dica = null;
        String query = "SELECT id, titulo, descricao FROM fatec.dicas WHERE id = " + id + ";";
        try (Statement stm = connection.createStatement();
                ResultSet rst = stm.executeQuery(query)) {
            if (rst.next()) {
                dica = new Dica();
                dica.id = rst.getInt("id");
                dica.titulo = rst.getString("titulo");
                dica.descricao = rst.getString("descricao");
            }
        } catch (Exception e) {
            logger.severe("Erro ao buscar dica: " + e.getMessage());
        }
        return dica;
    }

    @Override
    public Dica atualizar(Dica dica) {
        String queryTemplate = "UPDATE fatec.dicas" + 
        " SET titulo='%s', descricao='%s' WHERE id=%d";
        try (Statement stm = connection.createStatement()) {
            stm.execute(String.format(
                queryTemplate, 
                dica.titulo, 
                dica.descricao, 
                dica.id));
        } catch (Exception e) {
            logger.severe("Erro ao atualizar dica: " + e.getMessage());
        }
        return dica;
    }

}

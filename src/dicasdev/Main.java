package dicasdev;

import dicasdev.model.dao.DicasDAO;
import dicasdev.model.dao.impl.MySqlDAO;
import dicasdev.model.domain.Dica;
import dicasdev.model.factories.ConexaoFactory;
import dicasdev.model.repositories.IDicasRepository;
import dicasdev.model.repositories.impl.MySqlDicasRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        // Criar dica
        var dica = new Dica();
        dica.id = 1;
        dica.descricao = "Utilizar Java 25";
        dica.titulo = "Dica de ouro Java";
        
        // Criar repository
        Connection connection =  ConexaoFactory.getConexao();
        DicasDAO dao = new MySqlDAO(connection);
        IDicasRepository repository = new MySqlDicasRepository(dao);

        // Criar dica
        repository.criar(dica);

        // TODO: Faça um select no banco de dados para verificar se a dica foi criada

        // // Buscar dica
        // Dica dicaEncontrada = repository.buscarPorId(1);
        // System.out.println(dicaEncontrada);
        // List<Dica> dicas = repository.buscarTodas();
        // System.out.println(dicas);
        
        // // Alterar dica
        // dica.descricao = "Sempre aplique o SOLID";
        // Dica dicaAlterada = repository.buscarPorId(1);
        // System.out.println(dicaAlterada);
        
        // // Apagar dica
        // repository.apagar(1);
        // dicas = repository.buscarTodas();
        // System.out.println(dicas);

        //TODO: Implementar testes com Camada de Serviços
    }
    
}

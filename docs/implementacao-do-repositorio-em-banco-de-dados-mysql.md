# Implementação do repositório em banco de dados MySQL

O Repositório MySQL será implementado na classe `MySqlDicasRepository` que implementa a interface `IDicasRepository`.

Ele fará uso da classe `MySqlDAO` que implementa a interface `DicasDAO`.

Utilizaremos esta estrutura para que o repositório não faça a conexão com o banco de dados diretamente, mas sim através da classe `MySqlDAO`.

## DAO - MySQL

A classe `MySqlDAO` será implementada na classe `MySqlDAO` que implementa a interface `DicasDAO`.

1. No pacote `dao.impl` crie a classe `MySqlDAO` que implementa a interface `DicasDAO`.

> Fique atento aos imports das classes utilizadas no seu projeto local, elas podem não coincidir com as do exemplo.

```java
package dicasdev.model.dao.impl;

import java.sql.Connection;
import java.sql.Statement;
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
    public void apagar(Integer id) {
        logger.info("Apagando dica com id: " + id);
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apagar'");
    }

    @Override
    public List<Dica> buscarTodas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodas'");
    }

    @Override
    public Dica buscarPorId(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    @Override
    public Dica atualizar(Dica dica) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }    
}
```

> Faremos a impleentação dos demais em sala de aula, logo mais.

## MySQL Repository

A classe `MySqlDicasRepository` será implementada na classe `MySqlDicasRepository` que implementa a interface `IDicasRepository`.

1. No pacote `repositories.impl` crie a classe `MySqlDicasRepository` que implementa a interface `IDicasRepository`.

> Fique atento aos imports das classes utilizadas no seu projeto local, elas podem não coincidir com as do exemplo.

```java
package dicasdev.model.repositories.impl;

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
        // Estamos repassando a responsabilidade para o DAO
        return dao.criar(dica);
    }

    @Override
    public void apagar(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apagar'");
    }

    @Override
    public List<Dica> buscarTodas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodas'");
    }

    @Override
    public Dica buscarPorId(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    @Override
    public Dica atualizar(Dica dica) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

}
```

> Faremos a impleentação dos demais em sala de aula, logo mais.

## Próximos Passos

1. Implementar o método `apagar` na classe `MySqlDicasRepository`
2. Implementar o método `buscarTodas` na classe `MySqlDicasRepository`
3. Implementar o método `buscarPorId` na classe `MySqlDicasRepository`
4. Implementar o método `atualizar` na classe `MySqlDicasRepository`

## Testando a conexão com o banco de dados

Para testar a conexão com o banco de dados, você pode criar um método de teste na classe `MySqlDicasRepository`:

## Ajuste o metodo main da classe `Main` para testar a conexão com o banco de dados

```java
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
    }
    
}

```

> Execute o método `main` e verifique se a dica foi criada no banco de dados.

> Lembre-se de validar as credenciais de acesso ao banco de dados no arquivo `src/dicasdev/model/factories/ConexaoFactory.java`.


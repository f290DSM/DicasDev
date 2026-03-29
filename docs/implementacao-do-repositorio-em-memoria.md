## Implementando o repositório EmMemoriaDicasRepository

[Voltar](../README.md#implementacao-do-repositorio-em-memoria.md)

### Implementação

Utilizando a interface genérica do repositório:
- Implemente a classe `EmMemoriaDicasRepository` na pasta `model/repositories`.

```java
// Inclua o código na classe `repositories/impl/EmMemoriaDicasRepository.java`
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
```

> Observe que a classe `EmMemoriaDicasRepository` implementa a interface `IDicasRepository` e utiliza uma lista para armazenar as dicas em memória. Ela fará paralelo com a classe `MySqlDicasRepository` que implementará a interface `IDicasRepository` e utilizará um banco de dados MySQL para armazenar as dicas. Essa abordagem permite que você troque facilmente entre diferentes implementações de repositório sem alterar o código que usa a interface.

## Testando a implementação do repositório em memória

Partindo do principio de responsabilidade única, vamos testar a implementação do repositório em memória.

Utilizaremos o método main da classe Main para testar a implementação do repositório em memória. Posteriormente, vamos criar um teste unitário para validar a implementação.

1. Inclua o código abaixo no método main da classe Main:

```java
package dicasdev;

import dicasdev.model.domain.Dica;
import dicasdev.model.factories.ConexaoFactory;
import dicasdev.model.repositories.IDicasRepository;
import dicasdev.model.repositories.impl.EmMemoriaDicasRepository;
import dicasdev.model.repositories.impl.MySqlDicasRepository;
import dicasdev.model.services.DicasService;

import java.sql.ResultSet;
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
        IDicasRepository repository = new EmMemoriaDicasRepository();

        // Criar dica
        repository.criar(dica);

        // Buscar dica
        Dica dicaEncontrada = repository.buscarPorId(1);
        System.out.println(dicaEncontrada);
        List<Dica> dicas = repository.buscarTodas();
        System.out.println(dicas);
        
        // Alterar dica
        dica.descricao = "Sempre aplique o SOLID";
        Dica dicaAlterada = repository.buscarPorId(1);
        System.out.println(dicaAlterada);
        
        // Apagar dica
        repository.apagar(1);
        dicas = repository.buscarTodas();
        System.out.println(dicas);
    }
    
}

```

2. Execute o método main da classe Main e verifique se as dicas foram criadas, buscadas, alteradas e apagadas corretamente.

> Observe que as dicas foram criadas, buscadas, alteradas e apagadas corretamente, de forma isolada, sem dependencia alguma de qualquer outro componente.


# Implementação do repositório em banco de dados MySQL

[Implementação do repositório em banco de dados MySQL](docs/implementacao-do-repositorio-em-banco-de-dados-mysql.md)


# Implementação de camada de serviços

A camada de serviços é responsável por conter a lógica de negócio da aplicação, garantindo que ela esteja escrita de acordo com os princípios SOLID, como o princípio de responsabilidade única (Single Responsibility Principle - SRP), que diz que cada classe deve ter uma e apenas uma razão para mudar. Ela também deve ser agnóstica ao tipo de repositório utilizado, pois recebe uma instância do repositório através de injeção de dependência. Por fim, ela deve ser facilmente testável e extensível.

> Segurem a emoção para o SOLID e o princípio SRP, serão assuntos das próximas aulas.

Em resumo, a camada de serviços é responsável por:
- Orquestrar as operações do repositório;
- Validar as regras de negócio;
- Receber uma instância do repositório através de injeção de dependência;
- Executar as operações do repositório;

## Implementação do serviço

1. Crie a classe `DicasService` na pasta `src/main/java/com/example/dicasdev/service/`;

```java
package dicasdev.model.services;

public class DicasService {
    // Demais atributos e métodos serão implementados posteriormente
}
```

2. A classe deve receber uma instância do repositório através de injeção de dependência;

```java
package dicasdev.model.services;

import dicasdev.model.repositories.IDicasRepository;

public class DicasService {
    private final IDicasRepository repository;
    
    public DicasService(IDicasRepository repository) {
        this.repository = repository;
    }
}
```

> Observe que a classe `DicasService` recebe uma instância do repositório através de injeção de dependência, o que a torna agnóstica ao tipo de repositório utilizado.

3. A classe deve conter os métodos de negócio da aplicação;

```java
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

```

# Implementação do repositório em memória

[Implementação do repositório em memória](../docs/implementacao-do-repositorio-em-memoria.md)

# Desafio Júnior Itaú

Esse repositório foi criado para guardar minha resolução de um desafio de programação proposto pelo Itaú Unibanco. Além da definição do desafio, também usei o repositório da Angelica Weiler para me guiar durante a codificação.



### Referência
Segue os links das referências citadas acima:
 - [Repositório da Angélica Weiler](https://github.com/angelicaweiler/transacao-api)
 - [Descrição do desafio](https://github.com/rafaellins-itau/desafio-itau-vaga-99-junior)

## Transação API
Esta é uma API que usa os conceitos de REST para salvar transações em memória e calcular as estatísticas das transações feitas nos últimos 60 segundos. 

Foi desenvolvida com Java SpringBoot e Gradle, Swagger para a documentação e actuator para verificação da saúde da aplicação.
## Requisitos
Para rodar essa aplicação você precisa de:

- JDK 21 ou superior
- Gradle 8.13 ou superior
- Git
- Docker (opcional)
## Configuração do projeto

Após clonar o projeto para sua máquina:

1. Compile o projeto

```bash
 gradle build
```
2. Execute o projeto
```bash
 ./gradlew bootRun
```
3. Executar em container (opcional)

3.1 Crie a imagem Docker
```bash
docker build -t api-transacoes 
```

3.2 Execute o container
```bash
docker run -p 8080:8080 api-transacoes
```
## Documentação da API

#### Adicionar uma transação

```http
  POST /transacao
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `value` | `Double` | **Obrigatório**. Não pode ser menor que 0 |
| `dateTime` | `OffSetDateTime` | **Obrigatório**. Não pode ser uma data no futuro |

#### Remover todas as transações

```http
  DELETE /transacao
```

#### Obter estatísticas das transações adicionadas

```http
  GET /estatistica
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `searchInterval` | `Integer` | **Opcional**. O valor padrão é 60 segundos |

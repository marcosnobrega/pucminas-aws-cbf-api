## pucminas-aws-cbf-api
Repositório do projeto final da disciplina de APIs e Web Services da pós em Arquitetura de Software Distribuído da PUC Minas

## Objetivo do projeto

Implementação prática de uma API REST.

## Descrição do Projeto

A empresa que você trabalha foi contratada pela CBF (Confederação Brasileira de Futebol)
para a criação de uma API que auxiliará no gerenciamento de sua principal competição anual: O
Campeonato Brasileiro. Portanto, ficou combinado que, na primeira Sprint, os seguintes
recursos deverão ser implementados: Times, Jogadores, Transferências, Torneios e Partidas.

## Alunos

* Marcos Costa Junior - Matricula: 139280

## Stack

* Linguagem JAVA 8 + Spring Framework (Spring Boot, Spring Web, Spring Data) + Lombok 
* Banco de dados MySQL
* RabbitMQ 

A utilização do Spring Framework e do Lombok acelera o desenvolvimento de APIs REST,
removendo a necessidade de realizar diversas configurações e a repetição de código para 
criar getters, setters e construtores. Além disso o Spring também fornece bibliotecas 
facilmente configuráveis para acesso a bancos de dados.

## Sobre a execução e documentação

É possível executar o projeto utilizando docker, através do arquivo em cbf-api/docker/docker-compose.yml

Este arquivo docker-compose irá criar 2 containers, um com a aplicação e outro com o banco de dados MySQL.

A aplicação executa na porta 8080 e o container da aplicação expõe esta porta. 
Após iniciar os containers, será possível acessar a aplicação em http://localhost:8080

A API foi documentada seguindo especificação OpenAPI e foi utilizada a ferramenta Swagger 
para gerar uma visualização gráfica dos recursos da API com possibilizada de interação e consumo.

## Como executar o projeto?

### Build

Antes de executar a aplicação com o docker é necessário executar o processo de build da aplicação, 
pois o container da aplicação depende da geração do .jar durante o build.

Para isso, a partir da pasta cbf-api, execute a task do gradle abaixo:

`./gradlew build -x test`

Após a execução do comando acima, o artefato .jar deverá ser gerado na pasta cbf-api/build/libs/

### Iniciando a aplicação com docker-compose

Após ter realizado o build do projeto, a partir da pasta cbf-api, execute o comando abaixo:

`docker-compose -f docker/docker-compose.yml up --build -d`

Após a execução do comando acima, se tudo ocorreu corretamente, a API deve estar disponível em http://localhost:8080

E a documentação da API, assim como seus recursos podem ser acessados em http://localhost:8080/swagger-ui.html

### Parar a execução com o docker-compose

Para parar a aplicação, execute o comando abaixo:

`docker-compose -f docker/docker-compose.yml down`

### Documentação da API no padrão OpenAPI

O arquivo da documentação da API está localizado no arquivo cbf-api-doc-openapi.yaml na raiz do repositório.

### Videos de apresentação

* [Vídeo parte 1](https://github.com/marcosnobrega/pucminas-aws-cbf-api/blob/main/videos-demonstracao/demo-parte-1.mp4)
* [Vídeo parte 2](https://github.com/marcosnobrega/pucminas-aws-cbf-api/blob/main/videos-demonstracao/demo-parte-2.mp4)
* [Vídeo parte 3](https://github.com/marcosnobrega/pucminas-aws-cbf-api/blob/main/videos-demonstracao/demo-parte-3.mp4)
* [Vídeo parte 4](https://github.com/marcosnobrega/pucminas-aws-cbf-api/blob/main/videos-demonstracao/demo-parte-4.mp4)
* [Vídeo parte 5](https://github.com/marcosnobrega/pucminas-aws-cbf-api/blob/main/videos-demonstracao/demo-parte-5.mp4)
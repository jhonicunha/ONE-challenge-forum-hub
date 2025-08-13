# Challenge Forum Hub - Oracle Next Education

FórumHub é uma API RESTful desenvolvida como parte do Challenge Back End da Alura. O projeto simula o back-end de um fórum de discussões, permitindo a criação, consulta, atualização e exclusão de tópicos. A API é construída com foco em boas práticas de desenvolvimento, incluindo um modelo de dados relacional, validações de negócio e um sistema de autenticação e autorização seguro utilizando tokens JWT.

Status do Projeto: Concluído (CRUD de Tópicos e Autenticação).

## Tecnologias Utilizadas

Linguagem: Java 17

Framework: Spring Boot 3.5.4

Segurança: Spring Security

Persistência de Dados: Spring Data JPA / Hibernate

Banco de Dados: MySQL 8

Gerenciador de Dependências: Maven

Migrations: Flyway

## Principais Dependências

Spring Web: Para a construção de endpoints REST.

Spring Data JPA: Para a camada de persistência e comunicação com o banco de dados.

Spring Security: Para implementar a autenticação e autorização.

Validation: Para validações dos dados de entrada (DTOs).

Lombok: Para reduzir código boilerplate nas entidades e DTOs.

MySQL Driver: Conector JDBC para o banco de dados MySQL.

Flyway Migration: Para versionamento e controle do schema do banco de dados.

Auth0 Java JWT: Para geração e validação de tokens JWT (JSON Web Tokens).

## Funcionalidades

A API FórumHub possui as seguintes funcionalidades implementadas:

Segurança e Autenticação
Registro de Usuário: Endpoint público para criação de novos usuários. As senhas são armazenadas de forma segura utilizando o algoritmo BCrypt.

Autenticação de Usuário: Endpoint público para login. Em caso de sucesso, retorna um token JWT para ser utilizado nas requisições subsequentes.

Autorização via Token JWT: Todas as rotas, com exceção de login e registro, são protegidas. O acesso é concedido apenas mediante a apresentação de um token JWT válido no cabeçalho Authorization.

Arquitetura Stateless: A autenticação é stateless, não dependendo de sessões.

## API de Tópicos (CRUD Completo)

Cadastrar um Tópico (POST /topicos): Permite a um usuário autenticado criar um novo tópico, associando-o ao seu usuário e a um curso.

Listar Tópicos (GET /topicos): Retorna uma lista paginada e ordenada de todos os tópicos cadastrados.

Detalhar um Tópico (GET /topicos/{id}): Exibe as informações detalhadas de um único tópico com base no seu ID.

Atualizar um Tópico (PUT /topicos/{id}): Permite a um usuário autenticado atualizar o título e a mensagem de um tópico existente.

Excluir um Tópico (DELETE /topicos/{id}): Permite a um usuário autenticado remover um tópico do sistema.

## Configuração do Ambiente

Para executar este projeto localmente, siga os passos abaixo:

Pré-requisitos:

Java JDK 17 ou superior.

Maven 3.8 ou superior.

MySQL 8 ou superior.

Clone o repositório:

Bash

`git clone <url-do-seu-repositorio>`

`cd forumhub`

## Configure o Banco de Dados:

Crie um schema no seu MySQL com o nome forumhub.

Abra o arquivo src/main/resources/application.properties.

Altere as propriedades spring.datasource.username e spring.datasource.password para as suas credenciais do MySQL.

Execute a aplicação:

Pela linha de comando, na raiz do projeto, execute:

`mvn spring-boot:run`

A aplicação estará disponível em http://localhost:8080. 

O Flyway executará as migrations e criará todas as tabelas automaticamente na primeira inicialização.

## Como Utilizar a API

Utilize uma ferramenta de cliente API como Postman ou Insomnia.

1. Criar um Novo Usuário
   Endpoint: POST /usuarios

Body:

JSON

{
"email": "seu_email@exemplo.com",
"senha": "sua_senha_123"
}
Resposta: 200 OK

2. Efetuar Login para Obter o Token
   Endpoint: POST /login

Body:

JSON

{
"email": "seu_email@exemplo.com",
"senha": "sua_senha_123"
}
Resposta: 200 OK com o token JWT no corpo.

JSON

{
"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}

3. Acessando Rotas Protegidas
   Para todas as outras requisições, você deve incluir o token JWT no cabeçalho de autorização.

Header: Authorization

Value: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9... (substitua pelo token que você recebeu).

Endpoints da API
A seguir, a lista detalhada dos endpoints disponíveis. Todos, exceto /login e /usuarios, requerem autenticação.

POST /usuarios

Descrição: Cria um novo usuário.

Autenticação: Não requerida.

POST /login

Descrição: Autentica um usuário e retorna um token JWT.

Autenticação: Não requerida.

POST /topicos

Descrição: Cria um novo tópico.

Autenticação: Requerida (Bearer Token).

Body de Exemplo:

JSON

{
"titulo": "Dúvida sobre Spring Data JPA",
"mensagem": "Como funciona o método save()?",
"idAutor": 1,
"idCurso": 1
}
Resposta de Sucesso: 201 Created

GET /topicos

Descrição: Lista todos os tópicos de forma paginada.

Autenticação: Requerida (Bearer Token).

Parâmetros de URL (Opcionais): ?size=5, ?page=1, ?sort=titulo,desc

GET /topicos/{id}

Descrição: Detalha um tópico específico pelo seu ID.

Autenticação: Requerida (Bearer Token).

Resposta de Sucesso: 200 OK

PUT /topicos/{id}

Descrição: Atualiza o título e/ou a mensagem de um tópico existente.

Autenticação: Requerida (Bearer Token).

Body de Exemplo:

JSON

{
"titulo": "Novo Título Atualizado",
"mensagem": "Nova mensagem do tópico."
}
Resposta de Sucesso: 200 OK

DELETE /topicos/{id}

Descrição: Exclui um tópico do banco de dados.

Autenticação: Requerida (Bearer Token).

Resposta de Sucesso: 204 No Content
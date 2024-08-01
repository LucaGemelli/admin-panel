# Admin Panel Backend

Este é o backend para o projeto de painel administrativo, desenvolvido com Spring Boot. A aplicação é construída usando Java 21 e oferece uma API para gerenciar usuários e permissões, além de integração com RabbitMQ para mensageria e PostgreSQL como banco de dados.

## Tecnologias Utilizadas

- **Java 21**: Linguagem de programação usada no desenvolvimento da aplicação.
- **Spring Boot**: Framework utilizado para criar a aplicação web e expor endpoints REST.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenar dados.
- **RabbitMQ**: Sistema de mensageria usado para comunicação assíncrona entre componentes.
- **Docker**: Plataforma para containerização de aplicações.
- **Maven**: Gerenciador de dependências e build.

## Configuração

### Variáveis de Ambiente

Certifique-se de definir as seguintes variáveis de ambiente para a aplicação Spring Boot:

- `SPRING_DATASOURCE_URL`: URL de conexão com o banco de dados PostgreSQL.
- `SPRING_DATASOURCE_USERNAME`: Nome de usuário para conexão com o banco de dados PostgreSQL.
- `SPRING_DATASOURCE_PASSWORD`: Senha para conexão com o banco de dados PostgreSQL.
- `RABBITMQ_HOST`: Endereço do servidor RabbitMQ.
- `RABBITMQ_PORT`: Porta do servidor RabbitMQ.
- `RABBITMQ_USERNAME`: Nome de usuário para autenticação no RabbitMQ.
- `RABBITMQ_PASSWORD`: Senha para autenticação no RabbitMQ.

### Executando com Docker

Para rodar o backend usando Docker, você pode utilizar o seguinte comando para criar e iniciar os containers:
```bash
docker-compose up
```
Lembrando o docker neste projeto é somente para o DB e o Rabbit o sistema deve ser rodado manualmente
junto ao front para testes locais.


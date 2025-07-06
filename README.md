# Cibus — Sistema de Entrega de Comida (Exercício Concluído)

### Contexto

Este projeto é um **exercício proposto na internet**, com o objetivo de simular o desenvolvimento de uma aplicação de entrega de comida chamada **Cibus**, voltada para cidades pequenas do interior do Brasil.

> ✅ **Este exercício foi completamente implementado com sucesso**, incluindo todos os requisitos propostos.

### Regras de Negócio

- Aceitamos apenas pagamentos com **Cartão de Crédito**, **Cartão de Débito**, **Vale Refeição** ou **PIX**.
- O próprio restaurante é responsável pela entrega dos pedidos.
- Existem três perfis de usuários:
    - **Cliente**: realiza pedidos.
    - **Dono do restaurante**: gerencia dados do restaurante e altera status de pedidos.
    - **Administrador do sistema**: gerencia dados básicos e aprova restaurantes.

### Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA**
- **Bean Validation**
- **Flyway DB**
- **MySQL 8+**
- **Maven 3+**
- **Docker Compose** para infraestrutura
- **Coleções Postman** para testes

### Funcionalidades Implementadas

- CRUD completo de **restaurantes**
    - Listagem, detalhamento, criação, atualização e exclusão
- CRUD completo de **formas de pagamento**
    - Incluindo os tipos: cartão de crédito, débito, vale-refeição e PIX
- Relatório: **Quantidade de restaurantes por tipo de cozinha**
    - Inclusão de tipos com zero restaurantes
- Cadastro e listagem de **tipos de cozinha**
- Mapeamento de entidades com JPA, validações e integração com banco de dados
- Scripts SQL de criação e carga inicial para facilitar testes locais

### Observações

- Este projeto foi desenvolvido com fins educacionais, como forma de consolidar conhecimentos em Java, Spring Boot, JPA e boas práticas de API REST.
- A estrutura foi organizada em **camadas (controller, domain, repository, dto)**, com uso de boas práticas como DTOs de entrada/saída e versionamento lógico.
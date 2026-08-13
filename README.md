# 🏥 Davila API — Sistema de Gestão e Segurança REST

> API RESTful desenvolvida com **Java 17** e **Spring Boot 3**, com foco em boas práticas de arquitetura, validações, persistência de dados em **PostgreSQL** e controle de acesso com **Spring Security**.

---

## 🚀 Tecnologias & Ferramentas

* **Linguagem:** Java 17
* **Framework Principal:** Spring Boot 3
* **Segurança:** Spring Security
* **Persistência & ORM:** Spring Data JPA / Hibernate
* **Banco de Dados:** PostgreSQL
* **Produtividade:** Lombok
* **Gerenciador de Dependências:** Maven

---
## 🚀 Construindo o davilaAPI: Desafios, Arquitetura e Aprendizados no Desenvolvimento Backend com Spring Boot
Desenvolver uma API REST do zero é sempre uma jornada de aprendizado intenso. Recentemente, finalizei a primeira versão do davilaAPI, uma aplicação voltada para a gestão e agendamento de consultas médicas, desenvolvida com Java 17, Spring Boot 3 e PostgreSQL.

O objetivo principal deste projeto foi ir além de um CRUD básico, aplicando boas práticas de arquitetura de software, isolamento de responsabilidades e garantindo que a aplicação estivesse pronta para ambientes de produção.
---

## 🛠️ Arquitetura e Boas Práticas Utilizadas
Durante a estruturação da API, foquei em garantir que o código fosse escalável e de fácil manutenção:

Padrão DTO (Data Transfer Object): Garanti o desacoplamento entre as entidades de domínio da aplicação e as requisições/respostas HTTP, evitando a exposição desnecessária da estrutura do banco de dados.

Validações de Entrada: Uso do jakarta.validation (@Valid, @NotNull, @NotBlank) para garantir a integridade das informações antes que elas atinjam a camada de serviço.

Tratamento Global de Exceções: Implementação de um @RestControllerAdvice para capturar falhas na aplicação e retornar respostas HTTP claras e padronizadas, sem vazar stack traces sensíveis para o cliente.

Segurança: Configuração das correntes de filtros do Spring Security para gerenciar a autorização dos endpoints.


---

## 🎯 Dificuldades Encontradas e Como Foram Superadas
Nenhum projeto real é feito sem percalços pelo caminho. Durante o desenvolvimento e preparação para o deploy, enfrentei alguns desafios técnicos marcantes:

Gestão de Credenciais e Segurança no Deploy:
Um dos maiores aprendizados foi entender como proteger dados sensíveis (como credenciais de banco de dados e segredos JWT) sem deixá-los expostos no controle de versão (Git). A solução foi desacoplar as configurações sensíveis utilizando Variáveis de Ambiente (${DATASOURCE_PASSWORD}) injetadas em tempo de execução, mantendo o repositório público 100% seguro.

Evolução e Controle de Migrations do Banco de Dados:
Lidar com a evolução do schema no PostgreSQL exigiu cuidado. Compreender as diferenças entre o gerenciamento automático via Hibernate (ddl-auto) e ferramentas de migração como o Flyway trouxe uma visão mais crítica sobre como manter a consistência do banco de dados entre os ambientes de desenvolvimento e produção.

Incompatibilidade de Autenticação no Git via Terminal:
Durante a integração com o GitHub, deparei-me com as recentes exigências de segurança na autenticação do Git via linha de comando no macOS, substituindo senhas tradicionais por Personal Access Tokens (PAT) e configurando o gerenciamento de credenciais do sistema nativo para automatizar o fluxo.

---

## 🧠 Arquitetura & Padrões Adotados

* **Padrão DTO (Data Transfer Object):** Isolamento da camada de modelo/entidade nas requisições e respostas HTTP.
* **Camada de Validação:** Regras de negócio e validações com `jakarta.validation` (`@Valid`, `@NotNull`, `@NotBlank`).
* **Tratamento Global de Exceções:** Implementação de `@RestControllerAdvice` para respostas HTTP padronizadas e limpas sem vazar *stack trace*.
* **Segurança:** Bloqueio e autorização de requisições gerenciados pela cadeia de filtros do Spring Security.

---

## 🛠️ Como Executar o Projeto Localmente

### Pré-requisitos
* **Java 17** instalado.
* **PostgreSQL** instalado e em execução.
* **Maven** instalado (ou utilize o wrapper `./mvnw`).

### Passos:

1. **Clonar o repositório:**
   ```bash
   git clone [https://github.com/yannickdavila07/davilaAPI.git](https://github.com/yannickdavila07/davilaAPI.git)
   cd davilaAPI

2. **Adicionar as variáveis de ambiente:**
   <img width="1992" height="836" alt="image" src="https://github.com/user-attachments/assets/8cc8038b-791d-4e01-821f-a1620a50660b" />

   - Mude as variaveis de ambiente e coloque as configuracao do seu banco de dados em postgresql.

3. **Conecte ao front-end:**

   - Apos as configurações na arquivo application.properties agora basta configurar e conectar ao fron-end OBS: Não se esqueca de configurar o Cors

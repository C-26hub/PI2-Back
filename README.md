
# **Ecosy**

O **Ecosy** é uma plataforma digital para a gestão, distribuição e rastreabilidade do programa de aquisição de **sementes crioulas** em Pernambuco.

O sistema conecta gestores públicos, técnicos de campo e agricultores familiares, substituindo o controle manual (planilhas, papel) por um fluxo de trabalho digital, eficiente e transparente.

Este repositório contém o **Backend** do projeto Ecosy, uma API RESTful desenvolvida com **Java e Spring Boot**. O sistema é responsável por gerenciar todo o ciclo de vida da distribuição de sementes, desde o cadastro de lotes e beneficiários até o registro das entregas no campo.

## 🛠️ Tecnologias Utilizadas

O projeto foi construído utilizando as melhores práticas de desenvolvimento Java moderno:

* **Java 21**: Linguagem base (Versão LTS)
* **Spring Boot**: Framework principal.
* **Spring Data JPA (Hibernate)**: Para persistência de dados e ORM (Mapeamento Objeto-Relacional).
* **Spring Security**: Para criptografia de senhas (BCrypt) e segurança básica.
* **MySQL**: Banco de dados relacional.
* **Maven**: Gerenciador de dependências.

---

## 🏗️ Arquitetura do Projeto

O sistema segue uma arquitetura em camadas bem definida para garantir a organização e escalabilidade:

1.  **Controller Layer (`.controller`)**: Pontos de entrada da API (REST). Recebem as requisições JSON e retornam as respostas HTTP adequadas.
2.  **Repository Layer (`.repository`)**: Interface de comunicação com o banco de dados via JPA.
3.  **Model/Entity (`.model`)**: Classes que representam as tabelas do banco de dados (Code-First).

### Destaques da Implementação:
* **Integração e Relacionamentos**: Uso de `@OneToMany` e `@ManyToOne` para garantir a integridade referencial entre Entregas, Beneficiários, Lotes e Usuários.
* **Tratamento Global de Erros**: Um `GlobalExceptionHandler` intercepta erros (como Entidade Não Encontrada ou Violação de Integridade) e retorna JSONs amigáveis e padronizados.
* **Soft Delete**: Implementação de exclusão lógica para Beneficiários, garantindo histórico e auditoria.

---

## 🚀 Como Rodar o Projeto

### Pré-requisitos
* Java JDK 21 instalado.
* Maven instalado.
* MySQL Server rodando na porta 3306 ou 3307.

### 1. Clonar o Repositório
```bash
git clone https://github.com/C-26hub/PI2-Back.git
```

### 2. Configurar o Banco de Dados
Crie um banco de dados vazio no seu MySQL:

SQL
```bash
CREATE DATABASE ecosy_db;
```

Abra o arquivo src/main/resources/application.properties e configure suas credenciais:

Properties
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/ecosy_db?useTimezone=true&serverTimezone=UTC
spring.datasource.username=seu_usuario_mysql
spring.datasource.password=sua_senha_mysql

# O Hibernate criará as tabelas automaticamente
spring.jpa.hibernate.ddl-auto=update
```

3. Executar a Aplicação
No terminal, dentro da pasta do projeto:

Bash
```bash
mvn spring-boot:run
```
O servidor iniciará em http://localhost:8080.


## 👥 Equipe
- **Arthur Filipe Rodrigues da Silva** – arthur.filipe2402@gmail.com
- **Filipe Xavier dos Santos** – xfilipe2006.santos@gmail.com   
- **Maria Cecília de Lima e Silva** – cecilmari33@gmail.com  
- **Maria Eduarda Pereira Vilarim** – vilarim051@gmail.com
- **Matheus Alves de Arruda** – matheusalves2906@gmail.com


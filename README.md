# Ecosy

> **Ecosy** é uma plataforma digital para a gestão, distribuição e rastreabilidade do programa de aquisição de **sementes crioulas** em Pernambuco.

O sistema conecta gestores públicos, técnicos de campo e agricultores familiares, substituindo o controle manual (planilhas, papel) por um fluxo de trabalho digital, eficiente e transparente.

Este repositório contém o **Backend** do projeto Ecosy, uma API RESTful desenvolvida com **Java e Spring Boot**. O sistema é responsável por gerenciar todo o ciclo de vida da distribuição de sementes, desde o cadastro de lotes e beneficiários até o registro das entregas no campo.

## 🛠️ Tecnologias Utilizadas

O projeto foi construído utilizando as melhores práticas de desenvolvimento Java moderno:

* **Java 21**: Linguagem base (Versão LTS).
* **Spring Boot 3**: Framework principal.
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

## 📚 Documentação da API (Principais Endpoints)

A API fornece CRUD completo para as entidades principais.

###  Usuários (Autenticação e Gestão)
| Método | Rota | Descrição |
| :--- | :--- | :--- |
| POST | `/api/usuarios/login` | Autenticação (Email/Senha) |
| POST | `/api/usuarios` | Criar novo Gestor ou Técnico |
| GET | `/api/usuarios` | Listar usuários |

###  Beneficiários (Agricultores)
| Método | Rota | Descrição |
| :--- | :--- | :--- |
| GET | `/api/beneficiarios` | Listar todos (Filtra ativos) |
| GET | `/api/beneficiarios/{id}` | Detalhes completos |
| POST | `/api/beneficiarios` | Cadastrar novo (c/ endereço) |
| PUT | `/api/beneficiarios/{id}` | Atualizar dados cadastrais |
| PATCH | `/api/beneficiarios/{id}/status` | Ativar/Inativar (Soft Delete) |
| DELETE | `/api/beneficiarios/{id}` | Exclusão (Admin) |

###  Lotes (Estoque de Sementes)
| Método | Rota | Descrição |
| :--- | :--- | :--- |
| GET | `/api/lotes` | Listar estoques disponíveis |
| POST | `/api/lotes` | Cadastrar novo lote de sementes |
| PUT | `/api/lotes/{id}` | Ajustar dados do lote |
| DELETE | `/api/lotes/{id}` | Remover lote (se s/ entregas) |

###  Entregas (Operação de Campo)
| Método | Rota | Descrição |
| :--- | :--- | :--- |
| GET | `/api/entregas` | Listar entregas |
| POST | `/api/entregas` | Registrar nova entrega (baixa estoque) |
| PUT | `/api/entregas/{id}` | Corrigir lançamento |
| DELETE | `/api/entregas/{id}` | Remover lançamento |

---

## 🚀 Como Rodar o Projeto

### Pré-requisitos
* Java JDK 21 instalado.
* Maven instalado.
* MySQL Server rodando na porta 3306.

### 1. Clonar o Repositório
```bash
git clone [https://github.com/C-26hub/PI2-Back.git](https://github.com/C-26hub/PI2-Back.git)
cd PI2-Back

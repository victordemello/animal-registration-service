# Animal Registration Service

> **Work in Progress** - Este projeto está em desenvolvimento ativo e possui diversas oportunidades de melhoria e evolução.

Sistema de cadastro e gerenciamento de animais para abrigos, permitindo o registro de entrada, acompanhamento e controle de adoções.

## Status do Projeto

| Aspecto | Status |
|---------|--------|
| API REST CRUD | Funcional |
| Migrations Flyway | Funcional |
| Docker | Funcional |
| Testes Unitários | Pendente |
| Testes de Integração | Pendente |
| Documentação OpenAPI/Swagger | Pendente |
| CI/CD | Pendente |
| Autenticação/Autorização | Pendente |

## Tecnologias

- **Java 21** (LTS)
- **Spring Boot 4.0.2**
- **Spring Data JPA**
- **Flyway** - Migrations de banco de dados
- **PostgreSQL 15**
- **Docker & Docker Compose**
- **pgAdmin 4** - Interface web para gerenciamento do banco

## Pré-requisitos

- [Docker](https://www.docker.com/get-started) e Docker Compose
- [Git](https://git-scm.com/)

Para desenvolvimento local:
- Java 21+
- Maven 3.9+

## Como Executar

### Com Docker (Recomendado)

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/animal-registration-service.git
cd animal-registration-service

# Copie o arquivo de variáveis de ambiente
cp .env.example .env

# Suba os containers
docker-compose up -d --build

# Verifique os logs
docker-compose logs -f app
```
## Acessos

| Serviço | URL | Credenciais |
|---------|-----|-------------|
| API | http://localhost:8080 | - |
| pgAdmin | http://localhost:5050 | admin@admin.com / admin |

### Configuração do pgAdmin

Para conectar ao PostgreSQL no pgAdmin:
- **Host:** `postgres` (ou `localhost` se acessando de fora do Docker)
- **Port:** `5432`
- **Database:** `animal_registration`
- **Username:** `animal_user`
- **Password:** `animal_secret`

## API Endpoints

Base URL: `http://localhost:8080/api/animals`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/create` | Cadastrar novo animal |
| `GET` | `/{id}` | Buscar animal por ID |
| `GET` | `/` | Listar todos os animais (paginado) |
| `GET` | `/available` | Listar animais disponíveis para adoção |
| `GET` | `/adopted` | Listar animais adotados |
| `PUT` | `/{id}` | Atualizar animal |
| `DELETE` | `/{id}` | Remover animal |
| `PATCH` | `/{id}/adoption` | Registrar adoção |

### Exemplos de Requisições

**Cadastrar Animal:**
```bash
curl -X POST http://localhost:8080/api/animals/create \
  -H "Content-Type: application/json" \
  -d '{
    "estimatedAgeInMonths": 12,
    "temporaryName": "Rex",
    "arrivalConditionDescription": "Encontrado na rua, saudável",
    "receivedBy": "João Silva",
    "animalTypeId": "uuid-do-tipo",
    "sizeId": "uuid-do-tamanho"
  }'
```

**Registrar Adoção:**
```bash
curl -X PATCH http://localhost:8080/api/animals/{id}/adoption \
  -H "Content-Type: application/json" \
  -d '{
    "adoptionDate": "2026-02-04"
  }'
```

**Listar Disponíveis para Adoção:**
```bash
curl http://localhost:8080/api/animals/available
```

## Estrutura do Projeto

```
src/main/java/com/victordemello/animalregistrationservice/
├── controllers/          # Endpoints REST
├── dtos/                 # Objetos de transferência (Request/Response)
├── entities/             # Entidades JPA
├── exceptions/           # Exceções customizadas e handlers
├── repositories/         # Interfaces de acesso a dados
└── services/             # Regras de negócio

src/main/resources/
├── db/migration/         # Migrations Flyway
└── application.yml       # Configurações da aplicação
```

## Migrations

As migrations são executadas automaticamente pelo Flyway na inicialização:

| Versão | Descrição |
|--------|-----------|
| V1 | Criação do schema inicial (tabelas e constraints) |
| V2 | Seed de tipos de animais (Cat, Dog) |
| V3 | Seed de tamanhos (Small, Medium, Large, Giant) |

## Variáveis de Ambiente

Consulte o arquivo `.env.example` para todas as variáveis disponíveis:

| Variável | Descrição | Padrão |
|----------|-----------|--------|
| `POSTGRES_DB` | Nome do banco | `animal_registration` |
| `POSTGRES_USER` | Usuário do banco | `animal_user` |
| `POSTGRES_PASSWORD` | Senha do banco | `animal_secret` |
| `POSTGRES_PORT` | Porta do PostgreSQL | `5432` |
| `PGADMIN_EMAIL` | Email do pgAdmin | `admin@admin.com` |
| `PGADMIN_PASSWORD` | Senha do pgAdmin | `admin` |
| `PGADMIN_PORT` | Porta do pgAdmin | `5050` |
| `APP_PORT` | Porta da aplicação | `8080` |

## Oportunidades de Melhoria para avaliar

Este projeto está em evolução. Algumas melhorias planejadas:

- [ ] Implementar testes unitários e de integração
- [ ] Adicionar documentação OpenAPI/Swagger
- [ ] Implementar autenticação e autorização (Spring Security + JWT)
- [ ] Criar endpoints para gerenciamento de AnimalTypes e Sizes
- [ ] Adicionar upload de fotos dos animais
- [ ] Implementar busca e filtros avançados
- [ ] Adicionar cache (Redis)
- [ ] Configurar CI/CD (GitHub Actions)
- [ ] Implementar logs estruturados
- [ ] Adicionar métricas e health checks (Actuator)
- [ ] Implementar soft delete para animais
- [ ] Adicionar histórico de alterações (audit log)

## Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests.

## Licença

Este projeto está sob a licença MIT.

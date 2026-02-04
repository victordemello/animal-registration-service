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

### Animals
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

### Statistics
Base URL: `http://localhost:8080/api/statistics`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/rescues?startDate={date}&endDate={date}` | Estatísticas de resgates por funcionário |

**Regras:**
- O intervalo entre `startDate` e `endDate` nao pode exceder 365 dias (1 ano)
- Datas no formato ISO: `YYYY-MM-DD`

### Exemplos de Requisicoes

**Cadastrar Animal:**
```bash
curl -X POST http://localhost:8080/api/animals/create \
  -H "Content-Type: application/json" \
  -d '{
    "estimatedAgeInMonths": 12,
    "temporaryName": "Rex",
    "arrivalConditionDescription": "Encontrado na rua, saudável",
    "animalTypeId": "uuid-do-tipo",
    "sizeId": "uuid-do-tamanho",
    "rescuedByEmployeeId": "uuid-do-funcionario"
  }'
```

**Registrar Adocao:**
```bash
curl -X PATCH http://localhost:8080/api/animals/{id}/adoption \
  -H "Content-Type: application/json" \
  -d '{
    "adoptionDate": "2026-02-04"
  }'
```

**Listar Disponiveis para Adocao:**
```bash
curl http://localhost:8080/api/animals/available
```

**Estatisticas de Resgates por Funcionario:**
```bash
curl "http://localhost:8080/api/statistics/rescues?startDate=2024-01-01&endDate=2024-12-31"
```

Resposta:
```json
{
  "startDate": "2024-01-01",
  "endDate": "2024-12-31",
  "totalRescues": 16,
  "rescuesByEmployee": [
    {
      "employeeId": "uuid",
      "employeeName": "Joao Santos",
      "employeeRole": "Animal Rescuer",
      "rescueCount": 5
    }
  ]
}
```

## Estrutura do Projeto

```
src/main/java/com/victordemello/animalregistrationservice/
├── controllers/          # Endpoints REST
│   ├── AnimalController.java
│   └── RescueStatisticsController.java
├── dtos/                 # Objetos de transferencia (Request/Response)
├── entities/             # Entidades JPA
│   ├── Animal.java
│   ├── AnimalType.java
│   ├── Employee.java
│   └── Size.java
├── exceptions/           # Excecoes customizadas e handlers
├── repositories/         # Interfaces de acesso a dados
└── services/             # Regras de negocio
    ├── AnimalService.java
    └── RescueStatisticsService.java

src/main/resources/
├── db/migration/         # Migrations Flyway (V1-V6)
└── application.yml       # Configuracoes da aplicacao
```

## Migrations

As migrations sao executadas automaticamente pelo Flyway na inicializacao:

| Versao | Descricao |
|--------|-----------|
| V1 | Criacao do schema inicial (tabelas e constraints) |
| V2 | Seed de tipos de animais (Cat, Dog) |
| V3 | Seed de tamanhos (Small, Medium, Large, Giant) |
| V4 | Criacao da tabela de funcionarios (employees) |
| V5 | Adicao de FK rescued_by_employee_id na tabela animals |
| V6 | Seed de dados de exemplo (funcionarios e animais) |

## Dados de Exemplo

O banco ja vem populado com dados para testes:

**Funcionarios:**
| Nome | Cargo | Resgates |
|------|-------|----------|
| Maria Silva | Veterinarian | 3 |
| Joao Santos | Animal Rescuer | 5 |
| Ana Oliveira | Animal Rescuer | 4 |
| Pedro Costa | Shelter Manager | 1 |
| Carla Mendes | Animal Rescuer | 3 |

**Animais:** 16 animais cadastrados (5 ja adotados) com datas de chegada entre Janeiro e Agosto de 2024.

## Variaveis de Ambiente

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

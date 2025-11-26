# Evoluir+ API

O projeto **Evoluir+** é uma solução de tecnologia e inovação desenvolvida para mitigar os impactos da disrupção tecnológica e as mudanças no mercado de trabalho. Seu principal foco é combater o **etarismo** e criar oportunidades mais justas e inclusivas por meio da educação e requalificação, especialmente para profissionais que buscam requalificação a partir dos 30 anos.

A **Evoluir+ API** é a aplicação *backend* central, construída com Java (Spring Framework), que suporta um aplicativo de recolocação profissional focado em usuários com mais de 30 anos.

---

## 1. Conceito e Arquitetura da Solução

### 1.1. Objetivo Central

O principal objetivo é fornecer um sistema seguro e eficiente para:
* **Cadastro e Autenticação** de usuários.
* **Gestão da Trilha de Conhecimento** (Cursos, Treinamentos e Palestras).
* **Rastreamento e Cálculo Automático do Progresso** dos usuários.

### 1.2. Arquitetura da Aplicação (Java Spring Framework)

A API adota o padrão de **arquitetura em camadas** (**Model**, **Repository**, **Service**, **Controller**) e segue o princípio RESTful.

| Camada | Função Principal |
| :--- | :--- |
| **Model/DTO** | Define as entidades de negócio e os objetos de transferência de dados para a comunicação HTTP, garantindo a segurança dos dados sensíveis. |
| **Repository** | Interface de persistência com o banco de dados (JPA). |
| **Service** | Contém a **Lógica de Negócio** central, incluindo criptografia de senha e o cálculo de porcentagens de conclusão. |
| **Controller** | Expõe os **endpoints** da API REST, lida com a validação de entrada e coordena o fluxo de requisição. |

### 1.3. Tecnologia e Hospedagem

A solução é implementada em um modelo de **Cloud Computing PaaS (Platform as a Service)**.

* **API:** Java com Spring Framework , hospedada como **Web App PaaS** no **Azure App Service** (Linux/Ubuntu).
* **Banco de Dados:** **PostgreSQL** , hospedado como Serviço PaaS no **Azure Database for PostgreSQL Flexible Server**.
* **Infraestrutura como Código (IaC):** O provisionamento de todos os recursos é feito via **Scripts Azure CLI**.

---

## 2. Fluxo de Autenticação e Uso da API

### 2.1. Cadastro de Usuário

* **Endpoint:** `{{baseURL}}/api/usuarios/cadastro`
* **Método:** `POST`
* **Corpo (JSON de Exemplo):**

```json
{
  "nomeCompleto": "Joao Silva",
  "cpf": "539.276.390-10",
  "dataNascimento": "1985-10-20",
  "cep": "01000-000",
  "email": "joao.silva@email.com",
  "senha": "senhacarlos123",
  "profissao": "GERENTE_DE_PROJETOS",
  "habilidades": ["COMUNICACAO", "LIDERANCA"],
  "objetivos": ["AUTOCONHECIMENTO", "CRESCIMENTO_PROFISSIONAL"]
}
```

### 2.2. Login (Autenticação)

O login retorna um **Token JWT** que deve ser usado no cabeçalho `Authorization: Bearer <seu_token>` para acessar endpoints protegidos.

* **Endpoint:** `{{baseURL}}/api/auth/login`
* **Método:** `POST`
* **Corpo (JSON de Exemplo):**

```json
{
  "email": "joao.silva@email.com",
  "senha": "senhacarlos123"
}
```

---

## 3. JSON de Teste (CRUD de Cursos)

As operações de **CRUD** (Create, Read, Update, Delete) em `/api/admin/cursos` exigem autenticação de Administrador.

### A. CREATE: Criar Curso

* **Endpoint:** `{{baseURL}}/api/admin/cursos`
* **Método:** `POST`
* **Corpo (JSON):**

```json
{
  "titulo": "Telecinese Aplicada à Gestão de Projetos",
  "descricao": "Treinamento intensivo para mover cronogramas usando apenas a força mental (e o MS Project).",
  "instrutor": "Jean Grey",
  "duracaoHoras": 30
}
```

### B. GET: Buscar Curso por ID

* **Endpoint:** `{{baseURL}}/api/admin/cursos/1`
* **Método:** `GET`

### C. UPDATE: Atualizar Curso

* **Endpoint:** `{{baseURL}}/api/admin/cursos/1`
* **Método:** `PUT`
* **Corpo (JSON):**

```json
{
  "titulo": "Telecinese Aplicada à Gestão de Projetos",
  "descricao": "Treinamento intensivo para mover cronogramas usando apenas a força mental (e o MS Project).",
  "instrutor": "Madelyne Pryor",
  "duracaoHoras": 30
}
```

### D. DELETE: Deletar Curso

* **Endpoint:** `{{baseURL}}/api/admin/cursos/1`
* **Método:** `DELETE`

---

## 4. Pipeline de CI/CD (Azure DevOps)

O projeto utiliza o **Azure Pipelines** para implementar um fluxo completo de **Integração Contínua (CI)** e **Entrega Contínua (CD)**[cite: 29, 86].

### 4.1. Fluxo de Desenvolvimento

O *workflow* é regido pelo **Azure Boards** (vinculação de tarefas) e **Azure Repos**, garantindo qualidade e rastreabilidade do código[cite: 78].

* O *workflow* de desenvolvimento é regido pelo Azure Boards e Azure Repos[cite: 78].
* A **Build (CI)** é configurada para ser acionada após a conclusão e *Merge* de um *Pull Request* aprovado[cite: 79].
* O **Deploy (CD)** é totalmente automatizado, executando o *push* do artefato **JAR** para o **Azure App Service**[cite: 80].
* A segurança de credenciais sensíveis é garantida pelo uso de **Variáveis de Ambiente** injetadas via *App Settings* e *Variable Groups* do Azure DevOps[cite: 30, 88].

### Elementos da Macro Arquitetura

| Elemento | Tecnologia/Ferramenta | Descrição Funcional |
| :--- | :--- | :--- |
| **Repositório** | Repos / SCM | Onde o código-fonte está versionado[cite: 60]. |
| **Pipeline** | Azure DevOps Pipelines / Orquestrador CI | Compila e executa testes unitários[cite: 61]. |
| **JAR** | Java / Compilador | Artefato gerado pela compilação da aplicação Java. |
| **Web App Service** | Azure DevOps / Serviço PaaS | Hospedagem da aplicação no Azure[cite: 64, 68]. |
| **Azure CLI** | Utilitário de Linha de Comando | Usado para automatizar ações e tarefas (IaC)[cite: 62, 84]. |

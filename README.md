# 🛍️ EcoMercado

**EcoMercado** é um projeto de e-commerce sustentável de produtos artesanais, construído com arquitetura de microsserviços. Os serviços principais são o `user-service` (usuários) e `product-service` (produtos), com comunicação via **REST** e **gRPC**, utilizando **Docker** para orquestração.

---

## 📌 Objetivo

Demonstrar o conhecimento em arquitetura distribuída, containers e comunicação entre microsserviços (REST e gRPC), conforme os requisitos das Unidades I.2, I.3 e II.7.

---

## 🧱 Tecnologias Utilizadas

- Node.js (Express)
- gRPC
- Docker & Docker Compose
- PostgreSQL
- REST API
- Microsserviços com arquitetura distribuída
- WSL 2 + VSCode

---

## 🗂️ Estrutura do Projeto

```bash
EcoMercado/
├── user-service/       # Microsserviço de usuários (porta 5000)
├── product-service/    # Microsserviço de produtos (porta 5001)
├── docker-compose.yml  # Orquestração com Docker
└── README.md           # Documentação
```

## 🚀 Como Executar o Projeto

### ✅ Pré-requisitos

- Docker Desktop instalado
- WSL 2 configurado no Windows
- Extensão WSL instalada no VSCode

---

### 🧠 Configuração do Docker com WSL

1. Abra o **Docker Desktop**
2. Vá em **Settings > Resources > WSL Integration**
3. Ative **“Enable integration with my default WSL distro”**
4. Marque sua distribuição Linux (ex: Ubuntu)
5. Clique em **Apply & Restart**

---

### 💻 Passos para Rodar no VSCode

1. Clone o repositório:

```bash
git clone https://github.com/mari-ww/Eco-Mercado
cd Eco-Mercado
```

## 🚀 Como Executar o Projeto

### ✅ Pré-requisitos

- Docker Desktop instalado
- WSL 2 configurado no Windows
- Extensão WSL instalada no VSCode

---

### 🧠 Configuração do Docker com WSL

1. Abra o **Docker Desktop**
2. Vá em **Settings > Resources > WSL Integration**
3. Ative **“Enable integration with my default WSL distro”**
4. Marque sua distribuição Linux (ex: Ubuntu)
5. Clique em **Apply & Restart**

---

### 💻 Passos para Rodar no VSCode

1. Clone o repositório:

```bash
git clone https://github.com/mari-ww/Eco-Mercado
cd Eco-Mercado
```

2. No VSCode, pressione Ctrl+Shift+P e selecione:

```makefile
WSL: Reopen Folder in WSL
```

3. No terminal integrado do VSCode, instale as dependências:

```bash
npm install
```

4. Inicie os containers com:

```bash
docker-compose up --build
```

## 🌐 Endpoints REST

### 🧑‍💼 User Service (`http://localhost:5000`)

| Método | Rota        | Descrição           |
|--------|-------------|---------------------|
| POST   | `/usuarios` | Cadastra um usuário |
| GET    | `/usuarios` | Lista os usuários   |

### 📦 Product Service (`http://localhost:5001`)

| Método | Rota        | Descrição         |
|--------|-------------|-------------------|
| GET    | `/produtos` | Lista os produtos |

---

## 🧪 Testando com Postman

Exemplo de requisição **POST** para cadastrar usuário:

```http
POST http://localhost:5000/usuarios
Content-Type: application/json

{
  "id": 1,
  "nome": "Lucas Verde"
}
```

## ✅ Funcionalidades Demonstradas

- ✅ Definição de requisitos funcionais e não funcionais  
- 🧱 Arquitetura distribuída com microsserviços  
- 🐳 Containerização com Docker  
- 🔗 Comunicação entre serviços com REST e gRPC  
- 🧠 Integração real com WSL 2 + VSCode  

---

## 👤 Autoria

Projeto desenvolvido por **Mari** como parte da disciplina de **Computação Distribuída**.

# 📚 LVNABooks

O **LVNABooks** é um sistema em Java que permite o gerenciamento de livros e usuários, integrando a **Google Books API** para buscar informações de livros.  
O projeto foi desenvolvido em **console (CLI)**, aplicando conceitos de **POO, JDBC e MVC**.

---

## 🚀 Funcionalidades
- 👤 Cadastro e login de usuários
- 🔍 Busca de livros pela **Google Books API**
- 📖 Exibição de detalhes de livros (título, autor, categoria, avaliação etc.)
- 💾 Salvamento de livros no **banco de dados MySQL**
- ❌ Exclusão de livros cadastrados
- 📋 Listagem de livros salvos pelo usuário

---

## 🛠️ Tecnologias utilizadas
- **Java +17**
- **MySQL + JDBC**
- **Google Books API**
- **Jackson** (para conversão de JSON)
- **Maven**

---

## 📂 Estrutura do projeto
|── Controller/

│ ├── API/ Consumo e conversão da API

│ ├── DAO/ Acesso ao banco de dados

│ ├── Service/ Regras de negócio

├── Model/ Classes de domínio (Livro, Usuário, etc.)

├── Repository/ Conexão com banco

└── LvnaBooksApplication.java/ Main


Copiar código

---

## ⚙️ Como executar
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/LVNABooks.git
Configure o banco de dados MySQL (script em /Controller/JDBC/SQLScript.txt).

Compile e execute o projeto:
 
Copiar código:
   ```bash
   mvn clean install
   java -jar target/lvnabooks.jar

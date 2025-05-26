# 🏥 MedSync - Sistema de Agendamento Médico

## 💡 Descrição Geral do Sistema
O **MedSync** é um sistema integrado de agendamento de consultas médicas, desenvolvido para facilitar a comunicação entre médicos e pacientes. O sistema permite que pacientes visualizem médicos disponíveis, agendem novas consultas e acompanhem o histórico de seus agendamentos. Já os médicos conseguem gerenciar sua agenda.

## 💻 Tecnologias Utilizadas
- **Backend:** Java 17, Spring Boot 3
- **Frontend:** JavaScript, Vue 3, Bootstrap 5.3.6
- **Banco de Dados:** PostgreSQL 17
- **Infraestrutura:** Docker
- **Outros:** Maven, npm, Swagger, MapStruct

## ⚙️ Funcionalidades Principais

### 👨‍⚕️Médico
- Disponibilizar os horários para consultas
- Visualizar agenda de consultas

### 👩‍⚕️ Paciente
- Selecionar médicos e agendar consultas
- Visualizar histórico de agendamentos (passados e futuros)

## 🔧 Configuração Local do projeto

### 🧾 Pré-requisitos
- **Java:** JDK 11 ou superior;
- **Node.js:** versão 16 ou superior; 
- **Ferramentas:** Maven, npm/Yarn, Postman (opcional);
- **IDE's:** Visual Studio Code (recomendado);
- **Plugins VS Code (opcional):** Spring Boot Tools (para inicialização);
- **Docker**;
- **Maven**: versão 3.8+ (Para empacotar o projeto)
- **WSL 2:** Apenas se utilizar Windows, será necessário instalar uma distribuição Linux (ex.: Ubuntu) e instalar o Docker dentro do Ubuntu através do WSL 2;


### 🔑 Configuração .env
Apesar de não ser recomendável publicar as variáveis de ambiente (arquivo .env) no repositório remoto, para maior facilidade, o backend do projeto possui um arquivo chamado .env, onde se encontra o valor da chave secreta para criação do JWT.


### 📂 Executando o projeto
Abra um diretório no terminal, copie e cole:
```bash
git clone https://github.com/devrafael/desafio-tecnico-conecte.git
```
Acesse a pasta do backend via terminal
```
cd desafio-tecnico-conecte/backend
```
Empacote o projeto
```
mvn clean package -DskipTests
```
Depois de empacotado, copie e cole:
```
docker-compose up --build -d
```
Após terminado o processo de build, retorne para o diretório principal e acesse a pasta do frontend:
```
cd ..
cd frontend
```
Por fim, copie e cole esses dois comandos:
```
npm install
npm run serve
```
A interface estará disponível em `http://localhost:3000` (porta padrão)

### 📝 Login
- Médico: {
   email: medico@email.com,
   senha: medico
}
- Paciente: {
   email: paciente@email.com,
   senha: paciente
}

Observação: Caso queira realizar login simultâneo (Médico e Paciente), utilize navegadores diferentes.

### 📝 Cadastro de usuário

Apesar do sistema já possuir 2 usuários cadastrados (1 médico e 1 paciente), é possível realizar o cadastro de um novo usuário. 


## 🔍 Endpoints

Utilizei a biblioteca Swagger para documentar e testar os endpoints.
Após executar o backend do projeto, acesse: http://localhost:8080/swagger-ui/index.html

## ✨ Considerações

### 🔐 Segurança
- Validação de entrada e proteção contra injeções de SQL  
- Senhas criptografadas  
- Autenticação via JWT

### 🖼️ Usabilidade
- Interface limpa e responsiva  
- Feedback visual nas ações  
- Navegação intuitiva para médicos e pacientes

---

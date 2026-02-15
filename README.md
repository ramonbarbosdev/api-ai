# MemorixAI API

API backend do **MemorixAI**, um assistente de inteligência artificial com memória persistente, desenvolvido com Spring Boot e Spring AI.

Esta API é responsável por gerenciar conversas, persistir histórico e integrar com modelos de IA.

---

## Tecnologias utilizadas

* Java 21+
* Spring Boot
* Spring AI
* PostgreSQL
* Maven
* REST API

---

## Funcionalidades

* Geração de respostas com IA
* Persistência de histórico de conversas
* Gerenciamento de contexto
* Arquitetura preparada para memória persistente
* Integração com modelos de linguagem (LLM)

---

## Estrutura do projeto

```
src/main/java/
 ├── controller     # Controllers REST
 ├── service        # Lógica de negócio
 ├── config         # Configurações
 ├── model          # Entidades
 └── repository     # Acesso a dados
```

---


## Executando o projeto

Clone o repositório:

```
git clone https://github.com/ramonbarbosdev/api-ai.git
```

Entre na pasta:

```
cd api-ai
```

Execute:

```
./mvnw spring-boot:run
```

ou

```
mvn spring-boot:run
```

---

## Endpoint principal

Exemplo de requisição:

```
POST /api/chat
```

Body:

```json
{
  "message": "Olá"
}
```

Resposta:

```json
{
  "response": "Olá! Como posso ajudar?"
}
```

---

## Objetivo

Este projeto faz parte do portfólio, com foco em:

* Arquitetura backend moderna
* Integração com inteligência artificial
* Persistência de memória conversacional
* Aplicações reais com Spring AI

---

## Autor

Ramon Barbosa
Desenvolvedor Full Stack
Java | Spring Boot | Angular | AI

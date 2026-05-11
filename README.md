# 🛡️ SentinelAI

<p align="center">
  <b>AppSec + IA + Programação Reativa</b>
</p>

<p align="center">
  Projeto experimental focado em segurança ofensiva, heurísticas de detecção e integração de IA em scanners de aplicações web.
</p>

---

# 📖 Sobre o Projeto

O **SentinelAI** é um projeto experimental desenvolvido com o objetivo de estudar:

- segurança ofensiva
- análise heurística de vulnerabilidades
- integração de IA em scanners de aplicações web

A proposta do projeto não é competir com ferramentas profissionais como **Burp Suite**, **OWASP ZAP** ou **Nuclei**, mas sim explorar arquiteturas modernas de **AppSec** utilizando:

- Java
- Spring WebFlux
- Reactor
- modelos de linguagem locais

---

# 🎯 Objetivo do Projeto

O SentinelAI foi criado como um laboratório pessoal para estudar:

- Segurança de aplicações web
- Heurísticas de detecção de vulnerabilidades
- Programação reativa com Reactor/WebFlux
- Paralelismo e concorrência
- Integração entre scanners e IA
- Redução de falsos positivos usando LLMs

O foco principal é:

```text
aprendizado + experimentação + exploração
```

de conceitos modernos de AppSec.

---

# ⚙️ Como o Projeto Funciona

A ideia central do SentinelAI é:

```text
Payloads
↓
Requests paralelas
↓
Filtros heurísticos rápidos
↓
IA analisa apenas resultados suspeitos
↓
Resultado final
```

O scanner executa múltiplas requisições HTTP em paralelo utilizando:

- WebClient
- Reactor

Depois aplica filtros básicos para encontrar possíveis indícios de vulnerabilidade e então envia apenas os resultados mais relevantes para análise por IA.

---

# 🧪 Vulnerabilidades Implementadas

Atualmente o projeto possui scanners experimentais para:

- XSS
- SQL Injection
- LFI
- Directory Traversal
- Open Redirect
- Command Injection
- Insecure Headers
- CSRF
- IDOR

Cada vulnerabilidade possui heurísticas próprias de detecção.

---

# 🧰 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring WebFlux
- Reactor
- WebClient
- Ollama
- Maven

---

# 🧠 Principais Conceitos Explorados

## ⚡ Programação Reativa

O projeto utiliza:

- Flux
- Mono

para execução assíncrona e paralela de requisições HTTP.

---

## 🔍 Heurísticas de Segurança

Cada scanner possui regras específicas para identificar comportamentos suspeitos:

- Reflexão de payloads
- Erros SQL
- Arquivos sensíveis
- Headers inseguros
- Ausência de proteção
- Diferenças de resposta

---

## 🤖 IA Aplicada à Segurança

Os resultados suspeitos são enviados para um modelo de linguagem (**Mistral**) local via Ollama para:

- reduzir falsos positivos
- contextualizar evidências
- gerar justificativas técnicas
- calcular nível de confiança

---

# 🏗️ Estrutura do Projeto

```text
services/
 ├── ci/
 ├── csrf/
 ├── idor/
 ├── ih/
 ├── lfi/
 ├── op/
 ├── sqli/
 ├── xss/
```

Cada scanner possui sua própria lógica de análise e heurística.

---

# 🧬 Pipeline Interno

```text
                ┌──────────────┐
                │   Request    │
                └──────┬───────┘
                       ↓
                ┌──────────────┐
                │ Payload Engine│
                └──────┬───────┘
                       ↓
                ┌──────────────┐
                │ WebClient     │
                └──────┬───────┘
                       ↓
                ┌──────────────┐
                │ Response Data │
                └──────┬───────┘
                       ↓
                ┌──────────────┐
                │ Basic Filter  │
                └──────┬───────┘
                       ↓
                ┌──────────────┐
                │ IA Analyzer   │
                └──────┬───────┘
                       ↓
                ┌──────────────┐
                │ Findings      │
                └──────────────┘
```

---

# ⚠️ Aviso

Este projeto foi desenvolvido exclusivamente para fins educacionais e de pesquisa.

Não utilize o SentinelAI contra sistemas sem autorização explícita.

O autor não se responsabiliza pelo uso indevido do software.

---

# 🚧 Status do Projeto

Projeto experimental em constante evolução.

Atualmente o foco está em:

- melhoria de heurísticas
- redução de falsos positivos
- arquitetura modular
- integração mais inteligente com IA
- melhoria de performance

---

# 🚀 Motivação

O SentinelAI nasceu da curiosidade sobre:

- como scanners modernos funcionam internamente
- como modelos de linguagem podem auxiliar na análise de vulnerabilidades

Mais do que um scanner, o projeto é um ambiente de estudo sobre:

```text
AppSec + IA + Programação Reativa
```

---

# 👨‍💻 Autor

### João Carlos Hallak

Projeto desenvolvido para aprendizado, pesquisa e diversão explorando segurança ofensiva e IA.
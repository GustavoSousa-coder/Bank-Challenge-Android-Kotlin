# Banking App - Android Client

Aplicativo Android desenvolvido em Kotlin para consumir a [Bank Transaction API](https://github.com/GustavoSousa-coder/REST-API-Banking-Challenge-with-Java-Spring-Boot), uma API REST bancária com autenticação JWT.

## Sobre o Projeto

Cliente mobile do sistema bancário desenvolvido como projeto pessoal de portfólio. O app permite que clientes realizem operações bancárias como login, visualização de contas, transferências via Pix e acompanhamento de estatísticas de transações.

## Estado Atual do Projeto

neste momento o projeto segue com telas simples apenas com layout simples organizado em activitys e códigos de cada tela,
a inteção desse commit se destaca por evidenciar a geração de telas e criação dos layouts básicos para seguir para a arquitetura de estrutura complexa para que o projeto siga sem falhas.

## Tecnologias

- **Kotlin** — linguagem principal
- **XML Layouts** — construção de interfaces
- **Retrofit** — cliente HTTP para consumo da API
- **OkHttp** — interceptor para autenticação JWT automática
- **Gson** — serialização e deserialização de JSON
- **Coroutines** — requisições assíncronas sem bloqueio da UI

## Funcionalidades

- [ x ] Login com email e senha
- [ ] Visualização de contas (corrente e poupança)
- [ ] Transferência via chave Pix
- [ ] Estatísticas de transações
- [ ] Cadastro de chave Pix

## Status

🚧 Em desenvolvimento
# Banking App - Android Client

Aplicativo Android desenvolvido em Kotlin para consumir a [Bank Transaction API](link-do-seu-repositório-da-api), uma API REST bancária com autenticação JWT.

## Sobre o Projeto

Cliente mobile do sistema bancário desenvolvido como projeto pessoal de portfólio. O app permite que clientes realizem operações bancárias como login, visualização de contas, transferências via Pix e acompanhamento de estatísticas de transações.

## Tecnologias

- **Kotlin** — linguagem principal
- **XML Layouts** — construção de interfaces
- **Retrofit** — cliente HTTP para consumo da API
- **OkHttp** — interceptor para autenticação JWT automática
- **Gson** — serialização e deserialização de JSON
- **Coroutines** — requisições assíncronas sem bloqueio da UI

## Funcionalidades

- [ ] Login com email e senha
- [ ] Visualização de contas (corrente e poupança)
- [ ] Transferência via chave Pix
- [ ] Estatísticas de transações
- [ ] Cadastro de chave Pix

## Pré-requisitos

Para rodar o projeto localmente é necessário ter a [Bank Transaction API](link-do-seu-repositório-da-api) rodando e configurar a URL base no cliente HTTP.

## Status

🚧 Em desenvolvimento
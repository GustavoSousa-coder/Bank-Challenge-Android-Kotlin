# Banking App - Android Client

Aplicativo Android desenvolvido em Kotlin para consumir a [Bank Transaction API](https://github.com/GustavoSousa-coder/REST-API-Banking-Challenge-with-Java-Spring-Boot), uma API REST bancária com autenticação JWT.

## Sobre o Projeto

Cliente mobile do sistema bancário desenvolvido como projeto pessoal de portfólio. O app permite que clientes realizem operações bancárias como login, visualização de contas, transferências via Pix e acompanhamento de estatísticas de transações.

## Estado Atual do Projeto

neste momento o projeto segue com telas simples apenas com layout simples organizado em activitys e códigos de cada tela,
a inteção desse commit se destaca por evidenciar a geração de telas e criação dos layouts básicos para seguir para a arquitetura de estrutura complexa para que o projeto siga sem falhas.

## Telas

# activity_splash
esta é apenas uma tela simples de carregamento na qual dura 2 segundos e meio para que tenha uma leve transição para o usuário do momento em que entra no aplicativo.

<img width="307" height="667" alt="splash" src="https://github.com/user-attachments/assets/d0b2e075-9199-4830-9475-faf0d7955ff6" />


# activity_login
esta é a tela de login na qual o usuário deverá entrar com seu e-mail e senha os dados inseridos devem ser enviados para a API já citada neste README e então ela dará a permissão para entrar caso o usuário não tenha um aconta ele poderá ser redirecionado para a tela de cadastro.

<img width="310" height="670" alt="login" src="https://github.com/user-attachments/assets/d6c39b19-6e78-4618-a49b-bcbbe053237c" />


# activity_register
já esta é a tela de cadastro para os usuário que ainda não passuem uma conta cadastrada no sistema do app no caso a API que segue com a mesma dinâmica da tela de login após esse momento o usuário é direcionado para as demais telas no momento a home.

<img width="277" height="602" alt="register" src="https://github.com/user-attachments/assets/dc763232-c85b-4146-97fc-b3e14768eea5" />


# activity_home
está é uma tela onde todo usuário já logado ou momentos antes criado a conta chega, aqui ele terá todos os recursos para suas atividades financeiras.

<img width="277" height="597" alt="home" src="https://github.com/user-attachments/assets/5a63673d-60a6-440a-bd53-b24ffe783492" />


## Tecnologias

- **Kotlin** — linguagem principal
- **XML Layouts** — construção de interfaces
- **Retrofit** — cliente HTTP para consumo da API
- **OkHttp** — interceptor para autenticação JWT automática
- **Gson** — serialização e deserialização de JSON
- **Coroutines** — requisições assíncronas sem bloqueio da UI

## Funcionalidades

- [x] Login com email e senha
- [ ] Visualização de contas (corrente e poupança)
- [ ] Transferência via chave Pix
- [ ] Estatísticas de transações
- [ ] Cadastro de chave Pix

## Status

🚧 Em desenvolvimento

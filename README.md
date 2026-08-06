# Banking App - Android Client

Aplicativo Android desenvolvido em Kotlin para consumir a [Bank Transaction API](https://github.com/GustavoSousa-coder/REST-API-Banking-Challenge-with-Java-Spring-Boot), uma API REST bancária com autenticação JWT.

## Sobre o Projeto

## Integração com a API

Esta versão fecha o ciclo completo de autenticação do aplicativo: 
desde o primeiro cadastro até o acesso autenticado à Home. Todo o 
fluxo foi implementado, testado de ponta a ponta e está funcionando 
com a API backend.

### O que foi implementado

- **Cadastro de cliente**: o usuário preenche seus dados (nome, CPF, 
  data de nascimento, e-mail e senha), que são enviados para a API e 
  validados no servidor.

- **Configuração de conta**: logo após o login, o usuário escolhe o 
  tipo de conta (Corrente ou Poupança), que é então criada e 
  vinculada ao seu cadastro.

- **Login e autenticação**: o usuário acessa o app com e-mail e senha. 
  A API retorna um token JWT, que o aplicativo passa a anexar 
  automaticamente em todas as requisições seguintes.

- **Autorização por permissões (Roles)**: cada requisição autenticada 
  é validada também quanto às permissões do usuário (Cliente ou 
  Admin), garantindo que cada rota da API só seja acessada por quem 
  tem autorização para isso.

- **Continuidade de sessão**: o app reconhece automaticamente se o 
  usuário já está logado, ou se iniciou um cadastro mas não chegou a 
  configurar a conta — retomando o fluxo exatamente de onde parou, 
  mesmo que o aplicativo tenha sido fechado no meio do processo.

### Como funciona por baixo dos panos

Toda a comunicação entre app e API acontece via **HTTP**, usando 
**DTOs** (Data Transfer Objects) — estruturas que representam 
exatamente os dados trocados em cada requisição e resposta, mantendo 
o app e a API sempre alinhados quanto ao formato da informação. A 
autenticação segue o padrão **JWT** (JSON Web Token) com controle de 
acesso baseado em permissões (**Role-Based Access Control**), 
seguindo as práticas recomendadas para APIs stateless.

### Ciclo de autenticação — concluído 
### Desenvolvimento assistido por IA

As interfaces deste aplicativo foram prototipadas com auxílio do v0 by Vercel, seguindo requisitos, fluxos, estados e uma identidade visual 8-bit/16-bit definidos para o projeto.

A IA foi utilizada para acelerar a exploração visual e gerar uma estrutura inicial das telas. Todo o código resultante foi analisado, adaptado para Android nativo com Kotlin e XML, integrado à arquitetura do aplicativo, refatorado e validado antes de sua inclusão no projeto.

As decisões de arquitetura, regras de negócio, integração com a API, segurança dos dados, revisão do código e responsabilidade pelo resultado final permanecem sob minha autoria.

## Tecnologias

- **Kotlin** — linguagem principal
- **XML Layouts** — construção de interfaces
- **Retrofit** — cliente HTTP para consumo da API
- **OkHttp** — interceptor para autenticação JWT automática
- **Gson** — serialização e deserialização de JSON
- **Coroutines** — requisições assíncronas sem bloqueio da UI

## Funcionalidades

- [x] Login com email e senha
- [ ] Transferência via chave Pix
- [ ] Estatísticas de transações
- [ ] Cadastro de chave Pix

## Status

A partir daqui, o desenvolvimento passa a se concentrar **dentro** do 
aplicativo já autenticado — construindo as funcionalidades que o 
usuário vai usar no dia a dia.

### Próximos passos

Com a base de autenticação consolidada, as próximas versões vão focar 
nas funcionalidades internas do app:

- Exibição de saldo e dados reais da conta na Home
- Menu lateral funcional (Perfil, Configurações, Suporte e Logout)
- Notificações
- Pix
- Transferências
- Otimização de performance e refinamento de experiência do usuário (UX)

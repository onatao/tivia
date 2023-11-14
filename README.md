# Desafio Java TiviaTI
<p><i>Este repositório é referente a um desafio para vaga de Desenvolvedor Java da empresa Tivia TI postada no site APInfo,
o teste foi enviado pela Tech Recruiter Miriam e desenvolvido por mim, Nathan Barros de Assis.</i></p>

<p><b>Abaixo você verá informações sobre o desenvolvimento e instruções para build.</b></p>

- ### Dependências:
  - Spring Boot - framework
  - Spring Security - autenticação e autorização 
  - Spring Data JPA - persistência
  - JWT - geração do token JWT
  - Swagger - documentação dos endpoints
  - Lombok - otimização de código boiler plate
  - H2 Database - base de dados para perfil de testes
  - PostgreSQL - base de dados para perfil de desenvolvimento
  - JUnit5 & Mockito - testes unitários
  - Jarkata Varlidation - validação dos dtos
  - Swagger - documentação dos endpoints

<i>Para garantir a segurança, agilidade e consistência no desenvolvimento foi utlizado <b>Git Flow</b> para versionamento entre as branches</i>

- ### Cadastro e condições para manter um beneficiário:
  <p>Como regra de negócio eu adotei que para um beneficiário possa ser cadastrado é necessário o envio dos seguintes documentos:
  CPF, RG, Certidão de Nascimento e Comprovante de Residência. Se por ventura algum destes documentos não estiver presente no ato de cadastro, a ação não será concluída e 
  retornará uma exeção.</p>
  <b>Para identificação do tipo de documento que está sendo cadastrado foi utlizado um tipo enumerado (enum).</b>

- ### Autenticação e Autorização (JWT)
  <p>Para manter a segurança da aplicação eu optei por utilizar o Spring Security em conjunto com JWT, a ideia é que seja uma aplicação statelss e que o fluxo de   
  autenticação e autorização seja feito através do Token JWT. Defini dois cargos (roles) que são: <b>ADMIN</b> e <b>USER</b> aonde <b>USER</b> representa um beneficiário que 
  utiliza o serviço e <b>ADMIN</b> representando algum colaborador que tenha acesso privilegiado.</p>

- ### Fluxo de endpoints
  <p>Abaixo você verá como foi feita a restrição de acesso aos endpoints com base em roles</p>

  | USER  | ADMIN | 
  | ------------- | ------------- |
  | Cadastrar-se na plataforma   | Remover um beneficiário  | 
  | Atualizar seus documentos  | Listar todos os beneficiários  |
  | -  | Listar documentos de um beneficiário  |

<b>>Existem dois endpoints especiais Registrar-se e Logar.</b>
  <p>Optei por restringir tanto a listagem de beneficiários quanto a listagem de documentos ao cargo de <b>ADMIN</b> para que apenas um colaborador com acesso privilegiado. 
  tenha acesso as informações sensíveis de um beneficário.</p>

  - ### Pré-requisitos
    Antes de começar, certifique-se de que você tenha as seguintes ferramentas instaladas:
      - JDK 17
      - Maven

  - ### Executando
      Você pode executar a aplicação de diferentes maneiras:
    #### Opção 1: Linha de Comando
    ```bash
      mvn spring-boot run
    ```
    <b>Observação: executar o comando com terminal aberto no diretório do projeto</b>
    #### Opção 2: IDE
    1. Importe o projeto em sua IDE de preferência que suporte Spring Boot e Maven
    2. Procure a classe principal `TiviaApplication.java` e execute como uma aplicação Java.




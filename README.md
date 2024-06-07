# 🐟 API de Sinalização Automática de Espécies Marinhas 🌊

## Visão Geral
A API de Identificação Automática de Espécies Marinhas é um sistema baseado em Java que permite o armazenamento e consulta de dados relacionados à detecção de espécies marinhas. Este projeto visa facilitar o monitoramento da biodiversidade marinha, ajudando cientistas e conservacionistas a rastrear populações, detectar espécies invasoras e identificar espécies em risco de extinção.

## Funcionalidades Principais
- Armazenamento de dados de detecção de espécies marinhas em um banco de dados.
- Consulta de dados de detecção de espécies através da API.
- Filtragem de ongs que possuem foco numa determinada categoria de animal para envio de sinalização.

## Tecnologias Utilizadas
- Linguagem de Programação: Java
- Frameworks: Spring Boot,BeanValidation, JPARepository, lombok, swagger
- Banco de Dados: OracleDatabse

## Endpoints da API
[Arquivo do Postman para baixar e importar](https://github.com/charlenefialho/javaAPI-oceanovivo-gs/blob/main/docs-files/api-java-oceano-vivo.postman_collection.json)

## Instalação e Uso
1. Clone este repositório.
2. Import como projeto Maven.
3. Configure as credenciais do banco de dados no arquivo `application.properties` com a sua para não ficar usando a minha.
4. Execute a aplicação utilizando o comando `mvn spring-boot:run`.
5. Acesse a documentação da API em `http://localhost:8080/swagger-ui.html`.

### Integrantes do grupo
<table>
  <tr>
        <td align="center">
      <a href="https://github.com/biancaroman">
        <img src="https://avatars.githubusercontent.com/u/128830935?v=4" width="100px;" border-radius='50%' alt="Bianca Román's photo on GitHub"/><br>
        <sub>
          <b>Bianca Román</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/charlenefialho">
        <img src="https://avatars.githubusercontent.com/u/94643076?v=4" width="100px;" border-radius='50%' alt="Charlene Aparecida's photo on GitHub"/><br>
        <sub>
          <b>Charlene Aparecida</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/laiscrz">
        <img src="https://avatars.githubusercontent.com/u/133046134?v=4" width="100px;" alt="Lais Alves's photo on GitHub"/><br>
        <sub>
          <b>Lais Alves</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/LuccaRaphael">
        <img src="https://avatars.githubusercontent.com/u/127765063?v=4" width="100px;" border-radius='50%' alt="Lucca Raphael's photo on GitHub"/><br>
        <sub>
          <b>Lucca Raphael</b>
        </sub>
      </a>
    </td>
     <td align="center">
      <a href="https://github.com/Fabs0602">
        <img src="https://avatars.githubusercontent.com/u/111320639?v=4" width="100px;" border-radius='50%' alt="Fabricio Torres's photo on GitHub"/><br>
        <sub>
          <b>Fabricio Torres</b>
        </sub>
      </a>
    </td>
  </tr>
</table>


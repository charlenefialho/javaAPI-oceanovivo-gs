# 🐟 API de Sinalização Automática de Espécies Marinhas 🌊

## Visão Geral
A API de Identificação Automática de Espécies Marinhas é um sistema baseado em Java que permite o armazenamento e consulta de dados relacionados à detecção de espécies marinhas. Este projeto visa facilitar o monitoramento da biodiversidade marinha, ajudando cientistas e conservacionistas a rastrear populações, detectar espécies invasoras e identificar espécies em risco de extinção.

## Funcionalidades Principais
- Armazenamento de dados de detecção de espécies marinhas em um banco de dados.
- Consulta de dados de detecção de espécies através da API.

## Tecnologias Utilizadas
- Linguagem de Programação: Java
- Frameworks: Spring Boot,BeanValidation, JPARepository, lombok, swagger
- Banco de Dados: OracleDatabse

## Endpoints da API
[Arquivo do Postman para baixar e importar](https://github.com/charlenefialho/LeadTechAPI/blob/master/api-dotnet-leadtech.postman_collection.json)

## Instalação e Uso
1. Clone este repositório.
2. Import como projeto Maven.
3. Configure as credenciais do banco de dados no arquivo `application.properties` com a sua para não ficar usando a minha.
4. Execute a aplicação utilizando o comando `mvn spring-boot:run`.
5. Acesse a documentação da API em `http://localhost:8080/swagger-ui.html`.

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request com melhorias ou correções.

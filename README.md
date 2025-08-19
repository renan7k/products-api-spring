## Api de Produtos - Spring
API construída junto de uma aula inicial sobre spring/ JPA, para aprender os fundamentos
e funcionamento do spring.

### Próximos passos
- Incluir paginação na listagem de produtos
- Incluir filtros
- Inserir tratamentos de erros
- Implementar camada service

### Tecnologias
-Java 17
-Spring boot 3.5
-Spring JPA 
-Postgree

### Subindo projeto
-Clonar repositório
-Baixar dependências (Maven)
-Run "SpringbootApplication"

### Rotas
Obs.: Bater na porta que o TOMCAT rodar na sua máquina
-Criar produto
    POST
    http://localhost:8080/products
    Body: {"name": "Mesa c/ 4 cadeiras","value": 270.25 }

-Consultar todos os produtos:
    GET
    http://localhost:8080/products

-Consultar por Id:
    GET
    http://localhost:8080/products/{{id}}

-Alterar produto
    PUT
    http://localhost:8080/products/{{ID}}
-Deletar produto
    DELETE  
    http://localhost:8080/products/{{ID}}   
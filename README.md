# Projeto SpringBoot+Angular

## Tecnologias da stack

- SpringBoot
- Spring Security OAuth
- AngularJS 9
- bootStrap 4
- Postgres
- Docker

- Projeto construído em ambiente linux.

## Configurar o projeto no seu ambiente

### Ter instalado

- Java 11
- Node.js
- Maven

### Build

- Compilado SprigBoot
    - na pasta api rodar

```unix
mvn package
```

- Compilando Angular
    - na pasta web rodar

```unix
npm install
npm install -g @angular/cli
ng build
```

### Deploy

- Deploy
    - na raiz do projeto

```unix
docker-compose up -d
```

- Acessar http://localhost:4200/

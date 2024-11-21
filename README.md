# Challenge Alura ONE - Foro Hub

## Diagrama Entidad-Relacion

### Entidades

* User
* Messages
* Topic

<div align="center">
    <img src="/src/main/resources/static/diagrama_relacional.png">
</div>

## Requerimientos

* Base de datos Relacional MySQL
* CRUD de Topicos
* Implementacion de Spring Security
* Implementacion de JWT
* Integracion de documentacion en Swagger
* Implementacion de rutas Messages and User (Opcional)

## Pasos Iniciar el Programa

1. Creacion del .env

```
DB_URL=
DB_USER=
DB_PASS=
JWT_SECRET=
JWT_EXPIRATION=
```

2. Configuracion del application.properties

```properties
spring.application.name=forohub
spring.datasource.url=jdbc:mysql://${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASS}
server.servlet.context-path=/api/v1
spring.jpa.hibernate.ddl-auto=update
server.error.include-stacktrace=never
api.security.token.secret=${JWT_SECRET}
api.security.token.expiration=${JWT_EXPIRATION}
```

3. Configurar en las variables de entorno en los perfiles de configuracion en IntelliJ IDEA

<div align="center">
    <img src="/src/main/resources/static/envconfig.png" alt="perfil">
</div>

## Badge de Challenge

<div align="center">
    <img src="/src/main/resources/static/Badge-Spring.png" alt="badge">
</div>

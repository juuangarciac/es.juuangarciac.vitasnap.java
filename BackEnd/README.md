# VitaSnap API REST

## Descripción general

El BackEnd de la aplicación consiste en la implementación de una **API REST**.

## Patrón Arquitectónico

## Estructura de carpetas

1. *`/src/main/java/es/juuangarciac/vitasnap`* código fuente de la aplicación, así como las **entidades**, **servicios** y **repositorios** que participan en ella.
    - `/album` entidad **album**
    - `/post` entidad **Post**
    - `/security` implementación de seguridad en el sistema mediante [Spring Boot Security](https://spring.io/projects/spring-security).
    - `/user` entidad **Usuario**.
        - `/controllers` implementación del servicio REST (*UserRestController.class*).
        - `/domain` implementación entidad Usuario (*User.class*) junto a Rol (*Role.class*).
        - `/repositories` implementación de los repositorios necesarios que interactúan directamente con la entidad *Usuario*.
        - `/services` implementación de los servicios que interactúan con sus respectivos repositorios. También se implementa el **servicio de correo electrónico** que usará la aplicación.
2. `/resources` contiene los recursos necesarios para ejecutar la aplicación, por ejemplo, el fichero *application.properties*.
3. `/test`


## Colaboradores


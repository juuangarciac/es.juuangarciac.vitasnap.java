# VitaSnap API REST

## Descripción general

Servicio **API RESTful**.

## Estructura de carpetas

1. *`/src/main/java/es/juuangarciac/vitasnap`* código fuente de la aplicación, así como las **entidades**, **servicios** y **repositorios** que participan en ella.
    - `/album` entidad **album**
         - `/controllers` implementación del servicio REST (*AlbumRestController.class*).
        - `/domain` implementación entidad Album (*Album.class*).
        - `/repositories` implementación de los repositorios necesarios que interactúan directamente con la entidad *Album*.
        - `/services` implementación de los servicios que interactúan con sus respectivos repositorios.
    - `/post` entidad **Post**
         - `/controllers` implementación del servicio REST (*PostRestController.class*).
        - `/domain` implementación entidad Post (*Post.class*).
        - `/repositories` implementación de los repositorios necesarios que interactúan directamente con la entidad *Post*.
        - `/services` implementación de los servicios que interactúan con sus respectivos repositorios. 
    - `/security` implementación de seguridad en el sistema mediante [Spring Boot Security](https://spring.io/projects/spring-security).
    - `/user` entidad **Usuario**.
        - `/controllers` implementación del servicio REST (*UserRestController.class*).
        - `/domain` implementación entidad Usuario (*User.class*) junto a Rol (*Role.class*).
        - `/repositories` implementación de los repositorios necesarios que interactúan directamente con la entidad *Usuario*.
        - `/services` implementación de los servicios que interactúan con sus respectivos repositorios. También se implementa el **servicio de correo electrónico** que usará la aplicación.
2. `/resources` contiene los recursos necesarios para ejecutar la aplicación, por ejemplo, el fichero *application.properties*.
3. `/test` sección de *testing* de los diferentes servicios o microservicios que proporciona el sistema.

## Anatomia de la API

La API de VitaSnap sigue la arquitectura **RESTful** (Representational State Transfer). Usa **API keys** para autenticar y autorizar las peticiones **HTTP** (GET, POST, PUT, DELETE).

## Requisitos de la API

La API debe permitir la ejecución de operaciones **CRUD** (Create,  Read, Update, Delete) sobre los datos del sistema, en concreto, sobre las entidades **Usuario**, **Post** y **Album**.

## Funcionamiento de la API protegida con Spring Security y API Keys

Nuestra API utiliza **Spring Security** para implementar un mecanismo de autenticación basado en **claves API**. Cuando un cliente realiza una solicitud a un punto final protegido, nuestro filtro personalizado `AuthenticationFilter` intercepta la solicitud y extrae la clave API del encabezado de la solicitud. Luego, la clave API se valida para garantizar que sea válida y autorizada para acceder a los recursos protegidos. Si la clave API es válida, se permite que la solicitud continúe y se procese el recurso solicitado. En caso de que la clave API no sea válida o no se proporcione, se devuelve un error de autenticación `401 Unauthorized`.

[Autenticación mediante claves API](img/resources/APIKeyAuthentication.png)

Una vez que se ha verificado la autenticación, las solicitudes se autorizan según los permisos y roles definidos en nuestra configuración de Spring Security.

## Arquitectura de la API

## EndPoints
1. `/api/users`
    - `/api/users/{id}` *single item*.
2. `/api/posts`
    - `/api/posts/{id}` *single item*.
3. `/api/albums`
    - `/api/albums/{id}` *single item*.

## Colaboradores

## Referencias

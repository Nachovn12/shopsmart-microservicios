# ShopSmart - Microservicios (Actividad 1.1.2)

Repositorio del proyecto de la actividad 1.1.2 de Desarrollo Fullstack III.
Implementamos 3 microservicios para la plataforma ShopSmart usando Spring Boot y Java.

## Integrantes

- Ignacio Valeria
- Benjamín Flores

## Descripcion

ShopSmart es una tienda online que tenia problemas con su sistema monolitico (caidas en peak de ventas, stock desactualizado, etc). La solucion fue separar el sistema en microservicios independientes, cada uno con su propia responsabilidad.

Implementamos 3 servicios:

- **inventory-service** → maneja los productos y el stock
- **orders-service** → maneja los pedidos de los clientes
- **users-service** → maneja el registro de usuarios

## Tecnologias

- Java 21
- Spring Boot 3.5.11
- Maven
- HTML/CSS/JS para el panel de pruebas

## Como correr el proyecto

Necesitas tener Java 21 y Maven instalados. Cada servicio se corre en una terminal distinta.

```bash
# Terminal 1
cd inventory-service
./mvnw spring-boot:run

# Terminal 2
cd orders-service
./mvnw spring-boot:run

# Terminal 3
cd users-service
./mvnw spring-boot:run
```

Una vez que los 3 esten corriendo, abrir el archivo `frontend-prueba.html` en el navegador para probar los endpoints sin necesidad de Postman.

## Puertos

| Servicio          | Puerto |
| ----------------- | ------ |
| inventory-service | 8081   |
| orders-service    | 8082   |
| users-service     | 8083   |

## Endpoints disponibles

**Productos**

- GET `http://localhost:8081/api/productos`
- POST `http://localhost:8081/api/productos`

**Pedidos**

- GET `http://localhost:8082/api/pedidos`
- POST `http://localhost:8082/api/pedidos`

**Usuarios**

- GET `http://localhost:8083/api/usuarios`
- POST `http://localhost:8083/api/usuarios`

## Notas

- Los datos no se guardan en base de datos real, todo queda en memoria mientras el servidor este corriendo. Si se reinicia el servicio se pierden los datos agregados.
- Los pedidos siempre parten con estado PENDIENTE al crearlos
- Los usuarios siempre parten con categoria NUEVO al registrarse
- Hay que tener los 3 servicios corriendo al mismo tiempo para que el panel HTML funcione completo


# Back-End Tools 2025

Este proyecto implementa un servicio REST en Java con Spring Boot que permite ordenar un conjunto de productos según criterios ponderados: unidades vendidas y ratio de stock. Emplea una arquitectura hexagonal con separación de dominio, aplicación e infraestructura, y utiliza MongoDB para la persistencia de datos.

## TODO:
- Resolver problemas en mi local con la lombok dependencies. (Mix entre @Value, @Getter, @Setter y métodos en crudo)
- Problemas con la dependencia Groovy


## Ejecución

1. Clonar el repositorio:  
   ```bash
   git clone https://github.com/andreadereque/backend-tools-2025.git
   cd backend-tools-2025


2. Configurar MongoDB en `src/main/resources/application.properties`:

   ```properties
   spring.data.mongodb.host=localhost
   spring.data.mongodb.port=27017
   spring.data.mongodb.database=backendtoolsdb
   ```
3. Iniciar la aplicación:

   ```bash
   mvn spring-boot:run
   ```

   La API estará disponible en `http://localhost:8080/api/rank`.

## Uso de la API

Enviar un POST a `/api/rank` con un cuerpo JSON como:

```json
{
  "products": [
    { "id": "1", "name": "A", "salesUnits": 10, "stocks": { "S": 5, "M": 2 } },
    { "id": "2", "name": "B", "salesUnits": 5, "stocks": { "S": 0, "M": 1 } }
  ],
  "weights": { "salesUnits": 1.0, "stockRatio": 1.0 }
}
```

La respuesta contiene la lista ordenada de mayor a menor puntuación.

## Pruebas

Ejecutar todos los tests:

```bash
mvn test
```

Incluirá tests unitarios y la prueba de integración E2E con Rest Assured.

## Estructura del proyecto

```
backend-tools-2025/
├ pom.xml
├ src/main/java/com/andrea/backendtools/
│  ├ application/
│  ├ config/
│  ├ domain/
│  └ infrastructure/
│     ├ api/
│     └ mongo/
└ src/test/java/com/andrea/backendtools/
```

El proyecto está preparado para añadir nuevos criterios implementando `ScoringCriteria`.

```
```

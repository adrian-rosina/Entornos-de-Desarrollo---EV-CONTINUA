# Sistema de Gestión para una Pajarería

Aplicación de consola en Java para gestionar **clientes**, **pájaros (productos)** y **ventas**.

## Requisitos
- Java 17+
- Maven 3.9+

## Estructura
```
/src/main/java/com/pajareria/    # código fuente
/src/test/java/com/pajareria/    # pruebas JUnit 5
/docs/                            # Javadoc generado
pom.xml                           # configuración Maven
```

## Ejecutar
```bash
mvn -q clean compile
mvn -q exec:java
```

## Pruebas
```bash
mvn -q test
```

## Generar Javadoc a /docs
```bash
mvn -q javadoc:javadoc
```

## Autoría y licencia
Autor: Adrián Rosiña Pérez  
Licencia: MIT

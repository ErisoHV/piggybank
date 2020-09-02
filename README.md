# Mi PiggyBank

Esta pequeña aplicación web simula ser una alcancía. Fue desarrollada utilizando:

- Java 1.8
- Springboot versión 2.3.3
- Base de datos H2 (en memoria)
- HTML, CSS, jQuery
- Bootstrap


# Requisitos de desarrollo

- Java 1.8
- Maven 3.6.3


# Generación del Jar

Una vez clonado el repositorio, puede ejecutar:

```
mvn clean install
```

# Ejecución

Una vez generado el jar. Ejecute:

```
java -jar piggybank-0.0.1-SNAPSHOT.jar
```

Esto levantará el servidor. La aplicación por defecto iniciará en el puerto 8081 (puede cambiar el puerto en el application.properties si lo desea)

Ingrese a la aplicación a través de: http://IP:PUERTO/ (por ejemplo: http://192.168.x.x:8081/)

# Demo

 ![Demo de la app](https://github.com/ErisoHV/piggybank/blob/master/screenshots/demo.gif)





# Iniciemos

### Requisitos
For further reference, please consider the following sections:

* Java 21
* Maven
* Postgresql
* Postman
* DbBeaver

### Guía

* Crear en Postgresql una base de datos llamada db_customer
* Configurar configuraciones en archivo application.properties
* Clonar repositorio
* Ejecutar servicio. Cómo estamos usando liquibase los modelos de datos se crearán al iniciar el proceso.

### Servicios Disponibles
* Crear un cliente
```curl --location 'http://localhost:8089/customer' \
--header 'Content-Type: application/json' \
--data-raw '{
    "documentType": "CC",
    "documentNumber": "57455823",
    "firstName": "Lionel",
    "lastName": "Messi",
    "status": "A",
    "contacts": [
        {
            "dataType": "EM",
            "data": "Lionel.Messi@example.com",
            "status": "A"
        },
        {
            "dataType": "PH",
            "data": "+57 3012223344",
            "status": "A"
        }
    ]
}'
```

* Consultar cliente por documentType y documentNumber
``` curl --location --request GET 'http://localhost:8089/customer/CC/57455823' \
--header 'Content-Type: application/json' \
--data '
'
```

* Consultar existencia y estado de un cliente
```   curl --location --request GET 'http://localhost:8089/customer/status?documentType=CC&documentNumber=57455823' \
  --header 'Content-Type: application/json' \
  --data '
  '
``` 

* Consultar todos los clientes

``` curl --location --request GET 'http://localhost:8089/customer/customers' \
--header 'Content-Type: application/json' \
--data '
'
```

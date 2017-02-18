# Paseos

# Tabla de contenidos
-  [Introducción](#introducción)
-  [API](#api-de-la-aplicación-paseos)
  - [Recurso Guia](#recurso-guia)
    - [GET /guias](#GET-/guias)
    - [GET /guias/{id}](#GET-/guias/{id})
    - [POST /guias](#POST-/guias)
    - [PUT /guias/{id}](#PUT-/guias/{id})
    - [DELETE /guias/{id}](#DELETE-/guias/{id})
  - [Recurso Cliente](#recurso-cliente)
    - [GET /clientes](#GET-/clientes)
    - [GET /clientes/{id}](#GET-/clientes/{id})
    - [POST /clientes](#POST-/clientes)
    - [PUT /clientes/{id}](#PUT-/clientes/{id})
    - [DELETE /clientes/{id}](#DELETE-/clientes/{id})

# API Rest
## Introducción
La comunicación entre cliente y servidor se realiza intercambiando objetos JSON. Para cada entidad se hace un mapeo a JSON, donde cada uno de sus atributos se transforma en una propiedad de un objeto JSON. Todos los servicios se generan en la URL /paseos.api/api/. Por defecto, todas las entidades tienen un atributo `id`, con el cual se identifica cada registro:

```javascript
{
    id: '',
    attribute_1: '',
    attribute_2: '',
    ...
    attribute_n: ''
}
```

Cuando se transmite información sobre un registro específico, se realiza enviando un objeto con la estructura mencionada en la sección anterior.
La única excepción se presenta al solicitar al servidor una lista de los registros en la base de datos, que incluye información adicional para manejar paginación de lado del servidor en el header `X-Total-Count` y los registros se envían en el cuerpo del mensaje como un arreglo.

La respuesta del servidor al solicitar una colección presenta el siguiente formato:

```javascript
[{}, {}, {}, {}, {}, {}]
```

## API de la aplicación paseos
### Recurso Guia
El objeto Guia tiene 1 representación JSON:	

#### Representación 
```javascript
{
    id: '' /*Tipo Long*/,
    nombre: '' /*Tipo String*/,
    apellido: '' /*Tipo String*/,
    usuario: '' /*Tipo String*/,
    pass: '' /*Tipo undifined*/,
    formacion: '' /*Tipo String*/,
    experiencia: '' /*Tipo undifined*/,
    calificacionPromedio: '' /*Tipo double*/,
}
```

#### GET /guias

Retorna una colección de objetos Guia

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [Representación](#recurso-guia)
409|Un objeto relacionado no existe|Mensaje de error
500|Error interno|Mensaje de error

#### GET /guias/{id}

Retorna una colección de objetos guias en su representación.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Guia a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Guia en [Representación](#recurso-guia)
404|No existe un objeto Guia con el ID solicitado|Mensaje de error
500|Error interno|Mensaje de error

#### POST /guias

Es el encargado de crear objetos Guia.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Guia que será creado|Sí|[Representación](#recurso-guia)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Guia ha sido creado|[Representación](#recurso-guia)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo crear el objeto Guia|Mensaje de error

#### PUT /guias/{id}

Es el encargado de actualizar objetos Guias.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Guia a actualizar|Sí|Integer
body|body|Objeto Guia nuevo|Sí|[Representación](#recurso-guia)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Guia actualizado|[Representación](#recurso-guia)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo actualizar el objeto Guia|Mensaje de error

#### DELETE /guias/{id}

Elimina un objeto Guia.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Guia a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error

### Recurso Cliente
El objeto Cliente tiene 1 representación JSON:	

#### Representación 
```javascript
{
    id: '' /*Tipo Long*/,
    nombre: '' /*Tipo String*/,
    apellido: '' /*Tipo String*/,
    usuario: '' /*Tipo String*/,
    pass: '' /*Tipo undefined.*/,
    edad: '' /*Tipo Integer.*/,
    condicionFisica: '' /*Tipo Integer.*/
}
```

#### GET /clientes

Retorna una colección de objetos CLiente en su representación.

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [Representación](#recurso-cliente)
409|Un objeto relacionado no existe|Mensaje de error
500|Error interno|Mensaje de error

#### GET /clientes/{id}

Retorna una colección de objetos Cliente en su representación.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Cliente a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Cliente en [Representación](#recurso-cliente)
404|No existe un objeto Cliente con el ID solicitado|Mensaje de error
500|Error interno|Mensaje de error

#### POST /clientes

Es el encargado de crear objetos Cliente.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Cliente que será creado|Sí|[Representación](#recurso-cliente)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Cliente ha sido creado|[Representación](#recurso-cliente)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo crear el objeto Cliente|Mensaje de error

#### PUT /clientes/{id}

Es el encargado de actualizar objetos clientes.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Cliente a actualizar|Sí|Integer
body|body|Objeto Cliente nuevo|Sí|[Representación](#recurso-cliente)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Cliente actualizado|[Representación](#recurso-cliente)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo actualizar el objeto Cliente|Mensaje de error

#### DELETE /clientes/{id}

Elimina un objeto Cliente.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Clientes a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error


[Volver arriba](#tabla-de-contenidos)


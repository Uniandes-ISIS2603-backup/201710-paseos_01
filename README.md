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
  - [Recurso Detalle](#recurso-detalle)
    - [GET /detalles](#GET-/detalles)
    - [GET /detalles/{id}](#GET-/detalles/{id})
    - [POST /detalles](#POST-/detalles)
    - [PUT /detalles/{id}](#PUT-/detalles/{id})
    - [DELETE /detalles/{id}](#DELETE-/detalles/{id})
  - [Recurso Paseo](#recurso-paseo)
    - [GET /paseos](#GET-/paseos)
    - [GET /paseos/{id}](#GET-/paseos/{id})
    - [POST /paseos](#POST-/paseos)
    - [PUT /paseos/{id}](#PUT-/paseos/{id})
    - [DELETE /paseos/{id}](#DELETE-/paseos/{id})
  - [Recurso Visita](#recurso-visita)
    - [GET /visitas](#GET-/visitas)
    - [GET /visitas/{id}](#GET-/visitas/{id})
    - [POST /visitas](#POST-/visitas)
    - [PUT /visitas/{id}](#PUT-/visitas/{id})
    - [DELETE /visitas/{id}](#DELETE-/visitas/{id})
    - [GET /visitas/{id}/fotos](#GET-/visitas/{id}/fotos)
    - [GET /visitas/{id}/fotos/{id}](#GET-/visitas/{id}/fotos/{id})
    

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
    calificacionPromedio: '' /*Tipo double*/
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

### Recurso Detalle
El objeto Detalle tiene 1 representación JSON:	

#### Representación 
```javascript
{
    id: '' /*Tipo Long*/,
    tematica: '' /*Tipo String*/,
    numeroMinimo: '' /*Tipo int*/,
    numeroMaximo: '' /*Tipo int*/,
    destino: '' /*Tipo String*/,
    descripcion: '' /*Tipo String*/,
    condicionFisica: '' /*Tipo int*/,
    costo: '' /*Tipo double*/,
    transporte: ''/*Tipo boolean*/,
    almuerzo: ''/*Tipo boolean*/
}
```

#### GET /detalles

Retorna una colección de objetos Detalle

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [Representación](#recurso-detalle)
409|Un objeto relacionado no existe|Mensaje de error
500|Error interno|Mensaje de error

#### GET /detalles/{id}

Retorna una colección de objetos detalles en su representación.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Detalle a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Detalle en [Representación](#recurso-detalle)
404|No existe un objeto Detalle con el ID solicitado|Mensaje de error
500|Error interno|Mensaje de error

#### POST /detalles

Es el encargado de crear objetos Detalle.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Detalle que será creado|Sí|[Representación](#recurso-detalle)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Detalle ha sido creado|[Representación](#recurso-detalle)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo crear el objeto Detalle|Mensaje de error

#### PUT /detalles/{id}

Es el encargado de actualizar objetos Detalle.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Detalle a actualizar|Sí|Integer
body|body|Objeto Detalle nuevo|Sí|[Representación](#recurso-detalle)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Detalle actualizado|[Representación](#recurso-detalle)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo actualizar el objeto Detalle|Mensaje de error

#### DELETE /detalles/{id}

Elimina un objeto Detalle.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Detalle a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error

### Recurso Paseo
El objeto Paseo tiene 1 representación JSON:	

#### Representación 
```javascript
{
    id: '' /*Tipo Long*/,
    idDetalle: '' /*Tipo Long*/,
    fecha: ''/*Tipo Date*/,
    inscritos: ''/*Tipo int*/
}
```

#### GET /paseos

Retorna una colección de objetos Paseo

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [Representación](#recurso-paseo)
409|Un objeto relacionado no existe|Mensaje de error
500|Error interno|Mensaje de error

#### GET /paseos/{id}

Retorna una colección de objetos paseos en su representación.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Paseo a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Paseo en [Representación](#recurso-paseo)
404|No existe un objeto Paseo con el ID solicitado|Mensaje de error
500|Error interno|Mensaje de error

#### POST /paseos

Es el encargado de crear objetos Paseo.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Paseo que será creado|Sí|[Representación](#recurso-paseo)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Paseo ha sido creado|[Representación](#recurso-paseo)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo crear el objeto Paseo|Mensaje de error

#### PUT /paseos/{id}

Es el encargado de actualizar objetos Paseo.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Paseo a actualizar|Sí|Integer
body|body|Objeto Paseo nuevo|Sí|[Representación](#recurso-paseo)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Paseo actualizado|[Representación](#recurso-paseo)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo actualizar el objeto Paseo|Mensaje de error

#### DELETE /paseos/{id}

Elimina un objeto Paseo.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Paseo a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error

### Recurso Visita
El objeto Visita tiene 1 representación JSON:	

#### Representación 
```javascript
{
    id: '' /*Tipo Long*/,
    idCliente: '' /*Tipo Long*/,
    idGuia: '' /*Tipo Long*/,
    idPaseo: '' /*Tipo Long*/,
    calificacion: ''/*Tipo int*/,
    comentario: ''/*Tipo String*/
}
```

#### GET /visitas

Retorna una colección de objetos visita

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [Representación](#recurso-visita)
409|Un objeto relacionado no existe|Mensaje de error
500|Error interno|Mensaje de error

#### GET /visitas/{id}

Retorna una colección de objetos visita en su representación.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Visita a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Visita en [Representación](#recurso-visita)
404|No existe un objeto Visita con el ID solicitado|Mensaje de error
500|Error interno|Mensaje de error

#### POST /visitas

Es el encargado de crear objetos Visita.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Visita que será creado|Sí|[Representación](#recurso-visita)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Visita ha sido creado|[Representación](#recurso-visita)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo crear el objeto Visita|Mensaje de error

#### PUT /visitas/{id}

Es el encargado de actualizar objetos Visita.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Visita a actualizar|Sí|Integer
body|body|Objeto Visita nuevo|Sí|[Representación](#recurso-visita)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Visita actualizado|[Representación](#recurso-visita)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo actualizar el objeto Visita|Mensaje de error

#### DELETE /visitas/{id}

Elimina un objeto Visita.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Visita a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error

El objeto Foto tiene 1 representación JSON:	

#### Representación 
```javascript
{
    id: '' /*Tipo Long*/,
    idVisita: '' /*Tipo Long*/,
    path: ''/*Tipo String*/
}
```
#### GET /visitas/{id}/fotos

Retorna una colección de objetos foto

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Visita con las fotos a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos foto en[Representación](#recurso-visita)
409|Un objeto relacionado no existe|Mensaje de error
500|Error interno|Mensaje de error

#### GET /visitas/{idVisita}/fotos/{idFoto}

Retorna una colección de objetos fotos en su representación.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
idVisita|Path|ID del objeto Visita con la foto a consultar|Sí|Integer
idFoto|Path|ID del objeto foto a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto foto en [Representación](#recurso-visita)
404|No existe un objeto Visita con el ID solicitado|Mensaje de error
500|Error interno|Mensaje de error


[Volver arriba](#tabla-de-contenidos)


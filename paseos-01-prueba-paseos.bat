{
	"variables": [],
	"info": {
		"name": "paseos-01-prueba",
		"_postman_id": "d44e47ef-8a10-860c-fae6-1705ba586aed",
		"description": "",
		"schema": "https://schema.getpostman.com/json/collection/v2.0.0/collection.json"
	},
	"item": [
		{
			"name": "http://localhost:8080/paseos-01-web/api/usuarios",
			"event": [
				{
					"listen": "test",
					"script": {
						"type": "text/javascript",
						"exec": [
							"tests[\"Status code is 200\"] = responseCode.code === 200;",
							"var data = JSON.parse([responseBody]);",
							"tests[\"test create\"] = data.destino === \"Prueba\";",
							"postman.setGlobalVariable(\"id\", data.id);"
						]
					}
				}
			],
			"request": {
				"url": "http://localhost:8080/paseos-01-web/api/paseos",
				"method": "POST",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json",
						"description": ""
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\r\n                                \"destino\":\"Prueba\",\r\n                                \"tematica\":\"Prueba\",\r\n                                \"condicionFisica\":6,\r\n                                \"almuerzo\":true,\r\n                                \"transporte\":false,\r\n                                \"numeroMinimo\":10,\r\n                                \"numeroMaximo\":100,\r\n                                \"costo\":100000\r\n                            }"
				},
				"description": ""
			},
			"response": []
		},
		{
			"name": "http://localhost:8080/paseos-01-web/api/usuarios copy",
			"event": [
				{
					"listen": "test",
					"script": {
						"type": "text/javascript",
						"exec": [
							"tests[\"Status code is 200\"] = responseCode.code === 200;",
							"var data = JSON.parse([responseBody]);",
							"tests[\"test create\"] = data.destino === \"Prueba\";",
							"postman.setGlobalVariable(\"idg\", data.id);"
						]
					}
				}
			],
			"request": {
				"url": "http://localhost:8080/paseos-01-web/api/paseos",
				"method": "POST",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json",
						"description": ""
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\r\n                                \"destino\":\"Prueba\",\r\n                                \"tematica\":\"Prueba\",\r\n                                \"condicionFisica\":6,\r\n                                \"almuerzo\":true,\r\n                                \"transporte\":false,\r\n                                \"numeroMinimo\":10,\r\n                                \"numeroMaximo\":100,\r\n                                \"costo\":100000\r\n                            }"
				},
				"description": ""
			},
			"response": []
		},
		{
			"name": "http://localhost:8080/paseos-01-web/api/paseos?catalogo=0",
			"event": [
				{
					"listen": "test",
					"script": {
						"type": "text/javascript",
						"exec": [
							"tests[\"Status code is 200\"] = responseCode.code === 200;",
							"var data = JSON.parse(responseBody);",
							"tests[\"test length of response\"] = data.length === 2;"
						]
					}
				}
			],
			"request": {
				"url": "http://localhost:8080/paseos-01-web/api/paseos?catalogo=0",
				"method": "GET",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json",
						"description": ""
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"nombres\": \"Prueba\",\r\n    \"apellidos\": \"Pruebas\",\r\n    \"condicionFisica\": 1,\r\n    \"fechaNacimiento\": \"1979-10-11T14:45:00\",\r\n    \"login\": \"prueba\"\r\n}"
				},
				"description": ""
			},
			"response": []
		},
		{
			"name": "http://localhost:8080/paseos-01-web/api/usuarios?guias=1",
			"event": [
				{
					"listen": "test",
					"script": {
						"type": "text/javascript",
						"exec": [
							"tests[\"Status code is 200\"] = responseCode.code === 200;",
							"var data = JSON.parse(responseBody);",
							"tests[\"test paseos\"] = data.length === 2;"
						]
					}
				}
			],
			"request": {
				"url": "http://localhost:8080/paseos-01-web/api/paseos?catalogo=1",
				"method": "GET",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json",
						"description": ""
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"nombres\": \"Prueba1\",\r\n    \"apellidos\": \"Pruebas\",\r\n    \"condicionFisica\": 1,\r\n    \"fechaNacimiento\": \"1979-10-11T14:45:00\",\r\n    \"login\": \"prueba\",\r\n    \"guia\": true,\r\n    \"ofertas\": [],\r\n    \"visitas\": []\r\n}"
				},
				"description": ""
			},
			"response": []
		},
		{
			"name": "http://localhost:8080/paseos-01-web/api/usuarios/2",
			"event": [
				{
					"listen": "test",
					"script": {
						"type": "text/javascript",
						"exec": [
							"tests[\"Status code is 200\"] = responseCode.code === 200;",
							"var data = JSON.parse([responseBody]);",
							"tests[\"test guia\"] = data.guia === true;"
						]
					}
				}
			],
			"request": {
				"url": "http://localhost:8080/paseos-01-web/api/usuarios/{{id}}",
				"method": "PUT",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json",
						"description": ""
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"nombres\": \"Prueba1\",\r\n    \"apellidos\": \"Pruebas\",\r\n    \"condicionFisica\": 1,\r\n    \"fechaNacimiento\": \"1979-10-11T14:45:00\",\r\n    \"guia\": true,\r\n    \"ofertas\": [],\r\n    \"visitas\": []\r\n}"
				},
				"description": ""
			},
			"response": []
		},
		{
			"name": "http://localhost:8080/paseos-01-web/api/usuarios?guias=1 copy",
			"event": [
				{
					"listen": "test",
					"script": {
						"type": "text/javascript",
						"exec": [
							"tests[\"Status code is 200\"] = responseCode.code === 200;",
							"var data = JSON.parse([responseBody]);",
							"tests[\"test paseo\"] = data.paseo === true;"
						]
					}
				}
			],
			"request": {
				"url": "http://localhost:8080/paseos-01-web/api/paseos/{{id}}",
				"method": "GET",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json",
						"description": ""
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"nombres\": \"Prueba1\",\r\n    \"apellidos\": \"Pruebas\",\r\n    \"condicionFisica\": 1,\r\n    \"fechaNacimiento\": \"1979-10-11T14:45:00\",\r\n    \"login\": \"prueba\",\r\n    \"guia\": true,\r\n    \"ofertas\": [],\r\n    \"visitas\": []\r\n}"
				},
				"description": ""
			},
			"response": []
		},
		{
			"name": "http://localhost:8080/viajes-01-web/api/usuarios copy",
			"event": [
				{
					"listen": "test",
					"script": {
						"type": "text/javascript",
						"exec": [
							"tests[\"Status code is 204\"] = responseCode.code === 204;"
						]
					}
				}
			],
			"request": {
				"url": "http://localhost:8080/paseos-01-web/api/paseos/{{id}}",
				"method": "DELETE",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json",
						"description": ""
					}
				],
				"body": {
					"mode": "raw",
					"raw": ""
				},
				"description": ""
			},
			"response": []
		},
		{
			"name": "http://localhost:8080/viajes-01-web/api/usuarios copy copy",
			"event": [
				{
					"listen": "test",
					"script": {
						"type": "text/javascript",
						"exec": [
							"tests[\"Status code is 204\"] = responseCode.code === 204;"
						]
					}
				}
			],
			"request": {
				"url": "http://localhost:8080/paseos-01-web/api/paseos/{{idg}}",
				"method": "DELETE",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json",
						"description": ""
					}
				],
				"body": {
					"mode": "raw",
					"raw": ""
				},
				"description": ""
			},
			"response": []
		}
	]
}
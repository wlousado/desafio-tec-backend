# Desafio Java Back-End

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

Para desenvolver este projeto, foi utilizado as seguintes tecnologias:

- Frameworks
  - Spring Boot
  - Swagger
  - Lombok
- Gerenciador de Dependência
  - Gradle
- DevOps
  - Docker Compose
- Banco de Dado:
  - Mongo
- Mensageria:
  - Kafka

## Instruções para build
```sh
./gradlew clean build
docker-compose up
```
## O que foi feito

- Implementado um versionamento a nível de pacote e controller
- Programação reativa com webflux + mongodb
- Para mensageria foi utilizado Kafka com Avro Serializer para padronização de mensagens
- Testes com o Contexto do Spring
- Separado a API por Camadas de Repository->Service->Facade->Controller
- API RestFull
- Implementado novas Exceptions e GlobalExceptionHandler

## Fluxo da API

1 - Para criar uma pauta ao sistema:

```http
POST /v1/schedule/create
```
```javascript
{
  "name" : string,
  "duration" : integer
}
```
| Status Code | Description |
| :--- | :--- |
| 200 | `OK` |
| 400 | `BAD REQUEST` |
| 409 | `CONFLICT` |
| 500 | `INTERNAL SERVER ERROR` |

2 - Para votar, será necessário adiquirir um id, segue endpoint:

```http
POST /v1/associate/{cpf}
```
| Status Code | Description |
| :--- | :--- |
| 200 | `OK` |
| 400 | `BAD REQUEST` |
| 409 | `CONFLICT` |
| 500 | `INTERNAL SERVER ERROR` |

3 - Realizar a votação

```http
POST /v1/vote/{idAssociate}/{idSchedule}/(sim/nao)
```
| Status Code | Description |
| :--- | :--- |
| 200 | `OK` |
| 400 | `BAD REQUEST` |
| 404 | `NOT FOUND` |
| 409 | `CONFLICT` |
| 500 | `INTERNAL SERVER ERROR` |

# Teste de Performance com JMeter 

```sh 
Usuários Simultâneos: 100
Tempo: 1s
```
<img src="https://i.imgur.com/r7IDJuc.png" width="600"/>
<img src="https://i.imgur.com/uEZLLRA.png" width="600"/>

```sh 
Usuários Simultâneos: 250
Tempo: 1s
```
<img src="https://i.imgur.com/POQfbYw.png" width="600"/>
<img src="https://i.imgur.com/9bjb0V4.png" width="600"/>

```sh 
Usuários Simultâneos: 500
Tempo: 1s
```
<img src="https://i.imgur.com/hCZuyet.png" width="600"/>
<img src="https://i.imgur.com/MhzXTKb.png" width="600"/>


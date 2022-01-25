# Spring Webflux REST Demo Project
프로젝트에서 활용 가능하도록 Webflux REST API 부터 Sample Project 만들어 보았습니다.
Spring Reactive Stack 중 Java 기준으로 Functional Endpoints 를 사용하였습니다.

## Prerequisites

```sh
* Java 11
* Spring Boot 2.6.2
* Spring Webflux
* PostgreSQL
```

## Install

```sql
CREATE TABLE employee (
	seq serial PRIMARY KEY,
	name VARCHAR(30) NOT NULL,
	salary int,
	department VARCHAR(100)
);
```

## Usage

```sh
## PostgreSQL
brew services start postgres
psql -U mongma postgres
\connect my_local

## Webflux & Netty Start
http://localhost:8080
```

## Run tests

```sh
curl -X GET http://localhost:8080/v1/employee/
curl -X GET http://localhost:8080/v1/employee/{id}
curl -X POST http://localhost:8080/v1/employee/add -H ‘Content-Type: application/json’ -d ‘{“name”: “mongma1”, “salary”: 1000, “department”: “dev1”}’
curl -X DELETE http://localhost:8080/v1/employee/delete/{id}
curl -X PUT http://localhost:8080/v1/employee/modify/{id} -H ‘Content-Type: application/json’ -d ‘{“name”: “mongma1”, “salary”: 1000, “department”: “dev1”}’
```

## Author
👤 mongma-n
* GitHub: (https://github.com/mongma-n)

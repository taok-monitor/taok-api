PT_BR

---

# Taok Monitor
Projeto simples que consome dados do Portal da transparência da Prefeitura de Fortaleza 

## Porquê o projeto surgiu ?
Existe muito incentivo para que a população economize água,
Mas será que os órgãos públicos também economizam? 

Vamos tentar responder essa pergunta com o **TaOk Monitor**

## Qual a origem dos dados?
Dados obtidos no portal da transparência da prefeitura de Fortaleza na categoria de [Despesas](https://transparencia.fortaleza.ce.gov.br/index.php/despesa/index), todos os dados são de **PAGAMENTOS** 

## Técnico
Projeto dividido em [taok-front](https://github.com/taok-monitor/) e [taok-api](https://github.com/taok-monitor/taok-backend).

## Tecnologias
- JERSEY
- CDI
- TOMCAT
- PostgreSQL

## Banco de dados
Para executar a plataforma é necessário um banco PostgreSQL, estamos usando Docker, com isso vamos iniciando o PostgreSQL com o seguinte comando:

> $ docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres

depois basta:
- criar um banco
- alterar o arquivo `persistence.xml` informado os dados de acesso.
- alterar o arquivo `persistence.xml` descomentando a flag `<property name="hibernate.hbm2ddl.auto" value="update" />`.

Olha nossa [wiki](https://github.com/taok-monitor/taok-backend/wiki)

---

EN_US

---

# Taok Monitor
Simple project that consumes data from the transparency portal of Fortaleza City Hall

## Why did the project come about?
There is a lot of government incentive for the population to save water, but do public agencies also save?

Let's try answer this question with **TaOk Monitor**

## What is the source of the data?
Data obtained from the transparency portal of Fortaleza City Hall in the category of [Expenses] 
(https://transparencia.fortaleza.ce.gov.br/index.php/despesa/index), all data is from **PAYMENTS** 

## Project structure
The project is organized into [taok-front](https://github.com/taok-monitor/) and [taok-api](https://github.com/taok-monitor/taok-backend).

## Technologies
- JERSEY
- CDI
- TOMCAT
- PostgreSQL

## Database
To run the platform it is necessary to have a PostgreSQL, we are using Docker to help us and you can execute the below command:

> $ docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres

Then you just need:
- Create a database
- Change the file `persistence.xml` informing the access data
- Change the file `persistence.xml` uncommenting the flag `<property name="hibernate.hbm2ddl.auto" value="update" />`.

See our [wiki](https://github.com/taok-monitor/taok-backend/wiki)


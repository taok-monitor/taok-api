PT_BR

---

# Taok Monitor
Projeto simples que consome dados do Portal da transparência da Prefeitura de Fortaleza 

## Porquê o projeto surgiu ?
Existe muito incentivo para que a população economize água,
Mas será que os órgãos públicos também economizam? 

Vamos tentar responder essas pergunta com o **TaOk Monitor**

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

Para executar a plataforma e necessário existir um banco PostgreSQL com isso vamos iniciando o postgresql usando docker:

> $ docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres

depois basta:
- criar um banco
- alterar o arquivo `persistence.xml` informado os dados de acesso.
- alterar o arquivo `persistence.xml` descomentando a flag `<property name="hibernate.hbm2ddl.auto" value="update" />`.

Olha nossa [wiki](https://github.com/taok-monitor/taok-backend/wiki)

---

EN_US

---

translate to English

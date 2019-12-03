create database contatos;

use contatos;

CREATE TABLE pessoa (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Nome varchar(150) NOT NULL,
  Telefone varchar(20),
  Email varchar(100),
  PRIMARY KEY (Id)
);

select * from pessoa;
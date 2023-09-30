create database shoes_happy;

create table usuario(
 id int not null auto_increment primary key,
 usuario varchar(100),
 senha varchar(255)
 );
 
create table cliente(
  id int not null auto_increment primary key,
  nome varchar(200),
  endereco varchar(200),
  modalidade varchar(200),
  cpf varchar(200)
);
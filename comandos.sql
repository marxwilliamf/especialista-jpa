show databases;

create database cadastroevento;

use cadastroevento;

create table evento(id bigint not null auto_increment,
nome varchar(60) not null,
data datetime not null,
primary key(id)
);

insert into evento(id, nome, data) values(null, "Palestra João Silva", sysdate());

select * from evento;
drop database gato;
create database gato;
use gato;

create table Evento (
	pk_evento int not null auto_increment,
    titulo varchar(255) not null default 'Sem Título',
    dia date not null,
    hora_inicio time not null default '00:00',
    hora_fim time not null default '23:59',
    descricao varchar(255) not null default '',
    atividade bit(1) not null default 0,
    feriado bit(1) not null default 0,
    favorito bit(1) not null default 0,
    
    primary key (pk_evento)
);
create table Evento_Semanal (
	pk_evento_semanal int not null auto_increment,
    titulo varchar(255) not null default 'Sem Título',
    dia_semana enum('Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado') not null,
    hora_inicio time not null default '08:00',
    hora_fim time not null default '12:00',
    
    primary key (pk_evento_semanal)
);

insert into Evento values(null, 'Trabalho de POO', '19-11-23', '10:00', '12:00', 'fodeu galere', 1, 0, 0);
insert into Evento_Semanal values(null, 'Álgebra Linear', 'Segunda', '08:00', '10:00');
insert into Evento_Semanal values(null, 'Cálculo I', 'Quinta', '08:00', '10:00');
insert into Evento_Semanal values(null, 'IC2', 'Segunda', '10:00', '12:00');
insert into Evento_Semanal values(null, 'Cálculo I', 'Quarta', '10:00', '12:00');
insert into Evento_Semanal values(null, 'OCD', 'Quarta', '08:00', '10:00');
insert into Evento_Semanal values(null, 'Grupo de Estudos', 'Sábado', '08:00', '10:00');
insert into Evento_Semanal values(null, 'Linguagens e Paradigmas', 'Sexta', '08:00', '10:00');
select * from Evento_Semanal order by dia_semana asc, hora_inicio asc; 	
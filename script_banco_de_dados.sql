#create database gato if not exists;
use gato;

create table Evento (
	pk_evento int not null auto_increment,
    titulo varchar(255) not null default 'Sem Título',
    dia date not null,
    hora_inicio time not null default '00:00',
    hora_fim time not null default '23:59',
    descricao varchar(255) not null default '',
    atividade int not null,
    feriado int not null,
    favorito int not null,
    
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

insert into Evento values(null, 'Trabalho de POO', '19-11-23', '10:00', '12:00', 'Data maxima de entrega', 1, 0, 0);
insert into Evento values(null, 'Partida de Futebol', '19-11-24', '22:00', '23:00', 'Jogo ao vivo', 1, 0, 0);
insert into Evento values(null, 'Viagem de Formatura', '19-12-06', '00:00', '23:00', 'Partiu Praia', 0, 1, 0);
insert into Evento values(null, 'Viagem de Formatura', '19-12-07', '00:00', '23:00', 'Partiu Praia', 0, 1, 0);
insert into Evento values(null, 'Viagem de Formatura', '19-12-08', '00:00', '23:00', 'Partiu Praia', 0, 1, 0);
insert into Evento values(null, 'Evento de Computacao', '19-11-29', '12:00', '17:00', 'Conferencia da Microsoft', 1, 0, 0);
insert into Evento values(null, 'Compra de Natal', '19-12-20', '15:00', '20:00', 'Presentes para a familia', 1, 0, 0);
insert into Evento values(null, 'Jantar na Casa da Sogra', '20-01-14', '18:00', '21:00', 'Dona Maria convidou para um jantar', 1, 0, 0);
insert into Evento values(null, 'Comprar Ovos de Pascoa', '20-04-10', '15:00', '20:00', 'Comprar ovos de pascoa pra familia', 1, 0, 0);
insert into Evento values(null, 'Aniversario Sara', '19-08-10', '12:00', '15:00', 'Aniversario da BFF', 1, 0, 1);
insert into Evento values(null, 'Torneio de Tenis', '18-11-17', '16:00', '20:00', 'Torneio Amador de Tenis', 1, 0, 0);
insert into Evento values(null, 'ENEM', '19-11-03', '08:00', '12:00', 'Prova', 1, 0, 1);
insert into Evento values(null, 'ENEM', '19-11-10', '08:00', '12:00', 'Prova', 1, 0, 1);

insert into Evento_Semanal values(null, 'Natação', 'Segunda', '08:00', '10:00');
insert into Evento_Semanal values(null, 'Inglês', 'Quinta', '16:00', '18:00');
insert into Evento_Semanal values(null, 'IC2', 'Terça', '10:00', '12:00');
insert into Evento_Semanal values(null, 'Cálculo I', 'Quarta', '10:00', '12:00');
insert into Evento_Semanal values(null, 'OCD', 'Quarta', '08:00', '10:00');
insert into Evento_Semanal values(null, 'Grupo de Estudos', 'Sábado', '08:00', '10:00');
insert into Evento_Semanal values(null, 'Almoco na Vó', 'Domingo', '10:00', '14:00');

insert into Evento values(null, 'Confraternização Universal', '13-01-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Confraternização Universal', '14-01-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Confraternização Universal', '15-01-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Confraternização Universal', '16-01-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Confraternização Universal', '17-01-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Confraternização Universal', '18-01-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Confraternização Universal', '19-01-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Confraternização Universal', '20-01-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Confraternização Universal', '21-01-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Confraternização Universal', '22-01-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Confraternização Universal', '23-01-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Confraternização Universal', '24-01-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Confraternização Universal', '25-01-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);

insert into Evento values(null, 'Tiradentes', '13-04-21', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Tiradentes', '14-04-21', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Tiradentes', '15-04-21', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Tiradentes', '16-04-21', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Tiradentes', '17-04-21', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Tiradentes', '18-04-21', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Tiradentes', '19-04-21', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Tiradentes', '20-04-21', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Tiradentes', '21-04-21', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Tiradentes', '22-04-21', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Tiradentes', '23-04-21', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Tiradentes', '24-04-21', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Tiradentes', '25-04-21', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);

insert into Evento values(null, 'Dia do Trabalhador', '13-05-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Dia do Trabalhador', '14-05-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Dia do Trabalhador', '15-05-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Dia do Trabalhador', '16-05-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Dia do Trabalhador', '17-05-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Dia do Trabalhador', '18-05-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Dia do Trabalhador', '19-05-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Dia do Trabalhador', '20-05-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Dia do Trabalhador', '21-05-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Dia do Trabalhador', '22-05-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Dia do Trabalhador', '23-05-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Dia do Trabalhador', '24-05-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Dia do Trabalhador', '25-05-01', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);

insert into Evento values(null, 'Independência do Brasil', '13-09-07', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Independência do Brasil', '14-09-07', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Independência do Brasil', '15-09-07', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Independência do Brasil', '16-09-07', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Independência do Brasil', '17-09-07', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Independência do Brasil', '18-09-07', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Independência do Brasil', '19-09-07', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Independência do Brasil', '20-09-07', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Independência do Brasil', '21-09-07', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Independência do Brasil', '22-09-07', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Independência do Brasil', '23-09-07', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Independência do Brasil', '24-09-07', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Independência do Brasil', '25-09-07', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);

insert into Evento values(null, 'Nossa Senhora Aparecida', '13-10-12', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Nossa Senhora Aparecida', '14-10-12', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Nossa Senhora Aparecida', '15-10-12', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Nossa Senhora Aparecida', '16-10-12', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Nossa Senhora Aparecida', '17-10-12', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Nossa Senhora Aparecida', '18-10-12', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Nossa Senhora Aparecida', '19-10-12', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Nossa Senhora Aparecida', '20-10-12', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Nossa Senhora Aparecida', '21-10-12', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Nossa Senhora Aparecida', '22-10-12', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Nossa Senhora Aparecida', '23-10-12', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Nossa Senhora Aparecida', '24-10-12', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Nossa Senhora Aparecida', '25-10-12', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);

insert into Evento values(null, 'Finados', '13-11-02', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Finados', '14-11-02', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Finados', '15-11-02', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Finados', '16-11-02', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Finados', '17-11-02', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Finados', '18-11-02', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Finados', '19-11-02', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Finados', '20-11-02', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Finados', '21-11-02', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Finados', '22-11-02', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Finados', '23-11-02', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Finados', '24-11-02', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Finados', '25-11-02', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);

insert into Evento values(null, 'Proclamação da República', '13-11-15', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Proclamação da República', '14-11-15', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Proclamação da República', '15-11-15', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Proclamação da República', '16-11-15', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Proclamação da República', '17-11-15', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Proclamação da República', '18-11-15', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Proclamação da República', '19-11-15', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Proclamação da República', '20-11-15', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Proclamação da República', '21-11-15', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Proclamação da República', '22-11-15', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Proclamação da República', '23-11-15', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Proclamação da República', '24-11-15', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Proclamação da República', '25-11-15', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);

insert into Evento values(null, 'Natal', '13-12-25', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Natal', '14-12-25', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Natal', '15-12-25', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Natal', '16-12-25', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Natal', '17-12-25', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Natal', '18-12-25', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Natal', '19-12-25', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Natal', '20-12-25', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Natal', '21-12-25', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Natal', '22-12-25', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Natal', '23-12-25', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Natal', '24-12-25', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);
insert into Evento values(null, 'Natal', '25-12-25', '00:00', '23:00', 'Feriado Oficial', 0, 1, 0);

insert into Evento values(null, 'Revolução Constitucionalista de 1932', '13-07-09', '00:00', '23:00', 'Feriado Estadual de São Paulo', 0, 1, 0);
insert into Evento values(null, 'Revolução Constitucionalista de 1932', '14-07-09', '00:00', '23:00', 'Feriado Estadual de São Paulo', 0, 1, 0);
insert into Evento values(null, 'Revolução Constitucionalista de 1932', '15-07-09', '00:00', '23:00', 'Feriado Estadual de São Paulo', 0, 1, 0);
insert into Evento values(null, 'Revolução Constitucionalista de 1932', '16-07-09', '00:00', '23:00', 'Feriado Estadual de São Paulo', 0, 1, 0);
insert into Evento values(null, 'Revolução Constitucionalista de 1932', '17-07-09', '00:00', '23:00', 'Feriado Estadual de São Paulo', 0, 1, 0);
insert into Evento values(null, 'Revolução Constitucionalista de 1932', '18-07-09', '00:00', '23:00', 'Feriado Estadual de São Paulo', 0, 1, 0);
insert into Evento values(null, 'Revolução Constitucionalista de 1932', '19-07-09', '00:00', '23:00', 'Feriado Estadual de São Paulo', 0, 1, 0);
insert into Evento values(null, 'Revolução Constitucionalista de 1932', '20-07-09', '00:00', '23:00', 'Feriado Estadual de São Paulo', 0, 1, 0);
insert into Evento values(null, 'Revolução Constitucionalista de 1932', '21-07-09', '00:00', '23:00', 'Feriado Estadual de São Paulo', 0, 1, 0);
insert into Evento values(null, 'Revolução Constitucionalista de 1932', '22-07-09', '00:00', '23:00', 'Feriado Estadual de São Paulo', 0, 1, 0);
insert into Evento values(null, 'Revolução Constitucionalista de 1932', '23-07-09', '00:00', '23:00', 'Feriado Estadual de São Paulo', 0, 1, 0);
insert into Evento values(null, 'Revolução Constitucionalista de 1932', '24-07-09', '00:00', '23:00', 'Feriado Estadual de São Paulo', 0, 1, 0);
insert into Evento values(null, 'Revolução Constitucionalista de 1932', '25-07-09', '00:00', '23:00', 'Feriado Estadual de São Paulo', 0, 1, 0); 

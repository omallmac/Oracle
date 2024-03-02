---Crear base de datos TallerSQL---
create database TallerSQL
--Crear tabla llamada Clientes
create table Clientes(
Id char(8) primary key,
Nombre varchar(30),
Apellido varchar(50),
Telefono char(9),
Direccion varchar(50),
Email varchar(30)
);
--Insertar 10 registros
insert into Clientes (Id,Nombre,Apellido,Telefono,Direccion,Email)
values
('A8426514','Maria','Caseres Mallam',948529986,'San Luis','mar258@gmail.com'),
('A8158875','Cesar','Rosas Caina',985426984,'San Miguel','ros558@hotmail.com'),
('A5482276','Juan','Laime Salas',987451259,'San Borja','layme458@gmail.com'),
('A9847514','Lucho','Caseres Allan',985472651,'Lima Centro','allan258@hotmail.com'),
('A5137442','Oscar','Mallma Castillo',994615876,'Surquillo','oscar98@gmail.com'),
('A6264274','Leonel','Turpo Supe',984122557,'Villa Maria','leo66@hotmail.com'),
('A4368765','Jorge','Palacios Luan',985546998,'Villa Salvador','palacios77@gmail.com'),
('A5372435','Rosa','Cubas Julca',948588886,'Surco','rosacubas@gmail.com'),
('A4367573','Eduardo','Soya Milo',948554986,'Callao','soyamilo56@hotmail.com'),
('A8564624','Sonia','Castillo Ramoz',941144986,'Villa Maria','scastillo85@hotmail.com')
--Mostrar los registros
select * from Clientes;
--Actualisar 5 registros de la tabla anteriro
update Clientes 
set Direccion= 'Independencia'
where Direccion like 'S%'
--Eliminar 2 registros
delete Clientes
where Apellido like 'Soya%' or Apellido like 'Pala%'






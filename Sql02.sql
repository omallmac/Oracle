--Productos y Suppliers
select * from Products

select *from Products p
left join Suppliers s
on p.SupplierID=s.SupplierID

select * from Products p 
right join Suppliers s
on p.SupplierID=s.SupplierID
-----------
select * from Products p
inner join Suppliers s
on p.SupplierID=s.SupplierID
-----------
select s.CompanyName,p.ProductName from Products p
inner join Suppliers s
on p.SupplierID=s.SupplierID

--hacer consulta de inner join para yalbas region y territorio
select * from Territories t
inner join Region r
on t.RegionID=r.RegionID
--crear vista(view)
create view view_Customers
as
select * from Customers
where CustomerID like 'A%'
select * from view_Customers
--Crear 5 vistas adicionales
--1
create view view_Fecha
as
select GETDATE()as 'Fecha_Actual'
select * from view_Fecha
--2
create view view_Territory
as
select * from Territories
select * from view_Territory
--3
create view view_ProductCatergory
as
select p.ProductName,c.CategoryName from Products p
inner join Categories c
on p.CategoryID=c.CategoryID
select * from view_ProductCatergory
--4
create view view_DestinoOrder
as
select o.OrderID,c.Country from Orders o
inner JOIN Customers c
on o.CustomerID=c.CustomerID
select * from view_DestinoOrder
--5
create view view_Details
as
select * from [Order Details]
select *from view_Details
--triggers cuando se inserta registros

create table Region_Logs(
	Id int primary key identity(1,1),
	Usuario varchar(100),
	Accion varchar(30),
	fecha datetime
)
select * from Region_Logs
---
create trigger tgr_insert_region
on Region
for insert
as
	insert into Region_Logs values(USER_NAME(),'Insert',CURRENT_TIMESTAMP)

select * from Region
insert into Region values (5,'Este')
insert into Region values (6,'Oeste')
insert into Region values (7,'Norte')
---
create trigger tgr_update_region
on Region
for update
as
	insert into Region_Logs values(USER_NAME(),'Update',CURRENT_TIMESTAMP)


update Region set RegionDescription ='Sur'
Where RegionDescription='Este';
---
Alter trigger tgr_delete_region
on Region
for delete
as
	insert into Region_Logs values(SUSER_NAME(),'Delete',CURRENT_TIMESTAMP)


delete from Region
where RegionID=5;

select * from Region_Logs

select SUSER_NAME()
--Declarar variable

declare @edad int
set @edad=10
if @edad >18
	PRINT 'Eres mayor de edad'
else
	PRINT 'Eres pulpin'
--mostrar el promdeido de 3 numeros
declare @n1 int,@n2 int,@n3 int,@pro int
set @n1=40
set @n2=30
set @n3=20
set @pro=(@n1+@n2+@n3)/3
PrINT 'Tu promedio es de '+cast(@pro as varchar)
--Procedimiento
create procedure sp_sumar(
	 @n1 float,@n2 float,@n3 float
)
as 
	declare @resultado float
	set @resultado=@n1+@n2+@n3
	PRINT 'El resultado de la suma es: '+ cast(@resultado as varchar)
execute sp_sumar @n1=3.3,@n2=4,@n3=5
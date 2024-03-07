select count (*)  'Numero_Total_Filas' from Products
select * from Products
select ProductID as 'Id_del_producto',
ProductName as 'Nombre_del_producto'
from Products
--Practicando Alias
--Utilizando la funcion avg (Promedio)
select avg (UnitPrice) as'Promedio_unitario' from Products
---Mostar el promedio del precio unitario de todos los productos con id de categroa 2
select avg (UnitPrice) from Products
where CategoryID =2 or CategoryID=8
select avg (UnitPrice) from Products
where CategoryID in(2,8)
--mostrar los productos que custan entre 20 y 50
select * from Products
where UnitPrice between 20 and 50
--
select * from Products
where UnitPrice between 50 and 60 and Discontinued =0
--Mostrar la suma totoal de preciso de productos
select sum(unitPrice) as 'Suma_Total' from Products
select categoryId ,avg (UnitPrice)  from Products
group by CategoryID
--mostrar la suma de los precios por producto y agrupar por categoria
select  CategoryID ,sum(unitprice) as 'Precio_por_categoria' from Products
group by CategoryID
--^para negar
select * from Products
where  ProductName like'[A-E]%'
--Valores minimos y maximos 
select min(unitPRICE) from Products
select max(unitPRICE) from Products
--mostrar todos los productos que se encuentren entre el minimo y la mitad del maximo
select * from Products
where UnitPrice 
between
(select min(unitPRICE) from Products)
and
(select max(unitPRICE)/2 from Products)
--union de consultas
select ProductID ,ProductName from Products where CategoryID=1
union
select ProductID,ProductName from Products where CategoryID=2
--use case
select productid , productname,
case
	when categoryid =1 then 'Beverages'
	when categoryid =2 then 'Condiments'
	when categoryid =3 then 'confections'
	when categoryid =4 then 'Dayti products'
	when categoryid =5 then 'Grains?cereal'
	when categoryid =6 then 'Meat/poultry'
	when categoryid =7 then 'produce'
	when categoryid =8 then 'seafoof'
	else 'Falta definir'
end as category
from products
--Utilizar territoris y region mostart todos los customers y cambiar el id de la region su valor qeu se encuentra en la tabla region 
select * from Territories
select * from Region
--	
select  TerritoryDescription,
case
	when regionId =1 then 'Eastem'
	when regionId =2 then 'Westem'
	when regionId =3 then 'Northen'
	when regionId =4 then 'Sourthen'
	else 'Falta definir'
end as ID_Region
from Territories

--Subconsultas
select CustomerID from Customers where CustomerID ='alfki'
select * from Customers where CustomerID =(select CustomerID from Customers where CompanyName='Alfreds Futterkiste' )
select CustomerID from Customers where CompanyName ='Alfreds Futterkiste'
--Ejercicios
select * from Orders
select * from Products
--1-Ovtener el valor unico " AROUT " de customer
select CustomerID from Customers where CustomerID ='AROUT'
--2 obtener el valor unico de "Cactus Comidas para llevar de  Customers"
select CompanyName from Customers where CompanyName ='Cactus Comidas para llevar'
--3 OBTENER EL VALOR UNICO de 'Northwoods Cranberry Sauce' de products
select ProductName from Products where ProductName ='Northwoods Cranberry Sauce'
--4 obtener el valor unico de 'Gustaf's Knackebrod ' de products
select ProductName from Products where ProductName like 'Gustaf%'
--5 obtener el valr unico de 'CHOPS de orders'--
select CustomerID from Orders where OrderID = 10254
select * from Customers where CustomerID =(select CustomerID from Customers where CustomerID ='AROUT' )
select * from Customers where CompanyName =(select CompanyName from Customers where CompanyName ='Cactus Comidas para llevar' )
select * from Products where ProductName =(select ProductName from Products where ProductName ='Northwoods Cranberry Sauce')
select * from Products where ProductName =(select ProductName from Products where ProductName like 'Gustaf%')
select * from Orders where CustomerID =(select CustomerID from Orders where OrderID = 10254) and OrderID =10254
--Insertar y crear nueba tabla prousfbkp de la tabla Porduc
select * into Products_BKP from Products
select * from Products_BKP

begin tran 
	delete from Products_BKP
rollback /*negar*/
commit/*aceptar*/

create  Procedure sp_obtener_fecha_actual
as
	select GETDATE()  as 'Fecha_Actual'

exec sp_obtener_fecha_actual

create procedure Nombre
as
	print 'Mallma Castillo Oscar Antonio'

exec Nombre
--crear procedimientos con variables 

alter procedure sp_obener_saludo(@nombre varchar(200) , @apellido varchar(200))
as
	print @nombre
	print @apellido

execute sp_obener_saludo @nombre = 'Lucas' , @apellido='Casas'


--crear un procedimiento que permmita ingresar un sueldo y muestre el doble del sueldonatlla


create procedure sueldo(@sueldo int)
as
	print @sueldo*2
	
execute sueldo @sueldo=1025

CREATE PROCEDURE Sueldo (@sueldo int)
AS 
    DECLARE @resultado int
    SET @resultado = @sueldo * 2
    PRINT 'Mi sueldo es de ' + CAST(@resultado AS VARCHAR(20))
z=0








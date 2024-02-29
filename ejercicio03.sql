--01.-Listar todos los customers que inicien su id con la letra 'A'

select * from Customers 
where CustomerID like 'A%'

--02.-Listar a todos los customers que sean de los paises France,
--Germany,Swede,

select * from Customers
where Country in ('France' ,'Germany' ,'Sweden')

--3.-Listar todos los orders que el campo ShipRegion  no sea null

select * from Orders
where ShipRegion is not null
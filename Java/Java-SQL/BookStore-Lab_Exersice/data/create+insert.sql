-- drop table if exists transactions;
--
-- drop table  clients cascade ;
-- drop table if exists books cascade ;


create table clients(
    idClients serial primary key,
    CNP varchar(13) unique,
    lastName varchar(250),
    firstName varchar(250),
    age numeric
);

create table books(
                        idBooks serial primary key,
                        title varchar(255),
                        author varchar(255),
                        yearOfPublication date,
                        price numeric
);

create table transactions(
                        idTransactions serial primary key,
                        idBooks serial references books(idBooks) on delete cascade ,
                        idClients serial references clients(idClients) on delete cascade ,
                        amount int,
                        dateOfPurchase date
);

insert into clients (idClients, CNP, lastName, firstName, age) values (1, '1111111111111', 'ln1', 'fn1', 21), (2, '2222222222222', 'ln2', 'fn2', 32),
                                                               (3, '3333333333333', 'ln3', 'fn3', 23),(4, '4444444444444', 'ln4', 'fn4', 44);
insert into books(idBooks,title, author, yearOfPublication, price) values (1, 't1', 'a1', '2019-10-09', 12.99),(2,'t2', 'a2', '2010-12-01', 23.99),
                                                                   (3, 't3', 'a3', '2014-07-21', 34.99), (4,'t4', 'a4', '2020-09-08', 39.99),
                                                                   (5, 't5', 'a1', '1999-03-30', 49.99);
insert into transactions (idTransactions, idBooks, idClients, amount, dateOfPurchase) VALUES (1,2,3,2, '2023-11-12'), (2,1,1,1, '2023-12-12'), (3,2,1,4, '2023-09-08'),
                                                                                 (4,3,4,3, '2023-11-12'), (5,4,2,2, '2023-12-12'), (6,1,3,5, '2023-10-02'),
                                                                                 (7,4,3,3, '2023-09-08'),(8,5,1,1, '2023-11-12'),(9,3,3,2, '2023-01-12'),
                                                                                 (10,4,4,3, '2023-10-02'), (11,1,2,1, '2023-10-02'), (12,5,1,3, '2023-07-08');
select * from transactions;
-- delete from books where idBooks = 5;

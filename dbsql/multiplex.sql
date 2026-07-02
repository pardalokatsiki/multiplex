CREATE DATABASE multiplex;
USE multiplex;

								-- create tables and adequate data types for entries --

CREATE TABLE Users(
	id INT AUTO_INCREMENT PRIMARY KEY,
	username varchar(150)  NOT NULL UNIQUE,
    passwd varchar(50) NOT NULL
);

CREATE TABLE Movies(
	id INT AUTO_INCREMENT PRIMARY KEY,
    title varchar(100) NOT NULL,
    duration_min INT NOT NULL,
    info varchar(300) NOT NULL
);

CREATE TABLE Tickets(
	id INT AUTO_INCREMENT PRIMARY KEY,
    seat_no varchar(30) NOT NULL,
	price decimal(5,2) NOT NULL,
    movies_id INT NOT NULL,
    users_id INT NOT NULL,
    FOREIGN KEY (movies_id) REFERENCES Movies(id),
	FOREIGN KEY (users_id) REFERENCES Users(id)
);



SHOW TABLES;
DESCRIBE Users;

									-- insert into tables default data needed for testing --


insert into Users(id,username,passwd) 
values
(1,'Mary','Mary123!'), 
(2,'Bob','BobaDrink5'),
(3,'Anna','Ann796?'),
(6,'Steve','MynameisSteve3');


insert into Movies
values
(12,'The Godfather',175,'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.'),
(24,'Fight Club',139,'An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more.'),
(36,'The Matrix',136,'A computer hacker discovers that his life is nothing more than an elaborate simulation run by an evil AI.'),
(11,'Star Wars: Episode IV - A New Hope',121,' A farmboy joins forces with various allies to save their galaxy from the evil Empires planet-destroying battle station, while also rescuing the princess leading the rebellion against them.'),
(3,'Spirited Away',124,'During her familys move to the suburbs, a sullen 10-year-old girl wanders into a world ruled by gods, witches and spirits, and where humans are changed into beasts.');


insert into Tickets(id,seat_no,price,movies_id,users_id)
values
(2,'H15',10.5,12,1),
(4,'G6',10.5,24,2),
(6,'F3',10.5,36,3),
(8,'K2',10.5,11,3),
(10,'G12',8.5,3,6);

							-- Some needed changes based on the app's functions --


ALTER TABLE Users add email varchar(255);

alter table users modify email varchar(255) not null;

alter table movies add showday varchar(30);
alter table movies add showtime varchar(30);


select * from movies;
select * from users;


ALTER TABLE Movies MODIFY showtime varchar(30) NOT NULL;
ALTER TABLE Movies MODIFY showday varchar(30) NOT NULL;

UPDATE movies  SET showday = 'Monday', showtime = '19:00' WHERE id = 12;
UPDATE movies  SET showday = 'Tuesday', showtime ='20:00' WHERE id = 3;
UPDATE movies  SET showday = 'Friday', showtime = '21:30' WHERE id = 24;
UPDATE movies  SET showday = 'Saturday', showtime = '21:00' WHERE id = 36;
UPDATE movies  SET showday = 'Sunday', showtime = '20:45' WHERE id = 11;


UPDATE users  SET email = 'MerryMary3@gmail.com' WHERE id = 1;
UPDATE users  SET email = 'BossBob@gmail.com' WHERE id = 2;
UPDATE users  SET email = 'LadyAnna58@yahoo.gr' WHERE id = 3;
UPDATE users  SET email = 'ItsStevemydude@gmail.com' WHERE id = 6;













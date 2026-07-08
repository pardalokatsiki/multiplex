CREATE DATABASE IF NOT EXISTS multiplex;
USE multiplex;

-- 1. ΚΑΘΑΡΙΣΜΟΣ: Απενεργοποίηση ελέγχων για ασφαλή διαγραφή
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Movies;
DROP TABLE IF EXISTS Tickets;

-- Επαναφορά των ελέγχων
SET FOREIGN_KEY_CHECKS = 1;

								-- create tables and adequate data types for entries -- 

CREATE TABLE Users(
	id INT AUTO_INCREMENT PRIMARY KEY,
	username varchar(150)  NOT NULL UNIQUE,
    passwd varchar(50) NOT NULL,
	email varchar(255) NOT NULL
);

CREATE TABLE Movies(
	id INT AUTO_INCREMENT PRIMARY KEY,
    title varchar(100) NOT NULL,
    duration_min INT NOT NULL,
    info varchar(300) NOT NULL,
    showday varchar(30) NOT NULL,
    showtime varchar(30) NOT NULL
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


insert into Users(id,username,passwd, email) 
values
(1,'Mary','Mary123!','MerryMary3@gmail.com'), 
(2,'Bob','BobaDrink5','BossBob@gmail.com'),
(3,'Anna','Ann796?','LadyAnna58@yahoo.gr'),
(6,'Steve','MynameisSteve3','ItsStevemydude@gmail.com');


									-- All movies descriptions have been taken by IMDB(imdb.com)--
                                    -- Used solely for educational purposes --

insert into Movies (id,title,duration_min,info,showday,showtime)
values
(12,'The Godfather',175,'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.', 'Monday','19:00'),
(4,'The Dark Knight',152,'When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham.', 'Tuesday','20:00'),
(24,'Fight Club',139,'An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more.', 'Friday','21:30'),
(36,'The Matrix',136,'A computer hacker discovers that his life is nothing more than an elaborate simulation run by an evil AI.', 'Saturday','21:00'),
(11,'Star Wars: Episode IV - A New Hope',121,' A farmboy joins forces with various allies to save their galaxy from the evil Empires planet-destroying battle station, while also rescuing the princess leading the rebellion against them.', 'Sunday','20:45'),
(3,'Spirited Away',124,'During her familys move to the suburbs, a sullen 10-year-old girl wanders into a world ruled by gods, witches and spirits, and where humans are changed into beasts.', 'Monday','18:00'),
(1,'A Quiet Place',90,'A family struggles for survival in a world invaded by blind alien creatures with ultra-sensitive hearing.','Saturday','22:30'),
(2,'Atonement',123,'Thirteen-year-old fledgling writer Briony Tallis irrevocably changes the course of several lives when she accuses her older sisters lover of a crime he did not commit.','Tuesday','17:00'),
(5,'Doctor Strange',115, 'While on a journey of physical and spiritual healing, a brilliant neurosurgeon is drawn into the world of the mystic arts.','Friday','21:30'),
(6,'Dodgeball', 92, 'A group of misfits enter a Las Vegas dodgeball tournament in order to save their cherished local gym from the onslaught of a corporate health fitness chain.','Monday','18:00'),
(7,'Dune',175,'Paul Atreides arrives on Arrakis after his father accepts the stewardship of the dangerous planet. However, chaos ensues after a betrayal as forces clash to control melange, a precious resource.','Saturday','21:00'),
(8,'How to Lose a Guy in 10 Days', 116, 'An advertising executive and ladies man, to win a big campaign, bets that he can make a woman fall in love with him in 10 days.','Monday','19:00'),
(9,'Kill Bill',111, 'After waking from a four-year coma, a former assassin wreaks vengeance on the team of assassins who betrayed her.','Friday','12:00'),
(10,'Kung Fu Panda',92,'To everyones surprise, including his own, Po, an overweight, clumsy panda, is chosen as protector of the Valley of Peace. His suitability will soon be tested as the valleys arch-enemy is on his way.','Saturday','21:00'),
(22,'Night at the Museum',108,'A newly recruited night security guard at the Museum of Natural History discovers that an ancient curse causes the animals and exhibits on display to come to life and wreak havoc.', 'Saturday','21:00'),
(56,'Obsession',108,'After breaking the mysterious "One Wish Willow" to win his crushs heart, a hopeless romantic finds himself getting exactly what he asked for but soon discovers that some desires come at a dark, sinister price.', 'Saturday','21:00'),
(13,'Open Season',86 ,'Boog, a domesticated 900lb. Grizzly bear, finds himself stranded in the woods 3 days before Open Season.', 'Saturday','21:00'),
(15,'Over the Hedge',83,'A scheming raccoon fools a mismatched family of forest creatures into helping him repay a debt of food', 'Saturday','21:00'),
(17,'Pearl', 103, 'In 1918, a young woman on the brink of madness pursues stardom in a desperate attempt to escape the drudgery, isolation, and lovelessness of life on her parents farm', 'Saturday','21:00'),
(21,'Pride & Prejudice', 129, 'When Elizabeth Bennet meets the handsome Mr. Darcy, she believes he is the last man she could ever marry, but as their lives become intertwined, she finds herself captivated by the man she has sworn to hate forever.', 'Saturday','21:00'),
(23,'Punch-Drunk Love', 95, 'An entrepreneur with social anxiety falls victim to a blackmailing scheme that jeopardizes his chance at true love', 'Saturday','21:00'),
(25,'The Perks of Being a Wallflower', 103, 'A pair of charismatic seniors take socially awkward Charlie under their wing.', 'Saturday','21:00'),
(27,'Twilight', 122, 'When Bella Swan moves to a small town in the Pacific Northwest, she falls in love with Edward Cullen, a mysterious classmate who reveals himself to be a 108-year-old vampire.', 'Saturday','21:00'),
(29,'Zoolander', 90, 'At the end of his career, a clueless fashion model is brainwashed to kill the Prime Minister of Malaysia.', 'Saturday','21:00');




insert into Tickets(id,seat_no,price,movies_id,users_id)
values
(2,'H15',10.5,12,1),
(4,'G6',10.5,24,2),
(6,'F3',10.5,36,3),
(8,'K2',10.5,11,3),
(10,'G12',8.5,3,6);

							
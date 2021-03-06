
CREATE EXTENSION IF NOT EXISTS pgcrypto;

DROP TABLE IF EXISTS users;
CREATE TABLE users(
	Id BIGINT PRIMARY KEY,
	Password VARCHAR(40) NOT NULL,
	FirstName VARCHAR(20) NOT NULL,
	LastName VARCHAR(20) NOT NULL,
	EmailAddress VARCHAR(255) NOT NULL,
	LastAccess TIMESTAMP NOT NULL,
	EnrolDate TIMESTAMP NOT NULL,
	Enable BOOLEAN DEFAULT 'false',
	Type VARCHAR(2) NOT NULL
);
INSERT INTO users(Id,Password,FirstName,LastName,EmailAddress,EnrolDate,LastAccess,Enable,Type)
VALUES (100774848, ENCODE(DIGEST('password','sha1'),'hex'),'John','Doe','jdoe@dcmail.ca','2018-1-21 08:25:25','2021-02-01 18:01:03','true','s');	
	
INSERT INTO users(Id,Password,FirstName,LastName,EmailAddress,EnrolDate,LastAccess,Enable,Type)
VALUES (100774814, ENCODE(DIGEST('testpass','sha1'),'hex'),'Rut','Patel','rutnaineshhkumar.patel@dcmail.ca','2020-01-06 08:25:25','2021-02-01 18:01:03','true','s');

INSERT INTO users(Id,Password,FirstName,LastName,EmailAddress,EnrolDate,LastAccess,Enable,Type)
VALUES (100111111, ENCODE(DIGEST('testpass','sha1'),'hex'),'Mike','Jones','mjones@dcmail.ca','2018-1-21 08:25:25','2021-02-01 18:01:03','true','s');

INSERT INTO users(Id,Password,FirstName,LastName,EmailAddress,EnrolDate,LastAccess,Enable,Type)
VALUES (100123456, ENCODE(DIGEST('johndoe','sha1'),'hex'),'Darren','Puffer','dpuffer@dcmail.ca','2015-1-21 08:25:25','2021-02-01 18:01:03','true','f');

INSERT INTO users(Id,Password,FirstName,LastName,EmailAddress,EnrolDate,LastAccess,Enable,Type)
VALUES (100772825, ENCODE(DIGEST('password123','sha1'),'hex'),'John','Smith','jsmith@dcmail.ca','2017-1-21 08:25:25','2021-02-01 18:01:03','true','f');

INSERT INTO users(Id,Password,FirstName,LastName,EmailAddress,EnrolDate,LastAccess,Enable,Type)
VALUES (100121140, ENCODE(DIGEST('testpass321','sha1'),'hex'),'Jason','Roy','jroy@dcmail.ca','2018-1-21 08:25:25','2021-02-01 18:01:03','true','f');	

SELECT * FROM users;		
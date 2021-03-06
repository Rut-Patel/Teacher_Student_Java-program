
CREATE EXTENSION IF NOT EXISTS pgcrypto;

DROP TABLE IF EXISTS faculty;
CREATE TABLE faculty(
	FacultyId BIGINT PRIMARY KEY,
	SchoolCode VARCHAR(6) NOT NULL,
	SchoolDescription VARCHAR(50) NOT NULL,
	Office VARCHAR(20) NOT NULL,
	Extension INT NOT NULL,
	CONSTRAINT fk_faculty
      FOREIGN KEY(FacultyId) 
	  REFERENCES users(Id)
);

INSERT INTO faculty(FacultyId,SchoolCode,SchoolDescription,Office,Extension)
VALUES (100123456,'BITM','School of Business, IT & Management','H-140',123);

INSERT INTO faculty(FacultyId,SchoolCode,SchoolDescription,Office,Extension)
VALUES (100772825,'BITM','School of Business, IT & Management','H-145',456);

INSERT INTO faculty(FacultyId,SchoolCode,SchoolDescription,Office,Extension)
VALUES (100121140,'BITM','School of Business, IT & Management','H-150',789);

SELECT * FROM faculty;
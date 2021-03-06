
CREATE EXTENSION IF NOT EXISTS pgcrypto;

DROP TABLE IF EXISTS students;
CREATE TABLE students(
	StudentId BIGINT PRIMARY KEY,
	ProgramCode VARCHAR(6) NOT NULL,
	ProgramDescription VARCHAR(50) NOT NULL,
	Year INT NOT NULL,
	CONSTRAINT fk_student
      FOREIGN KEY(StudentId) 
	  REFERENCES users(Id)
);

INSERT INTO students(StudentId,ProgramCode,ProgramDescription,Year)
VALUES (100774848,'CST','Computer System Technology',3);

INSERT INTO students(StudentId,ProgramCode,ProgramDescription,Year)
VALUES (100774814,'CPA','Computer Programmer Analyst',2);

INSERT INTO students(StudentId,ProgramCode,ProgramDescription,Year)
VALUES (100111111,'CST','Computer System Technology',3);

SELECT * FROM students;
CREATE TABLE employee
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    skill VARCHAR(255) NOT NULL
   
);

INSERT INTO employee
(name, skill)
VALUES
('Rajesh','Core Java');

INSERT INTO employee
(name, skill)
VALUES
('Harshitha','Java2EE');
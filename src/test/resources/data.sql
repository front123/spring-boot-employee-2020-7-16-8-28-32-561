
-- insert into EMPLOYEE(name, age, gender) values('wowo',20,'male'),
-- ('good',25,'female'),
-- ('gg',30,'male'),
-- ('ew',40,'female'),
-- ('max',80,'male');


DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS company;
CREATE TABLE employee (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  age INT NOT NULL,
  gender VARCHAR(10) DEFAULT NULL,
  company_id INT Not NULL
);
INSERT INTO employee (name, age, gender,company_id) VALUES
 ('chengcheng','54','male',1),
 ('yuexie','82','female',2),
 ('haifeng','69','female',2),
 ('ang','18','male',1);

 CREATE TABLE company (
   company_id INT AUTO_INCREMENT  PRIMARY KEY,
   name VARCHAR(100) NOT NULL
 );
 INSERT INTO company (name) VALUES('oocl'), ('vbc'), ('qa');
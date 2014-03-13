CREATE DATABASE schoolapp;
USE schoolapp;

CREATE TABLE `SchoolName` (`name` VARCHAR(200) PRIMARY KEY);
CREATE TABLE `Parent`( `pid` INT UNSIGNED AUTO_INCREMENT NOT NULL , `first_name` VARCHAR(50) NOT NULL, `last_name` VARCHAR(50) NOT NULL,  `phone_no` VARCHAR(15) , `email_id` VARCHAR(70) , `profession` VARCHAR(200), `relation` VARCHAR(50), PRIMARY KEY(`pid`,`relation`));
CREATE TABLE `Login` (`username` VARCHAR(50) PRIMARY KEY, `pid` INT UNSIGNED NOT NULL, `password` VARCHAR(50) NOT NULL, `type` CHAR(1) DEFAULT 'P', `no_child` INT(2) DEFAULT '1', `sec_password` VARCHAR(50), `conf_code` VARCHAR(50), FOREIGN KEY(`pid`) REFERENCES `Parent`(`pid`) ON DELETE CASCADE ON UPDATE CASCADE );
CREATE TABLE `Student`(`sid` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, `pid` INT UNSIGNED NOT NULL,`first_name` VARCHAR(50) NOT NULL, `last_name` VARCHAR(50) NOT NULL,`roll` INT(3) NOT NULL, `class` VARCHAR(10) NOT NULL, `section` VARCHAR(2) NOT NULL, `dob` DATE, `phone_no` VARCHAR(15) , `email_id` VARCHAR(70), `address` VARCHAR(100), `achievements` VARCHAR(10000), `interests` VARCHAR(1000), FOREIGN KEY(`pid`) REFERENCES `Parent`(`pid`) ON DELETE CASCADE ON UPDATE CASCADE  );
CREATE TABLE `teacher` (`ID` INT UNSIGNED NOT NULL AUTO_INCREMENT, `NAME`  VARCHAR(60) NOT NULL, `SUBS` VARCHAR(200), `CLASSES` VARCHAR(100), `CONTACT` INT(15), `EMAILID`  VARCHAR(70), PRIMARY KEY (`ID`));
CREATE TABLE `TimeTable`(`class_no` VARCHAR(5) NOT NULL, `section` VARCHAR(3) NOT NULL, `day` CHAR(3) NOT NULL, `FROM` TIME NOT NULL, `TO` TIME NOT NULL,  `ID` INT UNSIGNED , `subject` VARCHAR(30) , PRIMARY KEY(`class_no`, `section`, `day`, `FROM`), FOREIGN KEY(`ID`) REFERENCES `teacher`(`ID`) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE `phoneList` (`ID` INT UNSIGNED NOT NULL AUTO_INCREMENT, `NAME`  VARCHAR(60) NOT NULL, `POST` VARCHAR(30) NOT NULL, `TAG` VARCHAR(30) NOT NULL, `CON_PERSON` VARCHAR(60), `CONTACT` INT(15), `EMAILID`  VARCHAR(70), PRIMARY KEY (`ID`));
Create Table `Class`(`class_no` VARCHAR(5) NOT NULL, `section` CHAR(1) NOT NULL, `subject` VARCHAR(30) NOT NULL, `ID` INT UNSIGNED , `students` INT UNSIGNED NOT NULL , `classteacher` CHAR(1) DEFAULT 'N', PRIMARY KEY(`class_no`,`section`, `subject`), FOREIGN KEY(`ID`) REFERENCES `teacher`(`ID`) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE Table `Event`( `eid` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, `title` VARCHAR(50) NOT NULL, `description` VARCHAR(1000), `start_time` DATETIME NOT NULL, `end_time` DATETIME , `venue` VARCHAR(40), `special_guest` VARCHAR(50), `extra_details` VARCHAR(500));
CREATE TABLE `Attendance`( `sid` INT(10) UNSIGNED NOT NULL, `date` DATE NOT NULL, `status` CHAR(1) NOT NULL, PRIMARY KEY(`sid`,`date`), FOREIGN KEY(`sid`) REFERENCES `Student`(`sid`));
CREATE TABLE `Medico`( `sid` INT UNSIGNED NOT NULL, `blood_group` VARCHAR(3), `height` INT UNSIGNED, `weight` INT UNSIGNED,`eye_sight` VARCHAR(10), `pd` CHAR(3) DEFAULT 'No', `allergies` VARCHAR(500),`injuries` VARCHAR(500), PRIMARY KEY(`sid`), FOREIGN KEY(`sid`) REFERENCES `Student`(`sid`) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE `AcadHistory` (`sid` INT UNSIGNED NOT NULL,`class_no` VARCHAR(5) NOT NULL, `subject` VARCHAR(40), `percentage` VARCHAR(5), `year` INT(4), `school` VARCHAR(60) NOT NULL, `board` VARCHAR(10), PRIMARY KEY(`sid`,`class_no`), FOREIGN KEY(`sid`) REFERENCES `Student`(`sid`) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE `Notifications` (`nid` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, `title` VARCHAR(50) NOT NULL, `description` VARCHAR(1000), `date` DATETIME NOT NULL);
CREATE TABLE `GradeAnalysis` (`sid` INT UNSIGNED NOT NULL PRIMARY KEY, `exam_type` VARCHAR(20), `subject` VARCHAR(40), marks VARCHAR(10),FOREIGN KEY(`sid`) REFERENCES `Student`(`sid`) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE `TimeStampDetails`( `table_name` VARCHAR(10), `time_stamp` TIMESTAMP, `flag` INT(3), PRIMARY KEY(`table_name`));



insert into SchoolName values('DAV PUBLIC SCHOOL, MUMBAI');

insert into Parent values(1,'Jaideep', 'Poonia', '08976208440' , 'poo@gmail.com', 'CEO of Tramplab', 'Father' );
insert into Parent values(2,'rohan', 'Khanna',  '8976322220' , 'rohan@gmail.com', 'CTO of Google', 'uncle'  );


insert into Student values(1, 2,'Rahul', 'Khanna', 1, '1', 'A','26-05-2004', '8976208440' , 'rahul@gmail.com', 'royal city, mumbai', 'Topper in class 6', 'cricket, music'  );
insert into Student values(2, 1,'Raj', 'Khanna',  2, '1', 'A', '26-01-2004', '8976322220' , 'raj@gmail.com', 'royal city, mumbai', 'Topper in class 6', 'cricket, music, movie'  );


insert into phoneList(NAME, POST, TAG, CON_PERSON, CONTACT, EMAILID) values('S Khurana', 'principal', 'administration','roshan', '32324242', 'khuran@gmail.com');
insert into phoneList(NAME, POST,  TAG, CON_PERSON, CONTACT, EMAILID) values('P PANDAY', 'vice-principal', 'administration', 'puja', '32324242', 'panday@gmail.com');
insert into phoneList(NAME, POST,  TAG, CON_PERSON, CONTACT, EMAILID) values('shiva prasad', 'watch man','authority',' ', '43867862', 'prasad@gmail.com');
insert into phoneList(NAME, POST,  TAG, CON_PERSON, CONTACT, EMAILID) values('milind sohni', 'security chief', 'authority','', '999324242', 'sohni@gmail.com');
insert into phoneList(NAME, POST,  TAG, CON_PERSON, CONTACT, EMAILID) values('versa aapte', 'staff-head', 'others',' ', '7878324242', 'aapte@gmail.com');


insert into teacher(NAME, SUBS,  CLASSES, CONTACT, EMAILID) values('MR. Khander', 'PHY, MATH', '1A, 2B', '7878324242', 'aapte@gmail.com');
insert into teacher(NAME, SUBS,  CLASSES, CONTACT, EMAILID) values('MR. bean', 'ENGLISH, Psychology', '1A, 3A', '7878324242', 'RAMESH@gmail.com');
insert into teacher(NAME, SUBS,  CLASSES, CONTACT, EMAILID) values('MR. ZUKERBERG', 'FACEBOOK, WHATSAPP', '1A, 10C', '7878324242', 'ZUKE@gmail.com');
insert into teacher(NAME, SUBS,  CLASSES, CONTACT, EMAILID) values('MR. martin', 'science, MATH', '1A, 4C', '7878324242', 'aapte@gmail.com');
insert into teacher(NAME, SUBS,  CLASSES, CONTACT, EMAILID) values('MR. Kher', 'HINDI, MATH', '1A, 2B', '7878324242', 'aapte@gmail.com');

insert into class values(1,'A', 'PHY', 1, 48, 'N');
insert into class values(1,'A', 'Psychology', 2, 48, 'N');
insert into class values(1,'A', 'FACEBOOK', 3, 48, 'Y');
insert into class values(1,'A', 'WHATSAPP', 3, 48, 'Y');
insert into class values(1,'A', 'MATH', 4, 48, 'N');
insert into class values(1,'A', 'science', 4, 48, 'N');
insert into class values(1,'A', 'HINDI', 5, 48, 'N');


insert into TimeTable values( 1, 'A', 'MON', '08:30:00', '09:25:00', 1, 'PHY');
insert into TimeTable values( 1, 'A', 'MON', '09:30:00', '10:25:00', 2, 'Psychology');
insert into TimeTable values( 1, 'A', 'MON', '10:30:00', '11:25:00', 4, 'science');
insert into TimeTable values( 1, 'A', 'MON', '11:30:00', '12:25:00', 3, 'FACEBOOK');
insert into TimeTable values( 1, 'A', 'TUE', '11:30:00', '12:25:00', 3, 'WHATSAPP');
insert into TimeTable values( 1, 'A', 'WED', '11:30:00', '12:25:00', 3, 'FACEBOOK');
insert into TimeTable values( 1, 'A', 'FRI', '11:30:00', '12:25:00', 3, 'FACEBOOK');
insert into TimeTable values( 1, 'A', 'TUE', '10:30:00', '11:25:00', 4, 'science');
insert into TimeTable values( 1, 'A', 'WED', '10:30:00', '11:25:00', 4, 'MATH');
insert into TimeTable values( 1, 'A', 'FRI', '10:30:00', '11:25:00', 4, 'science');
insert into TimeTable values( 1, 'A', 'TUE', '09:30:00', '10:25:00', 2, 'Psychology');
insert into TimeTable values( 1, 'A', 'WED', '09:30:00', '10:25:00', 2, 'Psychology');
insert into TimeTable values( 1, 'A', 'FRI', '09:30:00', '10:25:00', 2, 'Psychogy');
insert into TimeTable values( 1, 'A', 'TUE', '08:30:00', '09:25:00', 5, 'MATH');
insert into TimeTable values( 1, 'A', 'WED', '08:30:00', '09:25:00', 1, 'PHY');
insert into TimeTable values( 1, 'A', 'FRI', '08:30:00', '09:25:00', 5, 'HINDI');



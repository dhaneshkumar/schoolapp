CREATE DATABASE schoolapp;
USE schoolapp;


CREATE TABLE `Parent`( `pid` INT UNSIGNED AUTO_INCREMENT NOT NULL , `first_name` VARCHAR(50) NOT NULL, `last_name` VARCHAR(50) NOT NULL, `email_id` VARCHAR(70) NOT NULL, `phone_no` VARCHAR(15) NOT NULL, `profession` VARCHAR(200), `relation` VARCHAR(50), PRIMARY KEY(`pid`,`relation`));
CREATE TABLE `Login` (`username` VARCHAR(50) PRIMARY KEY, `pid` INT UNSIGNED NOT NULL, `password` VARCHAR(50) NOT NULL, `type` CHAR(1) DEFAULT 'P', `no_child` INT(2) DEFAULT '1', `sec_password` VARCHAR(50), `conf_code` VARCHAR(50), FOREIGN KEY(`pid`) REFERENCES `Parent`(`pid`) ON DELETE CASCADE ON UPDATE CASCADE );
CREATE TABLE `Student`(`sid` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, `pid` INT UNSIGNED NOT NULL,`first_name` VARCHAR(50) NOT NULL, `last_name` VARCHAR(50) NOT NULL, `dob` DATE NOT NULL, `phone_no` VARCHAR(15) , `email_id` VARCHAR(70), `address` VARCHAR(100), `achievements` VARCHAR(10000), `interests` VARCHAR(1000), FOREIGN KEY(`pid`) REFERENCES `Parent`(`pid`) ON DELETE CASCADE ON UPDATE CASCADE  );
CREATE TABLE `TimeTable`( `class_no` INT(2) UNSIGNED NOT NULL, `section` VARCHAR(3) NOT NULL, `day` CHAR(3) NOT NULL, `FROM` TIME NOT NULL, `TO` TIME NOT NULL,  `teacher` VARCHAR(50), `subject` VARCHAR(30) , PRIMARY KEY(`class_no`, `section`, `day`, `FROM`));
CREATE TABLE `phoneList` (`ID` INT UNSIGNED NOT NULL AUTO_INCREMENT, `NAME`  VARCHAR(60) NOT NULL, `POST` VARCHAR(30) NOT NULL, `CON_PERSON` VARCHAR(60), `CONTACT` INT(15), `EMAILID`  VARCHAR(70), PRIMARY KEY (`ID`));
Create Table `Class`( `class_no` INT(2) UNSIGNED NOT NULL, `section` CHAR(1) NOT NULL, `subject` VARCHAR(30) NOT NULL, `ID` INT UNSIGNED NOT NULL, `classteacher` CHAR(1) DEFAULT 'N', PRIMARY KEY(`class_no`,`section`, `subject`), FOREIGN KEY(`ID`) REFERENCES `PhoneList`(`ID`) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE Table `Event`( `eid` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, `title` VARCHAR(50) NOT NULL, `description` VARCHAR(1000), `start_time` DATETIME NOT NULL, `end_time` DATETIME , `venue` VARCHAR(40), `special_guest` VARCHAR(50), `extra_details` VARCHAR(500));
CREATE TABLE `Attendance`( `sid` INT(10) UNSIGNED NOT NULL, `date` DATE NOT NULL, `status` CHAR(1) NOT NULL, PRIMARY KEY(`sid`,`date`), FOREIGN KEY(`sid`) REFERENCES `Student`(`sid`));
CREATE TABLE `Medico`( `sid` INT UNSIGNED NOT NULL, `blood_group` VARCHAR(3), `height` INT UNSIGNED, `weight` INT UNSIGNED,`eye_sight` VARCHAR(10), `pd` CHAR(3) DEFAULT 'No', `allergies` VARCHAR(500),`injuries` VARCHAR(500), PRIMARY KEY(`sid`), FOREIGN KEY(`sid`) REFERENCES `Student`(`sid`) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE `AcadHistory` (`class_no` INT(2) NOT NULL PRIMARY KEY, `subject` VARCHAR(40), `percentage` VARCHAR(5), `year` INT(4), `school` VARCHAR(60) NOT NULL, `board` VARCHAR(10));
CREATE TABLE `Notifications` (`nid` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, `title` VARCHAR(50) NOT NULL, `description` VARCHAR(1000), `date` DATETIME NOT NULL);
CREATE TABLE `GradeAnalysis` (`sid` INT UNSIGNED NOT NULL PRIMARY KEY, `exam_type` VARCHAR(20), `subject` VARCHAR(40), marks VARCHAR(10),FOREIGN KEY(`sid`) REFERENCES `Student`(`sid`) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE `TimeStampDetails`( `table_name` VARCHAR(10), `time_stamp` TIMESTAMP, `flag` INT(3), PRIMARY KEY(`table_name`));




insert into TimeTable values( 1, 'A', 'MON', '08:30:00', '09:25:00', 'MR. Khander', 'PHY');
insert into TimeTable values( 1, 'A', 'MON', '09:30:00', '10:25:00', 'MR. bean', 'Psychology');
insert into TimeTable values( 1, 'A', 'MON', '10:30:00', '11:25:00', 'MR. martin', 'science');
insert into TimeTable values( 1, 'A', 'MON', '11:30:00', '12:25:00', 'MR. ZUKERBERG', 'FACEBOOK');
insert into TimeTable values( 1, 'A', 'TUE', '11:30:00', '12:25:00', 'MR. ZUKERBERG', 'BOOK');
insert into TimeTable values( 1, 'A', 'WED', '11:30:00', '12:25:00', 'MR. ZUKER', 'FACEBOOK');
insert into TimeTable values( 1, 'A', 'FRI', '11:30:00', '12:25:00', 'MR. ZUKERBERG', 'FACEBOOK');
insert into TimeTable values( 1, 'A', 'TUE', '10:30:00', '11:25:00', 'MR. martin', 'science');
insert into TimeTable values( 1, 'A', 'WED', '10:30:00', '11:25:00', 'MR. martin', 'scice');
insert into TimeTable values( 1, 'A', 'FRI', '10:30:00', '11:25:00', 'MR. main', 'science');
insert into TimeTable values( 1, 'A', 'TUE', '09:30:00', '10:25:00', 'MR. bean', 'Psychology');
insert into TimeTable values( 1, 'A', 'WED', '09:30:00', '10:25:00', 'MR. bean', 'Psychology');
insert into TimeTable values( 1, 'A', 'FRI', '09:30:00', '10:25:00', 'MR. bean', 'Psychogy');
insert into TimeTable values( 1, 'A', 'TUE', '08:30:00', '09:25:00', 'MR. Kher', 'PHY');
insert into TimeTable values( 1, 'A', 'WED', '08:30:00', '09:25:00', 'MR. Khander', 'PHY');
insert into TimeTable values( 1, 'A', 'FRI', '08:30:00', '09:25:00', 'MR. Khaer', 'PHY');

insert into phoneList(NAME, POST, CON_PERSON, CONTACT, EMAILID) values('S Khurana', 'principal', 'roshan', '32324242', 'khuran@gmail.com');
insert into phoneList(NAME, POST, CON_PERSON, CONTACT, EMAILID) values('P PANDAY', 'vice-principal', 'puja', '32324242', 'panday@gmail.com');
insert into phoneList(NAME, POST, CON_PERSON, CONTACT, EMAILID) values('shiva prasad', 'physics-teacher','', '43867862', 'prasad@gmail.com');
insert into phoneList(NAME, POST, CON_PERSON, CONTACT, EMAILID) values('milind sohni', 'IC102-prof', '', '999324242', 'sohni@gmail.com');
insert into phoneList(NAME, POST, CON_PERSON, CONTACT, EMAILID) values('versa aapte', 'sanskrit-teacher', '', '7878324242', 'aapte@gmail.com');






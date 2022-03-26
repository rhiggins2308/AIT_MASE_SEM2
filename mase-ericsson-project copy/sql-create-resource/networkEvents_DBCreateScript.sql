DROP DATABASE IF EXISTS networkEvents;
CREATE DATABASE networkEvents;
USE networkEvents;

DROP TABLE IF EXISTS EventCause;

CREATE TABLE EventCause (
	causeCode int NOT NULL,
	eventId int NOT NULL,
	description varchar(255) NOT NULL,
	PRIMARY KEY (causeCode, eventId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS FailureClass;
CREATE TABLE FailureClass (
  failureClass int PRIMARY KEY,
  description varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS MccMnc;
CREATE TABLE MccMnc (
  mcc int NOT NULL,
  mnc int NOT NULL,
  country varchar(50) NOT NULL,
  operator varchar(50) NOT NULL,
  PRIMARY KEY (mcc , mnc)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS UserEquipment;
CREATE TABLE UserEquipment (
  tac int PRIMARY KEY,
  marketingName varchar(100) NOT NULL,
  manufacturer varchar(50) NOT NULL,
  accessCapability varchar(150) NOT NULL,
  model varchar(50) NOT NULL,
  vendorName varchar(50) NOT NULL,
  ueType varchar(30) ,
  os varchar(30) ,
  inputMode varchar(30)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS BaseData;
CREATE TABLE BaseData (
  id int auto_increment PRIMARY KEY,
  eventDate datetime ,
  eventId int NOT NULL,
  failureClass int,
  ueType int NOT NULL,
  market int NOT NULL,
  operator int NOT NULL,
  cellId int NOT NULL,
  duration int NOT NULL,
  causeCode int,
  neVersion varchar(50) NOT NULL,
  imsi bigint NOT NULL,
  hier3Id bigint NOT NULL,
  hier32Id bigint NOT NULL,
  hier321Id bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SELECT * FROM BaseData;
SELECT * FROM UserEquipment;
SELECT * FROM MccMnc;
SELECT * FROM FailureClass;
SELECT * FROM EventCause;

DROP TABLE IF EXISTS UserType;
CREATE TABLE UserType(
	typeId int AUTO_INCREMENT PRIMARY KEY,
    description varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
    
INSERT INTO UserType (description) values ('Customer Service Representative');
INSERT INTO UserType (description) values ('Support Engineer');
INSERT INTO UserType (description) values ('Network Management Engineer');
INSERT INTO UserType (description) values ('System Administrator');

DROP TABLE IF EXISTS User;
CREATE TABLE User(
	userId	int AUTO_INCREMENT PRIMARY KEY,
    userType int NOT NULL,
    firstName varchar(50) NOT NULL,
    lastName varchar(50) NOT NULL,
    password varchar(20) NOT NULL,
	CONSTRAINT user_fk_userType
    FOREIGN KEY (userType)
    REFERENCES UserType(typeid)
    );

INSERT INTO USER (userType, firstName, lastName, password) values (1, "Customer Rep", "Mike", "password");
INSERT INTO USER (userType, firstName, lastName, password) values (2, "Support", "Jane", "password");
INSERT INTO USER (userType, firstName, lastName, password) values (3, "Network", "Mary", "password");
INSERT INTO USER (userType, firstName, lastName, password) values (4, "Admin", "John", "password");

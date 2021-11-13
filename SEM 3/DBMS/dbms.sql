CREATE DATABASE CREDIT; 

USE CREDIT; 

CREATE TABLE USER 

( 

  user_id INT NOT NULL, 

  Name varchar(30) NOT NULL, 

  Email 

varchar(30) NOT NULL, 

  Address varchar(60) NOT NULL, 

  PRIMARY KEY (user_id) 

); 

desc user; 

CREATE TABLE Login_Account 

( 

  Username varchar(30) NOT NULL, 

  login_id INT NOT NULL, 

  password INT NOT NULL, 

  user_id INT NOT NULL, 

  PRIMARY KEY (login_id), 

  FOREIGN KEY (user_id) REFERENCES USER(user_id) 

); 

desc Login_Account; 
CREATE TABLE Bank 

( 

  Bank_name varchar(30) NOT NULL, 

Branch varchar(30) NOT NULL, 

  IFSC_CODE INT NOT NULL, 

  PRIMARY KEY (IFSC_CODE) 

); 

desc Bank; 
CREATE TABLE CreditCard_Network 

( 

  CC_N_Name varchar(30) NOT NULL, 

PRIMARY KEY (CC_N_Name) 

); 

 desc CreditCard_Network; 
 
 CREATE TABLE links 

( 

  IFSC_CODE INT NOT NULL, 

  CC_N_Name varchar(30) NOT NULL, 

  

PRIMARY KEY (IFSC_CODE, CC_N_Name), 

  FOREIGN KEY (IFSC_CODE) REFERENCES Bank(IFSC_CODE), 

  FOREIGN KEY (CC_N_Name) REFERENCES CreditCard_Network(CC_N_Name) 

); 

desc links; 
CREATE TABLE Joins 

( 

  user_id INT NOT NULL, 

  IFSC_CODE INT NOT NULL, 

  PRIMARY KEY (user_id, IFSC_CODE), 

  FOREIGN KEY (user_id) REFERENCES USER(user_id), 

  FOREIGN KEY (IFSC_CODE) REFERENCES Bank(IFSC_CODE) 

); 

  

desc joins; 
CREATE TABLE USER_Mobile_No 

( 

  Mobile_No INT NOT NULL, 

  user_id INT NOT NULL, 

  PRIMARY KEY (Mobile_No, user_id), 

  FOREIGN KEY (user_id) REFERENCES USER(user_id) 

); 

  

desc user_mobile_no; 
CREATE TABLE Credit_Card 

( 

Name varchar(30) NOT NULL, 

CC_ID INT NOT NULL, 

Balance INT NOT NULL, 

Type varchar(30) NOT NULL, 

Limit_of_CC INT NOT NULL, 

CC_Description varchar(60) NOT NULL, 

  user_id INT NOT NULL, 

IFSC_CODE INT NOT NULL,  

CC_N_Name varchar(30) NOT NULL,  

PRIMARY KEY (CC_ID),  

FOREIGN KEY (user_id) REFERENCES USER(user_id), 

FOREIGN KEY (IFSC_CODE) REFERENCES Bank(IFSC_CODE), 

  FOREIGN KEY (CC_N_Name) REFERENCES CreditCard_Network(CC_N_Name) 

); 

desc credit_card; 
CREATE TABLE Transaction 

( 

  T_ID INT NOT NULL, 

  DateTime INT NOT NULL, 

  Type INT NOT NULL, 

  Amount INT NOT NULL, 

  CC_ID INT NOT NULL, 

  user_id INT NOT NULL, 

  PRIMARY KEY (T_ID), 

  FOREIGN KEY (CC_ID) REFERENCES Credit_Card(CC_ID), 

  FOREIGN KEY (user_id) REFERENCES USER(user_id) 

); 

desc transaction; 
CREATE TABLE OFFERS 

( 

  Description varchar(60) NOT NULL, 

  Offer_ID INT NOT NULL, 

  CC_ID INT NOT NULL, 

  CC_N_Name varchar(30) NOT NULL, 

  PRIMARY KEY (Offer_ID), 

  FOREIGN KEY (CC_ID) REFERENCES Credit_Card(CC_ID), 

  FOREIGN KEY (CC_N_Name) REFERENCES CreditCard_Network(CC_N_Name) 

); 

desc offers; 

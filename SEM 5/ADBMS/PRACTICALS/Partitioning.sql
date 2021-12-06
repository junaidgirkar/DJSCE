CREATE TABLE Sales ( cust_id INT NOT NULL, name VARCHAR(40),   
store_id VARCHAR(20) NOT NULL, bill_no INT NOT NULL,   
bill_date DATE PRIMARY KEY NOT NULL, amount DECIMAL(8,2) NOT NULL)   
PARTITION BY RANGE (year(bill_date))(   
PARTITION p0 VALUES LESS THAN (2016),   
PARTITION p1 VALUES LESS THAN (2017),   
PARTITION p2 VALUES LESS THAN (2018),   
PARTITION p3 VALUES LESS THAN (2020)); 

INSERT INTO Sales VALUES   
(1, 'Mike', 'S001', 101, '2015-01-02', 125.56),   
(2, 'Robert', 'S003', 103, '2015-01-25', 476.50),   
(3, 'Peter', 'S012', 122, '2016-02-15', 335.00),   
(4, 'Joseph', 'S345', 121, '2016-03-26', 787.00),   
(5, 'Harry', 'S234', 132, '2017-04-19', 678.00),   
(6, 'Stephen', 'S743', 111, '2017-05-31', 864.00),   
(7, 'Jacson', 'S234', 115, '2018-06-11', 762.00),   
(8, 'Smith', 'S012', 125, '2019-07-24', 300.00),   
(9, 'Adam', 'S456', 119, '2019-08-02', 492.20);  

SELECT * FROM Sales;  

SELECT TABLE_NAME, PARTITION_NAME, TABLE_ROWS, AVG_ROW_LENGTH, DATA_LENGTH  
FROM INFORMATION_SCHEMA.PARTITIONS  
WHERE TABLE_SCHEMA = 'partitioning' AND TABLE_NAME = 'Sales';  

# LIST PARTITIONING

CREATE TABLE Stores (   
    cust_name VARCHAR(40),   
    bill_no VARCHAR(20) NOT NULL,   
    store_id INT PRIMARY KEY NOT NULL,   
    bill_date DATE NOT NULL,   
    amount DECIMAL(8,2) NOT NULL  
)  
PARTITION BY LIST(store_id) (   
PARTITION pEast VALUES IN (101, 103, 105),   
PARTITION pWest VALUES IN (102, 104, 106),   
PARTITION pNorth VALUES IN (107, 109, 111),   
PARTITION pSouth VALUES IN (108, 110, 112));  

INSERT INTO Stores VALUES
("Mike", "1", 101, "2015-01-25", 100.00),
("Joseph", "2", 102, "2015-01-25", 100.00),
("Robert", "3", 103, "2015-01-25", 100.00),
("Peter", "4", 104, "2015-01-25", 100.00),
("Joseph", "5", 105, "2015-01-25",100.00),
("Harry", "6", 106, "2015-01-25", 100.00),
("Jacson", "7", 107, "2015-01-25", 100.00),
("Smith", "8", 108, "2015-01-25", 100.00),
("Adam", "9", 110, "2015-01-25", 100.00);

SELECT TABLE_NAME, PARTITION_NAME, TABLE_ROWS, AVG_ROW_LENGTH, DATA_LENGTH  
FROM INFORMATION_SCHEMA.PARTITIONS  
WHERE TABLE_SCHEMA = 'partitioning' AND TABLE_NAME = 'Stores'; 

# HASH PARTITIONING
CREATE TABLE Stores2 (   
    cust_name VARCHAR(40),   
    bill_no VARCHAR(20) NOT NULL,   
    store_id INT PRIMARY KEY NOT NULL,   
    bill_date DATE NOT NULL,   
    amount DECIMAL(8,2) NOT NULL  
)  
PARTITION BY HASH(store_id)  
PARTITIONS 4;

INSERT INTO Stores2 VALUES
("Mike", "1", 101, "2015-01-25", 100.00),
("Joseph", "2", 102, "2015-01-25", 100.00),
("Robert", "3", 103, "2015-01-25", 100.00),
("Peter", "4", 104, "2015-01-25", 100.00),
("Joseph", "5", 105, "2015-01-25",100.00),
("Harry", "6", 106, "2015-01-25", 100.00),
("Jacson", "7", 107, "2015-01-25", 100.00),
("Smith", "8", 108, "2015-01-25", 100.00),
("Adam", "9", 110, "2015-01-25", 100.00);

SELECT TABLE_NAME, PARTITION_NAME, TABLE_ROWS, AVG_ROW_LENGTH, DATA_LENGTH  
FROM INFORMATION_SCHEMA.PARTITIONS  
WHERE TABLE_SCHEMA = 'partitioning' AND TABLE_NAME = 'Stores2'; 

# RANGE COLUMN PARTITIONING
CREATE TABLE test_part (A INT, B CHAR(5), C INT, D INT)  
PARTITION BY RANGE COLUMNS(A, B, C)   
 (PARTITION p0 VALUES LESS THAN (50, 'test1', 100),   
 PARTITION p1 VALUES LESS THAN (100, 'test2', 200),   
 PARTITION p2 VALUES LESS THAN (150, 'test3', 300),   
 PARTITION p3 VALUES LESS THAN (MAXVALUE, MAXVALUE, MAXVALUE)); 
 
 INSERT INTO test_part VALUES
 (10, 'a', 50, 3),
 (30, 'test1', 150, 3),
 (55, 'test1', 175, 3),
 (123, 'test2', 233, 3),
 (160, 'test4', 333, 3);
 
SELECT TABLE_NAME, PARTITION_NAME, TABLE_ROWS, AVG_ROW_LENGTH, DATA_LENGTH  
FROM INFORMATION_SCHEMA.PARTITIONS  
WHERE TABLE_SCHEMA = 'partitioning' AND TABLE_NAME = 'test_part'; 

# LIST COLUMN PARTITIONING
CREATE TABLE AgentDetail (   
agent_id VARCHAR(10),  
agent_name VARCHAR(40),   
city VARCHAR(10))   
PARTITION BY LIST COLUMNS(agent_id) (   
PARTITION pNewyork VALUES IN('A1', 'A2', 'A3'),   
PARTITION pTexas VALUES IN('B1', 'B2', 'B3'),   
PARTITION pCalifornia VALUES IN ('C1', 'C2', 'C3'));  

INSERT INTO AgentDetail VALUES
('A1', 'DummyName', 'CityName'),
('A2', 'DummyName', 'CityName'),
('A3', 'DummyName', 'CityName'),
('B1', 'DummyName', 'CityName'),
('B2', 'DummyName', 'CityName'),
('B3', 'DummyName', 'CityName'),
('C1', 'DummyName', 'CityName'),
('C2', 'DummyName', 'CityName'),
('C3', 'DummyName', 'CityName'),
('A1', 'DummyName', 'CityName'),
('C2', 'DummyName', 'CityName'),
('B1', 'DummyName', 'CityName');

SELECT TABLE_NAME, PARTITION_NAME, TABLE_ROWS, AVG_ROW_LENGTH, DATA_LENGTH  
FROM INFORMATION_SCHEMA.PARTITIONS  
WHERE TABLE_SCHEMA = 'partitioning' AND TABLE_NAME = 'AgentDetail'; 

# KEY PARTITIONING
CREATE TABLE AgentDetail2 (   
    agent_id INT NOT NULL PRIMARY KEY,  
    agent_name VARCHAR(40)  
)  
PARTITION BY KEY()  
PARTITIONS 2;  

INSERT INTO AgentDetail2 VALUES
(1, "Name"),
(2, "Name"),
(3, "Name"),
(4, "Name"),
(5, "Name"),
(6, "Name"),
(7, "Name"),
(8, "Name"),
(9, "Name"),
(10, "Name"),
(11, "Name");

SELECT TABLE_NAME, PARTITION_NAME, TABLE_ROWS, AVG_ROW_LENGTH, DATA_LENGTH  
FROM INFORMATION_SCHEMA.PARTITIONS  
WHERE TABLE_SCHEMA = 'partitioning' AND TABLE_NAME = 'AgentDetail2';


# SUBPARTITIONING
CREATE TABLE Person (   
    id INT NOT NULL,  
    name VARCHAR(40),  
    purchased DATE,
    PRIMARY KEY (`id`, `purchased`)
)  
 PARTITION BY RANGE( YEAR(purchased) )  
    SUBPARTITION BY HASH( TO_DAYS(purchased) )  
    SUBPARTITIONS 2 (  
        PARTITION p0 VALUES LESS THAN (2015),  
        PARTITION p1 VALUES LESS THAN (2020),  
        PARTITION p2 VALUES LESS THAN MAXVALUE  
    );  
    
INSERT INTO Person VALUES
(1, "Name", "2013-01-13"),
(2, "Name2", "2014-04-22"),
(3, "Name3", "2015-02-25"),
(4, "Name4", "2018-05-05"),
(5, "Name5", "2020-02-04");

SELECT PARTITION_NAME, TABLE_ROWS, AVG_ROW_LENGTH, DATA_LENGTH    
FROM INFORMATION_SCHEMA.PARTITIONS  
WHERE TABLE_SCHEMA = 'partitioning' AND TABLE_NAME = 'Person';  
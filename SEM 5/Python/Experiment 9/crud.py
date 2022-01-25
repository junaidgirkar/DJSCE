
# Import module
import sqlite3
  
# Connecting to sqlite
conn = sqlite3.connect('hello.db')
  
# Creating a cursor object using
# the cursor() method
mycursor = conn.cursor()

mycursor.execute("create table student (stud_id INT(6) PRIMARY KEY, name VARCHAR(20), marks INT);")

for x in mycursor:
    print(x)
mycursor.execute("alter table student add column division VARCHAR(1);")
print('(attribute, type, null, key, default, extra)')

for x in mycursor:
    print(x)
mycursor.execute("insert into student (stud_id, name, marks, division) values (1,'Harry',98,'A');")
mycursor.execute("insert into student (stud_id, name, marks, division) values (2,'James',37,'B');")
mycursor.execute("insert into student (stud_id, name, marks, division) values (3,'Draco',92,'C');")
conn.commit()
mycursor.execute("select * from student;")
myresult = mycursor.fetchall()
for x in myresult:
    print(f'{x[0]}\t{x[1]}\t{x[2]}\t{x[3]}')
mycursor.execute("update student set marks = 99 where name = 'Harry';")
conn.commit()
print(mycursor.rowcount, "record(s) affected")
mycursor.execute("select * from student;")
myresult = mycursor.fetchall()
for x in myresult:
    print(f'{x[0]}\t{x[1]}\t{x[2]}\t{x[3]}')
mycursor.execute("delete from student where division = 'B';")
conn.commit()
print(mycursor.rowcount, "record(s) deleted")
mycursor.execute("select * from student;")
myresult = mycursor.fetchall()
for x in myresult:
    print(f'{x[0]}\t{x[1]}\t{x[2]}\t{x[3]}')
# mycursor.execute("drop table if exists student;")
for x in mycursor:
    print(x)
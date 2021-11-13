class Student:

    def __init__(self,name,id):
        self.id=id
        self.name = name




    def details(self):
        print("Hi, my name is "+ str(self.name) + " and my roll number is "+str(self.id))




alpha = Student("Larry", 20)
beta = Student("Harry", 50)

alpha.details()
beta.details()
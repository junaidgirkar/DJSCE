class Vehicle:
    
    def __init__(self, name, mileage, top_speed):
        self.mileage = mileage
        self.name = name
        self.top_speed = top_speed
    
    def max_running(self, litres):
        self.range = self.mileage * litres
        self.time = int(self.range) / int(self.top_speed)
        print("Vehicle ", self.name, " takes ", self.time, " hours to finish ", litres, " litres of fuel while running at a top speed of ", self.top_speed, " kmph")
    
    def details(self):
        print("My name is ", self.name, "\nMy mileage is ", self.mileage, "\nMy Top Speed is ", self.top_speed)

class Car(Vehicle):
    
    def __init__(self, name, mileage, top_speed, capacity):
        self.capacity = capacity
        super().__init__(name, mileage, top_speed)
    
    def type(self):
        print("I am a Car")
    
    def details(self):
        print("My name is ", self.name," and I am a CAR\nMy mileage is ", self.mileage, "\nMy Top Speed is ", self.top_speed, "\nI can seat ", self.capacity, " people")

class Truck(Vehicle):
    
    def __init__(self, name, mileage, top_speed, capacity):
        self.capacity = capacity
        super().__init__(name, mileage, top_speed)
    
    def type(self):
        print("I am a Truck")
    
    def details(self):
        print("My name is ", self.name," and I am a TRUCK\nMy mileage is ", self.mileage, "\nMy Top Speed is ", self.top_speed, "\nI can seat ", self.capacity, " people")

alpha = Car(name="Alpha", mileage="10", top_speed="200", capacity=5)
beta = Truck(name="Beta", mileage=5, top_speed=100, capacity=20)

alpha.type()
alpha.details()
print()
beta.type()
beta.details()
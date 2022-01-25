class Node:

    def __init__(self, data):
        self.data = data
        self.next = None


class Queue:

    def __init__(self):
        self.front = None
        self.rear = None

    def enqueue(self, data):
        y = Node(data)
        if self.rear is None:
            self.front = y
            self.rear = y
        else:
            self.rear.next = y
            self.rear = y

    def dequeue(self):
        if self.front is None:
            print("Queue Underflow")
            return -1
        elif self.front is self.rear:
            val = self.front.data
            cur = self.front
            self.front = None
            self.rear = None
        else:
            val = self.front.data
            cur = self.front
            self.front = self.front.next
        del cur
        return val

    def display(self):
        print("Queue: ")
        current = self.front
        if self.front != None:
            print(self.front.data, end=' ')
        else:
            print("Empty")
            return
        while current.next != None:
            print("->", end=' ')
            print(current.next.data, end=' ')
            current = current.next



def exec():
    x = Queue()
    option = 0
    while option != 4:
        print("\nEnter an option:\n1)Enqueue\n2)Dequeue")
        print("3)Display\n4)Exit\n")
        option = int(input())
        if option == 1:
            data = int(input("Enter the element: "))
            x.enqueue(data)
        elif option == 2:
            y = x.dequeue()
            if y != -1:
                print("Dequeued Element: " + str(y))
        elif option == 3:
            x.display()
        elif option != 4:
            print("Invalid entry. Retry")

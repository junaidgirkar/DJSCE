class Node:

    def __init__(self, data):
        self.data = data
        self.next = None


class LinkedList:

    def __init__(self):
        self.start = None

    def insert_beg(self, data):
        y = Node(data)
        if self.start is None:
            self.start = y
        else:
            y.next = self.start
            self.start = y

    def insert_after(self, data, val):
        y = Node(data)
        current = self.start
        while current.next is not None and current.data is not val:
            current = current.next
        if current.data is val:
            y.next = current.next
            current.next = y
        else:
            print("Value " + str(val) + " not found.")

    def delete(self, data):
        current = self.start
        if self.start.data is data:
            self.start = self.start.next
            del current
            return
        while current.next is not None and current.data is not data:
            preptr = current
            current = current.next
        if current.data is data:
            preptr.next = current.next
            del current
        else:
            print("Value " + str(data) + " not found.")

    def display(self):
        print("Linked List: ")
        current=self.start
        while current is not None:
            print(f"Data:{current.data}")
            current=current.next
            


def exec():
    x = LinkedList()
    option = 0
    while option != 5:
        print("\nEnter an option:\n1)Insert at Beg\n2)Insert after a particular value")
        print("3)Delete a particular value\n4)Display\n5)Exit\n")
        option = int(input())
        if option == 1:
            data = int(input("Enter the element: "))
            x.insert_beg(data)
        elif option == 2:
            val = int(input("Enter the value after which data is to be inserted: "))
            data = int(input("Enter the element: "))
            x.insert_after(data, val)
        elif option == 3:
            data = int(input("Enter the value to be deleted: "))
            x.delete(data)
        elif option == 4:
            x.display()
        elif option != 5:
            print("Invalid entry. Retry")

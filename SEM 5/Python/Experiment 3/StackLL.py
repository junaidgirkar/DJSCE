class Node:

    def __init__(self, data):
        self.data = data
        self.next = None


class Stack:

    def __init__(self):
        self.top = None

    def push(self, data):
        y = Node(data)
        if self.top is None:
            self.top = y
        else:
            y.next = self.top
            self.top = y

    def pop(self):
        if self.top is None:
            print("Stack Underflow")
            return -1
        else:
            val = self.top.data
            cur = self.top
            self.top = self.top.next
            del cur
            return val

    def peek(self):
        if self.top is None:
            print("Empty.")
            return
        else:
            print("Top of Stack: " + str(self.top.data))
            return

    def display(self):
        print("Stack: ")
        current = self.top
        if self.top is not None:
            print(self.top.data, end=' ')
        else:
            print("Empty.")
        while current.next is not None:
            print("<-", end=' ')
            print(current.next.data, end=' ')
            current = current.next



def exec():
    x = Stack()
    option = 0
    while option is not 5:
        print("\nEnter an option:\n1)Push\n2)Pop")
        print("3)Peek\n4)Display\n5)Exit\n")
        option = int(input())
        if option is 1:
            data = int(input("Enter the element: "))
            x.push(data)
        elif option is 2:
            y = x.pop()
            if y is not -1:
                print("Popped Element: " + str(y))
        elif option is 3:
            x.peek()
        elif option is 4:
            x.display()
        elif option is not 5:
            print("Invalid entry. Retry")

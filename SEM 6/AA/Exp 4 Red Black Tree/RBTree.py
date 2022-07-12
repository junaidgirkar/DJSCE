import sys

# Node creation
class Node():
    def __init__(self, item):
        self.item = item
        self.parent = None
        self.left = None
        self.right = None
        self.color = 1


class RedBlackTree():
    def __init__(self):
        self.TNULL = Node(0)
        self.TNULL.color = 0
        self.TNULL.left = None
        self.TNULL.right = None
        self.root = self.TNULL




    # Balance the tree after insertion
    def fix_insert(self, k):
        while k.parent.color == 1:
            if k.parent == k.parent.parent.right:
                u = k.parent.parent.left
                if u.color == 1:
                    print("Case 1: Uncle is Red => performing only Recoloring:")
                    u.color = 0
                    k.parent.color = 0
                    k.parent.parent.color = 1
                    k = k.parent.parent
                else:
                    print("Case 3: Uncle is black => perform rotation followed by recoloring")
                    if k == k.parent.left:
                        print("Rotating Right")
                        k = k.parent
                        self.right_rotate(k)
                    k.parent.color = 0
                    k.parent.parent.color = 1
                    print("Rotating left")
                    self.left_rotate(k.parent.parent)
                    
            else:
                u = k.parent.parent.right

                if u.color == 1:
                    print("Case 1: Uncle is Red => perform only Recoloring")
                    u.color = 0
                    k.parent.color = 0
                    k.parent.parent.color = 1
                    k = k.parent.parent
                    
                else:
                    print("Case 3: Uncle is Black => peroform rotation followed by recoloring:")
                    if k == k.parent.right:
                        k = k.parent
                        print("Rotating Left")
                        self.left_rotate(k)
                    k.parent.color = 0
                    k.parent.parent.color = 1
                    print("Rotating Right")
                    self.right_rotate(k.parent.parent)
            if k == self.root:
                break
        self.root.color = 0

    # Printing the tree
    def __print_helper(self, node, indent, last):
        if node != self.TNULL:
            sys.stdout.write(indent)
            if last:
                sys.stdout.write("R---> ")
                indent += "     "
            else:
                sys.stdout.write("L---> ")
                indent += "|    "

            s_color = "RED" if node.color == 1 else "BLACK"
            print(str(node.item) + "(" + s_color + ")")
            self.__print_helper(node.left, indent, False)
            self.__print_helper(node.right, indent, True)
   

    def left_rotate(self, x):
        y = x.right
        x.right = y.left
        if y.left != self.TNULL:
            y.left.parent = x

        y.parent = x.parent
        if x.parent == None:
            self.root = y
        elif x == x.parent.left:
            x.parent.left = y
        else:
            x.parent.right = y
        y.left = x
        x.parent = y

    def right_rotate(self, x):
        y = x.left
        x.left = y.right
        if y.right != self.TNULL:
            y.right.parent = x

        y.parent = x.parent
        if x.parent == None:
            self.root = y
        elif x == x.parent.right:
            x.parent.right = y
        else:
            x.parent.left = y
        y.right = x
        x.parent = y

    def insert(self, key):
        node = Node(key)
        node.parent = None
        node.item = key
        node.left = self.TNULL
        node.right = self.TNULL
        node.color = 1

        y = None
        x = self.root

        while x != self.TNULL:
            y = x
            if node.item < x.item:
                x = x.left
            else:
                x = x.right

        node.parent = y
        if y == None:
            self.root = node
        elif node.item < y.item:
            y.left = node
        else:
            y.right = node

        if node.parent == None:
            node.color = 0
            return

        if node.parent.parent == None:
            return

        self.fix_insert(node)

    def get_root(self):
        return self.root

    def print_tree(self):
        self.__print_helper(self.root, "", True)


if __name__ == "__main__":
    bst = RedBlackTree()
    n = int(input("Enter Number of nodes: "))
    for i in range(n):
        value  =  int(input("\nEnter value of Node {} : ".format(i+1)))
        bst.insert(value)

    bst.print_tree()
    
    while(True):
        print("Menu:\n1.Add new Node\n2.Exit:")
        option = int(input("Enter Option: "))
        if option == 1:
            add_new = int(input("Enter new Node: "))
            bst.insert(add_new)
            bst.print_tree()
        if option == 2:
            print("Exit")
            break
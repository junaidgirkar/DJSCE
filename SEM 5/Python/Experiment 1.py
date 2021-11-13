# Python program to demonstrate
# Creation of Array
 
# importing "array" for array creations
import array as arr
 
# creating an array with integer type
a = arr.array('i', [1, 2, 3])
 
# printing original array
print ("The new created array is : ", end =" ")
for i in range (0, 3):
    print (a[i], end =" ")
print()

# array with float type
b = arr.array('d', [2.5, 3.2, 3.3])
 
print ("Array before insertion : ", end =" ")
for i in range (0, 3):
    print (b[i], end =" ")
print()
 
# adding an element using append()
b.append(4.4)
 
print ("Array after insertion : ", end =" ")
for i in (b):
    print (i, end =" ")
print()

# accessing element of array
print("Access element index 0: ", a[0])

# initializing array with array values
# initializes array with signed integers
arr = arr.array('i', [1, 2, 3, 1, 5, 6, 8, 10, 12, 55, 99, 2])
 
# printing original array
print ("The new created array is : ", end ="")
for i in range (0, 5):
    print (arr[i], end =" ")
 
print ("\r")
 
# using pop() to remove element at 2nd position
print ("The popped element is : ", end ="")
print (arr.pop(2))
 
# printing array after popping
print ("The array after popping is : ", end ="")
for i in range (0, 4):
    print (arr[i], end =" ")
 
print("\r")
 
# using remove() to remove 1st occurrence of 1
arr.remove(1)
 
# printing array after removing
print ("The array after removing is : ", end ="")
for i in range (0, 3):
    print (arr[i], end =" ")

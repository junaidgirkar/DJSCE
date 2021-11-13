def check_if_even(number):
    if(number % 2 == 0):
        return True
    else:
        return False
print("QUESTION 1")
print("4 is even: ",check_if_even(4))
print("9 is add: ",check_if_even(9))

def calculate_factorial(number):
    product = 1
    for i in range(1, number+1):
        product *= i
    return product
print("\nQUESTION 2")

print("Factorial of 5 is ", calculate_factorial(5))

def arithmatic_operations(num1, num2):
    addition = num1 + num2
    multiplication = num1 * num2
    division = num1 / num2
    subtraction = num1 - num2

    return addition, subtraction, multiplication, division
print("\nQUESTION 3")
num1 = int(input("Enter the First Number: "))
num2 = int(input("Enter the Second number: "))
addition, subtraction, multiplication, division = arithmatic_operations(num1, num2)
print("Addition: ", addition, "\nSubtraction: ", subtraction, "\nMultiplication: ", multiplication, "\nDivision: ", division)


def check_if_prime(number):
    prime = False
    for i in range(1, number//2 + 1):
        if(number%i == 0):
            prime = True
    return prime
print("\nQUESTION 4")
print("7 is prime: ", check_if_prime(7))


def prime_numbers(num1, num2):
    print("Prime Numbers in the range ", num1, " - ", num2)  

    for num in range(num1,num2 + 1):
        if num > 1:  
            for i in range(2,num):  
                if (num % i) == 0:  
                    break  
            else:  
                print(num)

print("\nQUESTION 5")
num1 = int(input("Enter lower range: "))  
num2 = int(input("Enter upper range: ")) 
prime_numbers(num1, num2)   
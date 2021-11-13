import java.util.Scanner;

class Recursion {

    static int gcd(int a, int b) {

        if (a == 0)
            return b;
        if (b == 0)
            return a;

        if (a == b)
            return a;

        if (a > b)
            return gcd(a - b, b);
        return gcd(a, b - a);
    }

    static int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }

    static int power(int x, int y) {
        if (y == 0)
            return 1;
        else if (y % 2 == 0)
            return power(x, y / 2) * power(x, y / 2);
        else
            return x * power(x, y / 2) * power(x, y / 2);
    }

    static void Fibonacci(int N) {
        int num1 = 0, num2 = 1;

        int counter = 0;

        while (counter < N) {

            System.out.print(num1 + " ");

            int num3 = num2 + num1;
            num1 = num2;
            num2 = num3;
            counter = counter + 1;
        }
    }

    static int reversDigits(int num) {
        int rev_num = 0;
        while (num > 0) {
            rev_num = rev_num * 10 + num % 10;
            num = num / 10;
        }
        return rev_num;
    }

    static int calculateSum(int n) {
        int sum = 0, i;
        for (i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    static int sum_of_digit(int n) {
        if (n == 0)
            return 0;
        return (n % 10 + sum_of_digit(n / 10));
    }

    // Driver method
    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println(
                    "1. GCD\n2. LCM\n3. Exponential\n4. Fibonacci Series\n5. Reverse Number\n6. Sum of numbers\n7. Sum of digits\n8. Exit");
            choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.print("Enter the first number : ");
                    int a = sc.nextInt();
                    System.out.println("\n");
                    System.out.println("Enter the second number : ");
                    int b = sc.nextInt();
                    System.out.println("GCD of " + a + " and " + b + " is " + gcd(a, b));
                    System.out.println('\n');
                    break;
                }

                case 2: {
                    System.out.print("Enter the first number : ");
                    int a = sc.nextInt();
                    System.out.println("\n");
                    System.out.println("Enter the second number : ");
                    int b = sc.nextInt();
                    System.out.println("LCM of " + a + " and " + b + " is " + lcm(a, b));
                    System.out.println('\n');
                    break;
                }

                case 3: {
                    System.out.print("Enter the base number : ");
                    int a = sc.nextInt();
                    System.out.println('\n');
                    System.out.println("Enter the power : ");
                    int b = sc.nextInt();
                    System.out.println(a + " raised to " + b + " gives " + power(a, b));
                    System.out.println('\n');
                    break;
                }

                case 4: {
                    System.out.print("Enter the count of fibonacci numbers : ");
                    int a = sc.nextInt();
                    System.out.println('\n');
                    Fibonacci(a);
                    System.out.println('\n');
                    break;
                }

                case 5: {
                    System.out.print("Enter the number you want to reverse : ");
                    int a = sc.nextInt();
                    System.out.println('\n');
                    System.out.println("Reverse of no. is " + reversDigits(a));
                    System.out.println('\n');
                    break;
                }

                case 6: {
                    System.out.print("Enter the number you the sum upto : ");
                    int a = sc.nextInt();
                    System.out.println('\n');
                    System.out.println("Sum of nnumbers is : " + calculateSum(a));
                    System.out.println('\n');
                    break;
                }

                case 7: {
                    System.out.print("Enter the numberw whose digits sum you want : ");
                    int a = sc.nextInt();
                    System.out.println('\n');
                    System.out.println("Sum of digits in " + a + " is " + sum_of_digit(a));
                    System.out.println('\n');
                    break;
                }

                case 8: {
                    break;
                }

                default: {
                    System.out.println("Invalid Choice");
                    System.out.println('\n');
                }
            }

        } while (choice != 8);

    }
}

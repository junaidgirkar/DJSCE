import java.util.*;

class switchCase {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "Select your operation:\n 1.Addition\n 2.Subtraction\n 3.Multiplication\n 4.Division\n ");
        int choice = sc.nextInt();
        System.out.println("Enter the first Number:");
        int num1 = sc.nextInt();
        System.out.println("Enter the second number:");
        int num2 = sc.nextInt();
        switch (choice) {
            case (1):
                int sum = num1 + num2;
                System.out.print("Sum : " + sum);
                break;
            case (2):
                int difference = num1 - num2;
                System.out.println("Difference : " + difference);
                break;

            case (3):
                int product = num1 * num2;
                System.out.println("product : " + product);
                break;

            case (4):
                int division = num1 / num2;
                System.out.println("Division : " + division);
                break;


            default:
                System.out.println("Invalid Choice");

        }

    }
}
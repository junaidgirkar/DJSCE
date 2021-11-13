import java.util.*;

class quadraticEquation {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Input a: ");
        double a = input.nextDouble();
        System.out.print("Input b: ");
        double b = input.nextDouble();
        System.out.print("Input c: ");
        double c = input.nextDouble();

        double result = b * b - 4.0 * a * c;

        if (result > 0.0) {
            double r1 = (-b + Math.pow(result, 0.5)) / (2.0 * a);
            double r2 = (-b - Math.pow(result, 0.5)) / (2.0 * a);
            System.out.println("The roots are :\n\t" + r1 + "\n\t" + r2);
        } else if (result == 0.0) {
            double r1 = -b / (2.0 * a);
            System.out.println("The roots are equal and are :" + r1);
        } else {
            double realPart = -b / (2 * a);
            double imaginaryPart = (Math.pow(-result, 0.5)) / (2 * a);

            System.out.format("The roots are :\n\t %.2f+%.2fi \n\t %.2f-%.2fi", realPart, imaginaryPart, realPart,
                    imaginaryPart);
        }
    }
}
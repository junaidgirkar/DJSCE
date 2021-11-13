import java.util.Scanner;

public class OddAndPrime {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a, b;

        System.out.printf("Enter lower bound of the interval: ");
        a = sc.nextInt();

        System.out.printf("\nEnter upper bound of the interval: ");
        b = sc.nextInt();
        prime(a, b);
        odd(a, b);

    }

    public static void odd(int a, int b) {

        int i;
        System.out.printf("Odd numbers between %d and %d are: \n", a, b);
        for (i = a; i <= b; i++) {
            if (i % 2 != 0) {
                System.out.println(i);
            }
        }

    }

    public static void prime(int a, int b) {
        int i, j, flag;
        System.out.printf("\nPrime numbers between %d and %d are: \n", a, b);

        for (i = a; i <= b; i++) {

            if (i == 1 || i == 0)
                continue;

            flag = 1;

            for (j = 2; j <= i / 2; ++j) {
                if (i % j == 0) {
                    flag = 0;
                    break;
                }
            }

            if (flag == 1)
                System.out.println(i);
        }
    }
}
import java.util.*;

class digits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number");
        int number = sc.nextInt();
        int digits = 0;
        while (number != 0) {
            number = number / 10;
            digits = digits + 1;
        }
        System.out.println("Digits : " + digits);

    }
}
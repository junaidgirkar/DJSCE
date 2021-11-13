import java.util.Scanner;

class oddPrime {
    public static void main(String[] args) {
        int count = 0;
        Scanner ob = new Scanner(System.in);
        System.out.println("Enter a Range of Numbers");
        int beg = ob.nextInt();
        int end = ob.nextInt();
        System.out.println("Odd Numbers in Range");
        for (int i = beg; i < end; i++) {
            if (i % 2 == 1)
                System.out.println(i);
        }
        System.out.println("Prime Numbers in Range");
        for (int j = beg; j < end; j++) {
            for (int k = 2; k < j; k++) {
                if (j % k == 0) {
                    count++;
                }
            }
            if (count == 0)
                System.out.println(j);
            count = 0;
        }
        ob.close();
    }
}
import java.util.Scanner;

class Vampire {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String no = "";
        System.out.println("Enter a four digit no: ");
        while (no.length() != 4) {
            no = in.nextLine();
        }
        String a[] = no.split("");
        String c[][] = new String[12][2];
        int k = 0, i;
        for (i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                int m = -1, n = -1;
                int o[] = { 0, 1, 2, 3 };
                for (int p = 0; p < 4; p++) {
                    if (o[p] != i && o[p] != j) {
                        if (m == -1) {
                            m = o[p];
                            continue;
                        }
                        if (n == -1) {
                            n = o[p];
                        }
                    }
                }
                c[k][0] = a[i] + a[j];
                c[k][1] = a[m] + a[n];
                k++;
                c[k][0] = a[i] + a[j];
                c[k][1] = a[n] + a[m];
                k++;
            }
        }
        int copy = Integer.parseInt(no);
        int flag = 0;
        for (i = 0; i < 12; i++) {
            if (Integer.parseInt(c[i][0]) * Integer.parseInt(c[i][1]) == copy) {
                flag = 1;
                break;
            }
        }
        if (flag == 1) {
            System.out.println(no + " is vampire no since : " + c[i][0] + " * " + c[i][1] + " = " + no);
        } else {
            System.out.println(no + " is not a vampire no");
        }
    }
}

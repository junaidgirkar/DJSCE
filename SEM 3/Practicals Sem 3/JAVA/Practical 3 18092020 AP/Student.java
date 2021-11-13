import java.util.Scanner;

class Student {
    public static void main(String[] args) {
        int i, j;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the no of rows: ");
        int m = in.nextInt();
        System.out.print("Enter the no of columns: ");
        int n = in.nextInt();
        int table[][] = new int[m + 1][n + 1];
        System.out.println("Enter the elements columnwise");
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                table[i][j] = in.nextInt();
            }
        }
        for (i = 0; i < m; i++) {
            int count = 0;
            for (j = 0; j < n; j++) {
                count += table[i][j];
            }
            table[i][n] = count;
        }
        for (j = 0; j < n + 1; j++) {
            int count = 0;
            for (i = 0; i < m; i++) {
                count += table[i][j];
            }
            table[m][j] = count;
        }
        System.out.println("\nRequired Output Table:");
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                System.out.printf("%5d", table[i][j]);
            }
            System.out.print("| ");
            System.out.printf("%5d\n", table[i][m]);
        }
        for (j = 0; j <= n + 1; j++) {
            System.out.print("- - ");
        }
        System.out.print("\n");
        for (j = 0; j < n; j++) {
            System.out.printf("%5d", table[m][j]);
        }
        System.out.print("| ");
        System.out.printf("%5d", table[m][j]);
    }
}

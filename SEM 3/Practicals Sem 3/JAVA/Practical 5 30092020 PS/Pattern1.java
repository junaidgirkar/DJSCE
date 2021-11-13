import java.util.*;
import java.io.*;

public class Pattern1 {
    public static void main(String args[]) {
        int i, j;
        for (i = 1; i <= 7; i++) {
            if (i % 2 == 0) {
                for (j = i; j >= 1; j--) {
                    System.out.print(j + "\t");
                }
            } else {
                for (j = 1; j <= i; j++) {
                    System.out.print(j + "\t");
                }
            }
            System.out.print("\n");
        }
    }
}

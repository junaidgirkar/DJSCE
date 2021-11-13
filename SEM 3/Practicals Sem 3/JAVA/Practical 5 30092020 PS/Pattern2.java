import java.util.*;
import java.io.*;

public class Pattern2 {
    public static void main(String args[]) {
        int i, j, sp;
        for (sp = 40, i = 1; i <= 4; i++, sp--) {
            for (j = 1; j <= sp; j++) {
                System.out.print(" ");
            }
            for (j = 1; j <= i; j++) {
                System.out.print((char) ('A' + ((i) * (i + 1) / 2) - j));
            }
            System.out.print("\n");
        }
    }
}

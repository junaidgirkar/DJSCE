import java.util.*;
import java.io.*;

class InputRightShift {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Console console = System.console();
        String progLanguauge = console.readLine("Enter your favourite programming language: ");

        // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String name = System.console().readLine();

        System.out.println("Enter a number: ");
        int x = sc.nextInt();

        System.out.println("Name is :" + name);
        System.out.println("Right Shifted :" + (x >>> 1));

    }
}

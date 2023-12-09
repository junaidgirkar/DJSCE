import java.util.Scanner;
import java.util.Arrays;

public class SdesKeyGen {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a 10 digit binary number");
        String input = in.next();
        in.close();

        // Convert String input to a char array
        char[] inputArray = input.toCharArray();
        simpleDES(inputArray);
    }

    // function for P10
    public static char[] p10(char[] arr) {
        int[] map = new int[] { 3, 5, 2, 7, 4, 10, 1, 9, 8, 6 };
        char[] temp = new char[10];
        for (int i = 0; i < 10; i++) {
            temp[i] = arr[map[i] - 1];
        }
        return temp;
    }

    // function for P8
    public static char[] p8(char[] arr) {
        int[] map = new int[] { 6, 3, 7, 4, 8, 5, 10, 9 };
        char[] temp = new char[8];
        for (int i = 0; i < 8; i++) {
            temp[i] = arr[map[i] - 1];
        }
        return temp;
    }

    // left shift the array j number of times
    public static char[] leftShift(char[] arr, int j) {
        char[] arr1 = Arrays.copyOfRange(arr, 0, arr.length / 2);
        char[] arr2 = Arrays.copyOfRange(arr, arr.length / 2, arr.length);

        int aLen = arr1.length;
        int bLen = arr2.length;
        char[] result = new char[aLen + bLen];

        for (int i = 0; i < j; i++) {
            leftRotatebyOne(arr1);
            leftRotatebyOne(arr2);
        }

        System.arraycopy(arr1, 0, result, 0, aLen);
        System.arraycopy(arr2, 0, result, aLen, bLen);

        return result;
    }

    // Left shift the array once
    public static void leftRotatebyOne(char arr[]) {
        int n = arr.length;
        char temp;
        temp = arr[0];
        for (int i = 0; i < n - 1; i++)
            arr[i] = arr[i + 1];
        arr[n - 1] = temp;
    }

    public static void simpleDES(char[] arr) {

        // store results of the permuatations
        char[] p10Result = new char[10];
        char[] p10ResultLS = new char[10];
        char[] p10ResultLS2 = new char[10];
        char[] p8Result = new char[8];
        char[] result = new char[8];

        // Step 1 -> pass through p10
        p10Result = p10(arr);
        // System.out.print("After step 1: ");
        // System.out.println(p10Result);

        // Step 2 -> left shift by 1
        p10ResultLS = leftShift(p10Result, 1);
        // System.out.print("Step 2 result: ");
        // System.out.println(p10ResultLS);

        // step 3 -> use p8 to generate key1
        p8Result = p8(p10ResultLS);
        System.out.print("Key 1: ");
        System.out.println(p8Result);

        // step 4 -> Left shift by 2
        p10ResultLS2 = leftShift(p10ResultLS, 2);
        // System.out.print("second last step: ");
        // System.out.println(p10ResultLS2);

        // step 5 -> pass through p8 for key2
        result = p8(p10ResultLS2);
        System.out.print("Key 2: ");
        System.out.println(result);
    }

}
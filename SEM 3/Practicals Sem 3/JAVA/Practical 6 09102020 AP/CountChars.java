import java.util.Scanner;

public class CountChars {

    public static void main(String[] args) {
        String enteredString;
        int i;
        int alph = 0;
        int digi = 0;
        int spl = 0;
        int vowel = 0;

        int blankSpace = 0;
        int words = 0;

        char ch;

        Scanner sc = new Scanner(System.in);
        System.out.println("\nPlease Enter Alpha Numeric Special String =  ");
        enteredString = sc.nextLine();

        for (i = 0; i < enteredString.length(); i++) {
            ch = enteredString.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                vowel++;
            } else if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z') {
                alph++;

            } else if (ch >= '0' && ch <= '9') {
                digi++;
            } else if (ch == ' ') {
                blankSpace++;
                words++;
            } else {
                spl++;
            }
        }
        System.out.println("\nNumber of Alphabet Characters  =  " + alph);
        System.out.println("Number of Digit Characters     =  " + digi);
        System.out.println("Number of Special Characters   =  " + spl);
        System.out.println("Number of Vowels   =  " + vowel);
        System.out.println("Number of Consonants   =  " + (alph - vowel));
        System.out.println("Number of Blank Spaces   =  " + blankSpace);
        System.out.println("Number of words   =  " + (words + 1));
    }
}
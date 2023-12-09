import java.io.*;
import java.util.*;

public class Ceaser {
  // main string which we need to encrypt
  public static final String mainString = "abcdefghijklmnopqrstuvwxyyz0123456789";
  public static final int offset = 3;

  // encrypt a key using a particular offset
  public static String encrypt(String plainText, int off) {
    String cipherText = "";
    for (int i = 0; i < plainText.length(); i++) {
      int index = (mainString.indexOf(plainText.charAt(i)) + offset) % 36;
      cipherText = cipherText + mainString.charAt(index);
    }
    return cipherText;
  }

  // decrypt using key
  public static String decrypt(String cipherText, int off) {
    String plainText = "";
    for (int i = 0; i < cipherText.length(); i++) {
      int index = (mainString.indexOf(cipherText.charAt(i)) + 36 - off) % 36;
      plainText = plainText + mainString.charAt(index);
    }
    return plainText;
  }

  // Solve using brute force
  public static void bruteForce(String[] plainText) {
    for (int off = 0; off < 36; off++) {

      String result[] = new String[plainText.length];
      for (int i = 0; i < plainText.length; i++) {
        result[i] = decrypt(plainText[i], off);
      }
      for (String element : result) {
        System.out.println(element);
      }
      System.out.println();
    }

  }

  public static void main(String[] args) {
    Scanner ob = new Scanner(System.in);

    System.out.println("Enter a string to be encrypted:");
    String plainText = ob.next();
    String cipherText = encrypt(plainText, offset);
    System.out.println();
    System.out.println("Encrypted string: " + cipherText);
    System.out.println();

    System.out.println("Enter a string to be decrypted:");
    String encrypted = ob.next();
    String decrypted = decrypt(encrypted, offset);
    System.out.println();
    System.out.println("Dncrypted string: " + decrypted);
    System.out.println();

    System.out.println("Enter a line for Brute Force decryption:");
    String para = ob.next();
    String inputPT[] = para.split(" ", 0);
    System.out.println();
    bruteForce(inputPT);
    ob.close();
  }

}
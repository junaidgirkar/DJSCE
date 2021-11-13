import java.util.Vector;
import java.util.Scanner;

class Frequency {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i;
        System.out.println("Enter the no of items to be added: ");
        int n = in.nextInt();
        Vector v = new Vector(n);
        int fr[] = new int[n];
        for (i = 0; i < n; i++) {
            System.out.println("Enter element to be added: ");
            String element = in.next();
            v.addElement(element);
        }
        int visited = -1;
        for (i = 0; i < n; i++) {
            if (fr[i] != visited) {
                int count = 1;
                for (int j = i + 1; j < n; j++) {
                    if (((v.get(i)).toString()).equals(((v.get(j)).toString()))) {
                        count++;
                        fr[j] = visited;
                    }
                }
                fr[i] = count;
            }
        }
        for (i = 0; i < fr.length; i++) {
            if (fr[i] != visited)
                System.out.println("Frequency of element " + v.elementAt(i) + " is " + fr[i]);
        }
    }
}

import java.util.Vector;
import java.util.Scanner;

class Shopping {
    public static void main(String[] args) {
        Vector list = new Vector(10, 10);
        Scanner in = new Scanner(System.in);
        String item;
        System.out.println(
                "***List***\n1.Create a list.\n2.Add item at specified position.\n3.Delete item from list.\n4.Display List.\n5.Exit.");
        System.out.println("Enter your choice: ");
        int ch = in.nextInt();
        while (ch != 5) {
            switch (ch) {
                case 1:
                    System.out.println("Enter the number of items in list : ");
                    int len = in.nextInt();
                    for (int j = 0; j < len; j++) {
                        System.out.println("Enter item to be added: ");
                        item = in.next();
                        list.addElement(item);
                    }
                    break;
                case 2:
                    System.out.println("Enter item to be added : ");
                    item = in.next();
                    System.out.println("Enter position where item to be added: ");
                    int pos = in.nextInt();
                    list.insertElementAt(item, pos - 1);
                    break;
                case 3:
                    System.out.println("Enter item to be deleted: ");
                    item = in.next();
                    list.removeElement(item);
                    break;
                case 4:
                    System.out.println("***Your List***\n");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + "." + list.elementAt(i));
                    }
                    break;
            }
            System.out.println(
                    "***List***\n1.Create a list.\n2.Add item at specified position.\n3.Delete item from list.\n4.Display List.\n5.Exit.");
            System.out.println("Enter your choice: ");
            ch = in.nextInt();
        }
        in.close();
    }
}

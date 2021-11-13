import java.util.*;

class Rectangle {
    int x, y;

    public Rectangle() {
        x = 10; // Default length

        y = 12; // Default Width
    }

    public Rectangle(int height, int width) {
        x = height;
        y = width;
    }

    public int area() {
        return x * y;
    }

}

class Cube {
    int x;

    public Cube() {
        x = 5; // Default side of cube
    }

    public Cube(int side) {
        x = side;
    }

    public int volume() {
        return x * x * x;
    }

}

class Size {
    public static int size(Object obj) {
        if (obj instanceof Rectangle) {
            return ((Rectangle) obj).area();
        } else if (obj instanceof Cube) {
            return ((Cube) obj).volume();
        } else {
            return -1;
        }
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int x, y;

        System.out.print("Enter height and width of rectangle: ");
        x = in.nextInt();
        y = in.nextInt();
        Rectangle defrec = new Rectangle();
        System.out.println("Area of rectangle with default height and width  is " + size(defrec));
        Rectangle rec = new Rectangle(x, y);
        System.out.println("Area of rectangle with height " + x + " and width " + y + " is " + size(rec));

        System.out.print("Enter side of cube: ");
        x = in.nextInt();
        Cube defcb = new Cube();
        System.out.println("Volume of cube with default side is " + size(defcb));
        Cube cb = new Cube(x);
        System.out.println("Volume of cube with side " + x + " is " + size(cb));
    }
}
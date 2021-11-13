
class Box {
    double width, height;

    Box(double w, double h) {
        width = w;
        height = h;

    }

    // constructor used when square is created
    Box(double len) {
        width = height = len;
    }

    double area() {
        return width * height;
    }
}

public class Test {
    public static void main(String args[]) {

        Box mybox1 = new Box(10, 20);
        Box mybox2 = new Box(7);

        double area;

        // get area of first box
        area = mybox1.area();
        System.out.println(" Area of mybox1 is " + area);

        // get area of second box
        area = mybox2.area();
        System.out.println(" Area of mybox2 is " + area);

    }
}

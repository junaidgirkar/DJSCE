public class Cube2 {
    int length;
    int breadth;
    int height;

    public int getVolume() {
        return (length * breadth * height);
    }

    Cube2() {
        this(10, 10);
        System.out.println("Finished with Default Constructor");
    }

    Cube2(int l, int b) {
        this(l, b, 10);
        System.out.println("Finished with Parameterized Constructor having 2 params");
    }

    Cube2(int l, int b, int h) {
        length = l;
        breadth = b;
        height = h;
        System.out.println("Finished with Parameterized Constructor having 3 params");
    }

    public static void main(String[] args) {
        Cube2 cubeObj1, cubeObj2;
        cubeObj1 = new Cube2();
        cubeObj2 = new Cube2(10, 20, 30);
        System.out.println("Volume of Cube1 is : " + cubeObj1.getVolume());
        System.out.println("Volume of Cube2 is : " + cubeObj2.getVolume());
    }
}
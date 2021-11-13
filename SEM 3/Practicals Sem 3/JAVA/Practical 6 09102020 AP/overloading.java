class overloading {
    void calculateArea(float x) {
        System.out.println("Area of the square: " + x * x + " sq units");
    }

    void calculateArea(float x, float y) {
        System.out.println("Area of the rectangle: " + x * y + " sq units");
    }

    public static void main(String args[]) {
        overloading obj = new overloading();

        obj.calculateArea(6.1f);

        obj.calculateArea(10, 22);

    }
}
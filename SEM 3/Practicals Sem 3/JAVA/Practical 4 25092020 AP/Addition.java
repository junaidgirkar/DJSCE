class Addition {
    static int add(int a, int b) {
        return a + b;
    }

    static int add(int a, int b, int c) {
        return a + b + c;
    }
}

class TestOverloading1 {
    public static void main(String[] args) {
        System.out.println(Addition.add(11, 11));
        System.out.println(Addition.add(11, 11, 11));
    }
}
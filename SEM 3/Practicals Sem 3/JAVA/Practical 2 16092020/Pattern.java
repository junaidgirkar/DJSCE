class Pattern {
    public static void main(String[] args) {
        System.out.println("Number Pattern: ");
        for (int i = 0; i <= 7; i++) {
            if (i % 2 == 1) {
                for (int j = 1; j <= i; j++) {
                    System.out.print(j + " ");
                }
                System.out.println("");
            } else {
                for (int k = i; k > 0; k--) {
                    System.out.print(k + " ");
                }
                System.out.println("");
            }
        }
        int a = 0;
        System.out.println("Letter Pattern:");
        for (int h = 0; h < 4; h++) {
            for (int g = 3; g > h; g--) {
                System.out.print(" ");
            }
            for (int l = 0; l <= h; l++) {
                System.out.print((char) (a + 65));
                a++;
            }
            System.out.println("");
        }
    }
}
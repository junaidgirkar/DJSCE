public class Multithreading {
    public static void main(String[] args) {
        star f = new star();
        slash s = new slash();

        f.start();
        s.start();

    }
}

class star extends Thread {
    public void run() {

        for (int i = 1; i <= 10; i++) {
            System.out.println("*");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }
}

class slash extends Thread {
    public void run() {

        for (int i = 1; i <= 10; i++) {
            System.out.println("/");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }
}

public class Tables {

  public static void main(String[] args) {
    Five f = new Five();
    Seven s = new Seven();
    Thirteen t = new Thirteen();
    f.start();
    s.start();
    t.start();
  }
}

class Seven extends Thread {

  public void run() {
    long t1 = System.currentTimeMillis();
    for (int i = 1; i <= 10; i++) {
      System.out.println("7 x " + i + " = " + (7 * i));
      try {
        Thread.sleep(1000);
      } catch (Exception e) {}
    }
    long t2 = System.currentTimeMillis();
    System.out.println("Time Taken by table of 7: " + (t2 - t1) + " ms ");
  }
}

class Five extends Thread {

  public void run() {
    long t1 = System.currentTimeMillis();

    for (int i = 1; i <= 10; i++) {
      System.out.println("5 x " + i + " = " + (5 * i));
      try {
        Thread.sleep(1000);
      } catch (Exception e) {}
    }
    long t2 = System.currentTimeMillis();
    System.out.println("Time Taken by table of 5: " + (t2 - t1) + " ms ");
  }
}

class Thirteen extends Thread {

  public void run() {
    long t1 = System.currentTimeMillis();
    for (int i = 1; i <= 10; i++) {
      System.out.println("13 x " + i + " = " + (13 * i));
      try {
        Thread.sleep(1000);
      } catch (Exception e) {}
    }
    long t2 = System.currentTimeMillis();
    System.out.println("Time Taken by table of 13: " + (t2 - t1) + " ms ");
  }
}

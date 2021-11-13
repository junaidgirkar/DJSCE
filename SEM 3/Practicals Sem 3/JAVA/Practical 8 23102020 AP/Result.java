import java.util.Scanner;

class Student {
    int rollNo;

    Student(int rollNo) {

        this.rollNo = rollNo;
    }

    void setRollno(int rollNo) {
        this.rollNo = rollNo;
    }

    int getRollNo() {
        return this.rollNo;
    }

}

class Test extends Student {
    int sem1Marks;
    int sem2Marks;

    Test(int sem1Marks, int sem2Marks, int rollNo) {
        super(rollNo);

        this.sem1Marks = sem1Marks;
        this.sem2Marks = sem2Marks;
    }

    void setSem1Marks(int sem1) {
        this.sem1Marks = sem1;
    }

    int getSem1Marks() {
        return this.sem1Marks;
    }

    void setSem2Marks(int sem2Marks) {
        this.sem2Marks = sem2Marks;
    }

    int getRollNo() {
        return this.sem2Marks;
    }
}

class Result extends Test implements Sports {
    int total;
    int sportsScore;

    Scanner ob = new Scanner(System.in);

    Result(int sem1Marks, int sem2Marks, int rollNo, int sportScore) {

        super(sem1Marks, sem2Marks, rollNo);

        this.sportsScore = sportScore;
    }

    public void score() {
        this.total = (sem1Marks + sem2Marks) / 2 + sportsScore;
        System.out.println("The score of this student is: " + this.total);
    }

    public static void main(String[] args) {
        int r, s1, s2, s;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter student roll number");
        r = sc.nextInt();
        System.out.println("Enter s1");
        s1 = sc.nextInt();
        System.out.println("Enter s2");
        s2 = sc.nextInt();
        System.out.println("Enter ss");
        s = sc.nextInt();
        Result res = new Result(s1, s2, r, s);
        res.score();
    }

}

interface Sports {
    void score();
}
import java.util.*;
class MarksoutofBoundException extends Exception
{
    MarksoutofBoundException(String s)
    {
        super(s);
    }
}
class Result
{
    String name, date;
    int seatno, centerno, sem3_marks;
    Result(String a, int b, String c, int d, int e)
    {
        name = a;
        seatno = b;
        date = c;
        centerno = d;
        sem3_marks = e;
    }
}
class MarksException
{
    static void verify(int marks) throws MarksoutofBoundException
    {
        if(marks<0 | marks>100)
        {
            throw new MarksoutofBoundException("Entered marks are invalid!");
        }
        else
        {
            System.out.println("Valid Marks");
        }
    }
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        while(true){
        System.out.println("Enter Name:");
        String name = in.nextLine();

        System.out.println("Enter Seat number");
        int seatno = in.nextInt();
        in.nextLine();

        System.out.println("Enter Date YYYY-MM-DD");
        String date = in.nextLine();

        System.out.println("Enter Center Number");
        int centerno = (in.nextInt());

        System.out.println("Enter Sem3 marks");
        int sem3_marks = (in.nextInt());
        in.nextLine();

        Result res1 = new Result(name, seatno, date, centerno, sem3_marks);
        try
        {
            verify(res1.sem3_marks);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------\n\n");
    }
        
    }
}
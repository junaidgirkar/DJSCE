import java.util.Scanner; 
 
public class ArrayofObjects { 
    public static void main(String[] args) { 
        Scanner ob = new Scanner(System.in); 
        int n; 
        int i; 
        int s_id; 
        String name; 
        long math_mark; 
        long chem_mark; 
        long phy_mark; 
        System.out.println("Enter number of Students"); 
        n = ob.nextInt(); 
        Student[] objArr = new Student[n]; 
        System.out.println("Enter The Student Details"); 
        for (i = 0; i < n; i++) { 
            ob.nextLine(); 
            System.out.print("Enter Student Name: "); 
            name = ob.nextLine(); 
            System.out.println(); 
            System.out.print("Enter Student ID: "); 
            s_id = ob.nextInt(); 
            System.out.println(); 
            System.out.print("Enter Math Marks of Student: "); 
            math_mark = ob.nextInt(); 
            System.out.println(); 
            System.out.print("Enter Chem Marks of Student: "); 
            chem_mark = ob.nextInt(); 
            System.out.println(); 
            System.out.print("Enter Physics Marks of Student: "); 
            phy_mark = ob.nextInt(); 
            System.out.println(); 
            objArr[i] = new Student(name, s_id, phy_mark, chem_mark, math_mark); 
            System.out.println("=============== Student Details Have Succussfully Been Entered ==================="); 
 
        } 
        ob.close(); 
        System.out.println("=================== Student Details: ==========================="); 
        for (i = 0; i < n; i++) { 
            System.out.println("\nName = " + objArr[i].getName() + " Student ID: " + objArr[i].getID()); 
        } 
        sort(objArr); 
        System.out.println(); 
        System.out.println("==================== Sorted Students! ======================"); 
        for (i = 0; i < n; i++) { 
            System.out.println("\nName = " + objArr[i].getName() + " Student ID: " + objArr[i].getID()); 
        } 
    } 
 
    static void sort(Student[] objArr) { 
        for (int i = 0; i < objArr.length - 1; i++) 
            for (int j = 0; j <= objArr.length - i - 2; j++) 
                if (objArr[j].getTotalMarks() < objArr[j + 1].getTotalMarks()) { 
                    Student temp = objArr[j]; 
                    objArr[j] = objArr[j + 1]; 
                    objArr[j + 1] = temp; 
                } 
    } 
 
} 
 
class Student { 
 
    // attributes | class instance variables 
    private String name; 
    private int s_ID; 
    private long phy_marks, chem_marks, math_marks; 
    private long total_marks; 
 
    // constructor 
    Student(String name, int s_ID, long phy_marks, long chem_marks, long math_marks) { 
        this.name = name; 
        this.math_marks = math_marks; 
        this.phy_marks = phy_marks; 
        this.chem_marks = chem_marks; 
        this.s_ID = s_ID; 
        this.total_marks = this.phy_marks + this.math_marks + this.chem_marks; 
    } 
 
    public String getName() { 
        return this.name; 
    } 
 
    public int getID() { 
        return this.s_ID; 
    } 
 
    public long getTotalMarks() { 
        return this.total_marks; 
    } 
 
    public long getMathMarks() { 
        return this.math_marks; 
    } 
 
    public long getPhyMarks() { 
        return this.phy_marks; 
    } 
 
    public long getChemMarks() { 
        return this.chem_marks; 
    } 
 
}
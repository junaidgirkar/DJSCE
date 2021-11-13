import java.util.Scanner;
class Quadratic {
public static void main(String args[]){
double root1 = 0, root2 = 0;
Scanner sc = new Scanner(System.in);
System.out.println("Enter the value of a ::");
double a = sc.nextDouble();
System.out.println("Enter the value of b ::");
double b = sc.nextDouble();
System.out.println("Enter the value of c ::");
double c = sc.nextDouble();
double determinant = (b*b)-(4*a*c);
double sqrt = Math.sqrt(determinant);
String sq=sqrt+"i";
if(determinant > 0) {
root1 = (-b + sqrt) / (2 * a);
root2 = (-b - sqrt) / (2 * a);
System.out.println("First root is :"+root1);
System.out.println("Second root is :"+root2);
}
else if (determinant == 0) {
root1 = -b / (2 * a);
root2=root1;
System.out.println("First root is :"+root1);
System.out.println("Second root is :"+root2);
}
else {
double realPart = -b / (2 *a);
double imaginaryPart = Math.sqrt(-determinant) / (2 * a);
System.out.format("root1 = %.2f+%.2fi and root2 = %.2f-%.2fi", realPart, imaginaryPart, realPart, imaginaryPart);
}
}
}
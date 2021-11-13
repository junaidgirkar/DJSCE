import java.util.*;
class vector
{
	public static void main(String args[])
	{
		Vector v = new Vector(5,3);
		System.out.println("Initial Size "+v.size());
		System.out.println("Initial Capacity "+v.capacity());
		v.addElement(new String("Junaid"));
		v.addElement(new Integer(9));
		v.addElement(new String("DJSCE"));
		System.out.println("Capcity "+v.capacity());
		v.addElement(new Float(3.25));
		System.out.println("Capcity "+v.capacity());
		v.addElement(new Double(5.25));
		System.out.println("Capcity "+v.capacity());
		v.addElement(new Double(4.25));
		System.out.println("Capcity "+v.capacity());
		System.out.println("Vector content is "+v);
		System.out.println("First Element "+v.firstElement());	
	}
}
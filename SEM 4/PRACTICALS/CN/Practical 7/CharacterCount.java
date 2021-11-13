import java.util.*;
class CharacterCount 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nSENDER SIDE");
        System.out.println("\nEnter the number of frames to be sent : ");
        int n = sc.nextInt();
        String frames[] = new String[n];
        for(int i=0;i<n;i++)
        {
            System.out.print("\nEnter data for frame " + i + " : ");
            frames[i] = sc.next(); 
        }
        int k=0; //Code Length
        int code[] = new int[100];
        for(int i=0;i<n;i++)
        {
            code[k++] = frames[i].length() + 1;
            for(int j=0;j<frames[i].length();j++)
            {
                code[k++]=Character.getNumericValue(frames[i].charAt(j));
            }
        }
        System.out.println("\nAfter Character Count Framing : ");
        for(int i=0;i<k;i++)
        {
            System.out.print(code[i]);
        }
        System.out.println("\n\nRECEIVER SIDE");
        System.out.println("\nEnter received Frame : ");
        String data = sc.next();
        int i=0,flag=1,countOfFrames=0;
        String f[] = new String[10];
        while(flag==1 && i<data.length())
        {
            int len = Character.getNumericValue(data.charAt(i));
            if(i+len > data.length())
            {
                System.out.println("\nReceived frame is incorrect");
                flag=0;
            }
            if(flag==1)
            {
                String s = "";
                int j;
                for(j=i+1;j<i+len;j++)
                {
                    s+=data.charAt(j);
                }
                f[countOfFrames++]=s;
                i=j;
            }
        }
        if(flag==1)
        {
            for(int a=0;a<countOfFrames;a++)
            {
                System.out.println("\nFrame " + a + " : " + f[a]);
            }
        }
    }
}
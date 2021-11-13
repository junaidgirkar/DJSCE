class Valid 
{
    public static void main(String[] args) 
    {
        int valid=0,invalid=0,temp;
        for(int i=0;i<args.length;i++)
        {
            try
            {
                temp=Integer.parseInt(args[i]);
                valid ++;
            }
            catch(NumberFormatException e)
            {
                invalid++;
            }
        }
        System.out.println("Valid Integers : " + valid + "\nInvalid Integers : " + invalid);
    }    
}

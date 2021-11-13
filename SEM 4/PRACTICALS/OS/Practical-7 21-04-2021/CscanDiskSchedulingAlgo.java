import java.util.*;

class CscanDiskSchedulingAlgo {

	static int size = 8;
	static int disk_size = 200;

	public static void CSCAN(int arr[], int head)
	{
		int seek_count = 0;
		int distance, cur_track;

		Vector<Integer> left = new Vector<Integer>();
		Vector<Integer> right = new Vector<Integer>();
		Vector<Integer> seek_sequence
			= new Vector<Integer>();


		left.add(0);
		right.add(disk_size - 1);


		for (int i = 0; i < size; i++) {
			if (arr[i] < head)
				left.add(arr[i]);
			if (arr[i] > head)
				right.add(arr[i]);
		}

		// Sorting left and right vectors
		Collections.sort(left);
		Collections.sort(right);

		// First service the requests
		// on the right side of the
		// head.
		for (int i = 0; i < right.size(); i++) {
			cur_track = right.get(i);

			seek_sequence.add(cur_track);

			distance = Math.abs(cur_track - head);

			seek_count += distance;

			head = cur_track;
		}

		// Once reached the right end
		// jump to the beggining.
		head = 0;

		// adding seek count for head returning from 199 to
		// 0
		seek_count += (disk_size - 1);

		// Now service the requests again
		// which are left.
		for (int i = 0; i < left.size(); i++) {
			cur_track = left.get(i);

			seek_sequence.add(cur_track);

			distance = Math.abs(cur_track - head);

			seek_count += distance;

			head = cur_track;
		}

		System.out.println("Total number of seek "+ "operations = " + seek_count);

		System.out.println("Seek Sequence is");

		for (int i = 0; i < seek_sequence.size(); i++) {
			System.out.print(seek_sequence.get(i) + " -> ");
		}
	}

	// Driver code
	public static void main(String[] args) throws Exception
	{

		int n;  
        Scanner sc=new Scanner(System.in);  
        System.out.print("Enter the number of elements you want to store: ");   
        n=sc.nextInt();  

        int[] arr = new int[n];  
        System.out.print("Enter the elements of the array: ");  

        for(int i=0; i<n; i++)  
        {  
            arr[i]=sc.nextInt();  
        }  

        System.out.print("Enter Initial Head Position: ");
        int head_pos = sc.nextInt();

		CSCAN(arr, head_pos);
	}
}

// This code is contributed by divyesh072019

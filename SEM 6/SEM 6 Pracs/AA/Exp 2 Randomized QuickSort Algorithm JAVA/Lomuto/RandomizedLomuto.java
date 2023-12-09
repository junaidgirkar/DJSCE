
// Java program to illustrate
// Randomised Quick Sort
import java.util.*;
import java.time.Duration;
import java.time.Instant;

class RandomizedLomuto {
	// This Function helps in calculating
	// random numbers between low(inclusive)
	// and high(inclusive)
	static void random(int arr[], int low, int high) {

		Random rand = new Random();
		int pivot = rand.nextInt(high - low) + low;

		int temp1 = arr[pivot];
		arr[pivot] = arr[high];
		arr[high] = temp1;
	}

	/*
	 * This function takes last element as pivot,
	 * places the pivot element at its correct
	 * position in sorted array, and places all
	 * smaller (smaller than pivot) to left of
	 * pivot and all greater elements to right
	 * of pivot
	 */
	static int partition(int arr[], int low, int high) {
		// pivot is chosen randomly
		random(arr, low, high);
		int pivot = arr[high];

		int i = (low - 1); // index of smaller element
		for (int j = low; j < high; j++) {
			// If current element is smaller than or
			// equal to pivot
			if (arr[j] < pivot) {
				i++;

				// swap arr[i] and arr[j]
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		// swap arr[i+1] and arr[high] (or pivot)
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;

		return i + 1;
	}

	/*
	 * The main function that implements QuickSort()
	 * arr[] --> Array to be sorted,
	 * low --> Starting index,
	 * high --> Ending index
	 */
	static void sort(int arr[], int low, int high) {
		if (low < high) {
			/*
			 * pi is partitioning index, arr[pi] is
			 * now at right place
			 */
			int pi = partition(arr, low, high);

			// Recursively sort elements before
			// partition and after partition
			sort(arr, low, pi - 1);
			sort(arr, pi + 1, high);
		}
	}

	/* A utility function to print array of size n */
	static void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	// Driver Code
	public static void main(String args[]) {

		Instant start = Instant.now();
		int arr[] = { 9, -3, 5, 2, 6, 8, -6, 1, 3 };
		int n = arr.length;

		sort(arr, 0, n - 1);

		System.out.println("Sorted array");
		printArray(arr);
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		System.out.println("Time taken for Randomized Lomuto: " + timeElapsed.toMillis() + " milliseconds");
	}
}
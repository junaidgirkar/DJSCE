import java.util.Arrays;
import java.util.*;
import java.time.Duration;
import java.time.Instant;

class RandomizedHoarse {
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

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

	// Partition using Hoare's Partitioning scheme
	public static int partition(int[] a, int low, int high) {
		// pivot is chosen randomly
		random(a, low, high);
		int pivot = a[low];
		// int pivot = a[low];
		int i = low - 1;
		int j = high + 1;

		while (true) {
			do {
				i++;
			} while (a[i] < pivot);

			do {
				j--;
			} while (a[j] > pivot);

			if (i >= j) {
				return j;
			}

			swap(a, i, j);
		}
	}

	// Quicksort routine
	public static void quicksort(int[] a, int low, int high) {
		// base condition to check length
		if (low >= high) {
			return;
		}

		// rearrange elements across pivot
		int pivot = partition(a, low, high);

		// recur on subarray containing elements less than the pivot [LEFT SIDE SORTING]
		quicksort(a, low, pivot);

		// recur on subarray containing elements more than the pivot [RIGHT SIDE
		// SORTING]
		quicksort(a, pivot + 1, high);
	}

	public static void main(String[] args) {
		Instant start = Instant.now();

		int[] a = { 9, -3, 5, 2, 6, 8, -6, 1, 3 };

		quicksort(a, 0, a.length - 1);

		// print the sorted array
		System.out.println(Arrays.toString(a));

		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		System.out.println("Time taken for Randomized Hoarse : " + timeElapsed.toMillis() + " milliseconds");
	}
}
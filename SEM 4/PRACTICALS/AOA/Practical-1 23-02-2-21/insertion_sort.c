#include <math.h> 
#include <stdio.h> 


void insertionSort(int arr[], int n) 
{ 
	int i, key, j; 
	for (i = 1; i < n; i++) { 
		key = arr[i]; 
		j = i - 1; 

		/* Move elements of arr[0..i-1], that are 
		greater than key, to one position ahead 
		of their current position */
		while (j >= 0 && arr[j] > key) { 
			arr[j + 1] = arr[j]; 
			j = j - 1; 
		} 
		arr[j + 1] = key; 
	} 
} 


void printArray(int arr[], int n) 
{ 
	int i; 
	for (i = 0; i < n; i++) 
		printf("%d ", arr[i]); 
	printf("\n"); 
} 


int main() 
{	
	int n, i;
	printf("Enter Number of elements to be sorted: \t");
	scanf("%d",&n);
	printf("Enter elements to be sorted: \n");
	int arr[n];
	for(i=0;i<n;i++)
	{
		scanf("%d",&arr[i]);
	}


	insertionSort(arr, n); 
	printf("The sorted array is \n");
	printArray(arr, n); 

	return 0; 
} 

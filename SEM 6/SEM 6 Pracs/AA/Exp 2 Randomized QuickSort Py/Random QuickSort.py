import random
import time

y = input("Enter the number of iterations ")
x = int(y)


randomlist = []
randomm = []
for i in range(0,x):
    n = random.randint(1,70)
    randomlist.append(n)

for i in range(0,x):
    randomm.append(randomlist[i])


#QuickSort Part
def partitionn(arr,low,high):
   i = ( low-1 )
   pivot = arr[high] # pivot element
   for j in range(low , high):
      # If current element is smaller
      if arr[j] <= pivot:
         # increment
         i = i+1
         arr[i],arr[j] = arr[j],arr[i]
   arr[i+1],arr[high] = arr[high],arr[i+1]
   return ( i+1 )
# sort
def quickSort(arr,low,high):
   if low < high:
      # index
      pi = partitionn(arr,low,high)
      # sort the partitions
      quickSort(arr, low, pi-1)
      quickSort(arr, pi+1, high)
# main

print("\nOriginal list:")
print(randomm)
print("After applying Regular Quick Sort the said list becomes:")
start = time.time()
quickSort(randomm, 0, x-1)
end = time.time()
print(randomm)


print(end-start)

#Random Quick Sort Part

def partition(A, left_index, right_index):
    pivot = A[left_index]
    i = left_index + 1
    for j in range(left_index + 1, right_index):
        if A[j] < pivot:
            A[j], A[i] = A[i], A[j]
            i += 1
    A[left_index], A[i - 1] = A[i - 1], A[left_index]
    return i - 1
def quick_sort_random(A, left, right):
    if left < right:
        pivot = random.randint(left, right - 1)
        A[pivot], A[left] = (
            A[left],
            A[pivot],
        )
        pivot_index = partition(A, left, right)
        quick_sort_random(
            A, left, pivot_index
        )
        quick_sort_random(
            A, pivot_index + 1, right
        )



print("\nOriginal list:")
print(randomlist)
print("After applying Random Pivot Quick Sort the said list becomes:")
start = time.time()
quick_sort_random(randomlist, 0, x-1)
end = time.time()
print(randomlist)


print(end-start)
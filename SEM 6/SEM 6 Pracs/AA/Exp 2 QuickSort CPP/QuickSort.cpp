#include <iostream>
#include <vector>
#include <string>
#include <random>

std::random_device rd;
std::mt19937 gen(rd());

bool searchLine(std::string line, std::string key) {
    return line.find(key) != std::string::npos;
}

int getPivot(int i, int j, std ::string method) {
    if (searchLine(method, "random")) {
        std::uniform_int_distribution<> distrib(i, j);
        return distrib(gen);
    }
    if (searchLine(method, "lumoto")) return j;
    if (searchLine(method, "hoare")) return (i + j) / 2;
    return -1;
}

int sortPartition(int i, int j, std ::string method, std::vector<int>& array) {
    int pivot = getPivot(i, j, method);
    std::cout << "Pivot: " << pivot << std::endl;

    if (searchLine(method, "hoare")) {
        while (i < j) {
            if (array[i] > array[j]) {
                std::swap(array[i], array[j]);
                if (array[i] > array[pivot]) std::swap(array[i], array[pivot]);
                if (array[pivot] > array[j]) std::swap(array[pivot], array[j]);
            }

            i++;
            j--;
        }
    }
    // TODO: Lumoto Partition & Custom Partition

    return pivot;
}

void printArray(std::vector<int>& array) {
    for (auto x : array) std::cout << x << " ";
    std::cout << std::endl;
}

void quicksort(int start, int end, std::vector<int>& array, std ::string method) {
    if (start >= end) return;

    int pivot = getPivot(start, end, method);
    printArray(array);

    quicksort(start, pivot, array, method);
    quicksort(pivot + 1, end, array, method);
}

int main(int argc, const char** argv) {
    std::vector<int> array{5, 7, 3, 9, 2, 6, 10};

    printArray(array);

    quicksort(0, array.size() - 1, array, "hoare");

    printArray(array);

    return 0;
}
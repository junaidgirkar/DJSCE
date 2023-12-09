#include <iostream>
#include <vector>

int main() {
    std::vector<int> vec;

    vec.push_back(1);
    vec.push_back(2);

    std::cout << vec.front() << std::endl;

    vec.insert(vec.begin() + 1, 4);
    vec.erase(vec.begin(), vec.begin() + 1);
}
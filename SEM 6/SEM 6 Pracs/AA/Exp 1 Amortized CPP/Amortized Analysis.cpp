#include <iostream>
#include <string>
#include <cstring>
#include <fstream>
#include <cstdlib>
#include <unordered_map>

bool searchLine(std::string line, std::string key) {
    return line.find(key) != std::string::npos;
}

class DataStructure {
   public:
    virtual void process(std::ifstream &file, std::string &line) {}
    virtual void printCost() {}
};

class stackAnalyzer : public DataStructure {
};

class queueAnalyzer : public DataStructure {
};

class mapAnalyzer : public DataStructure {
};

class vectorAnalyzer : public DataStructure {
   private:
    std::string variable_name;

    int constant_cost = 1;
    int vector_size = 0;
    int max_size = 1;

    // Change in number of elements
    //  1: Add
    // -1: Remove
    //  0: No change
    std::unordered_map<std::string, int> function_calls{
        {"push_back", 1},
        {"insert", 1},
        {"erase", -1},
        {"find", 0},
        {"begin", 0},
    };

    // Cost per function
    std::unordered_map<std::string, int> incurred_costs{
        {"push_back", 0},
        {"insert", 0},
        {"erase", 0},
        {"find", 0},
        {"begin", 0},
    };

    // No of times function is called
    std::unordered_map<std::string, int> function_ops{
        {"push_back", 0},
        {"insert", 0},
        {"erase", 0},
        {"find", 0},
        {"begin", 0},
    };

    // Time Complexity
    std::unordered_map<std::string, int *> function_cost{
        {"push_back", &this->constant_cost},
        {"insert", &this->vector_size},
        {"erase", &this->vector_size},
        {"find", &this->vector_size},
        {"begin", &this->constant_cost},
    };

   public:
    vectorAnalyzer(std::string var_name = "vec") {
        this->variable_name = var_name;
    }

    void incurCost(std::pair<std::string, int> function) {
        if (this->vector_size > max_size) {
            this->incurred_costs[function.first] += this->max_size;
            this->max_size = this->max_size * 2;
        }
        this->incurred_costs[function.first] += *this->function_cost[function.first];
    }

    void search(std::string line) {
        for (auto function : function_calls) {
            if (searchLine(line, function.first)) {
                this->vector_size += function.second;
                this->incurCost(function);
                this->function_ops[function.first]++;
                std::cout << "\nFound: " << function.first << std::endl;
                std::cout << "Vector Size: " << this->vector_size << std::endl;
                std::cout << "Max Size: " << this->max_size << std::endl;
                std::cout << "Operation Cost: " << this->incurred_costs[function.first] << std::endl;
                std::cout << "No Ops: " << this->function_ops[function.first] << std::endl;
            }
        }
    }

    void process(std::ifstream &file, std::string &line) {
        while (getline(file, line)) {
            this->search(line);
        }
    }

    void printCost() {
        for (auto function : function_ops) {
            if (function.second) {
                std::cout << "\nFunction: " << function.first << std::endl;

                int val = this->incurred_costs[function.first] / function.second;
                std::cout << "Cost: " << (val / this->vector_size ? "n" : "1") << std::endl;
            }
        }
    }
};

DataStructure *identifyDataStructure(std::ifstream &file, std::string &line) {
    // can also use regex for better search + getting variable name
    while (getline(file, line)) {
        if (searchLine(line, "vector")) return new vectorAnalyzer();
        if (searchLine(line, "stack")) return new stackAnalyzer();
        if (searchLine(line, "queue")) return new queueAnalyzer();
        if (searchLine(line, "map")) return new mapAnalyzer();
    }
    return nullptr;
}

int main(int argc, char **argv) {
    std::cout << "\nProcessing: " << argv[1] << std::endl;

    std::ifstream file(argv[1]);
    std::string line;

    DataStructure *ds = identifyDataStructure(file, line);
    if (ds) {
        ds->process(file, line);
        std::cout << "\nAmortized Cost: " << std::endl;
        ds->printCost();
    } else {
        std::cout << "Data Structure Not Supported" << std::endl;
    }

    file.close();
    delete ds;
}
#include <iostream>
#include <fstream>
#include <stream>

#include "tree.h"

using namespace std;

#define BUFFER_LEN 256

// 1 1(1()())
#define MIN_LEN 10

Tree *build_tree(String tree_str) {

}



struct test_case parse_case(string test_inp) {
    size_t name_end = test_inp.find_first_of(":");
    string test_name = test_inp.substr(0, name_end);

    size_t first_int = test_inp.find_first_of("-0987654321", name_end);
    // error handle if not found

    

    size_t tree_start = test_inp.find_first_of("(");


    unsigned i = 0;
}

int run_case(string test_inp) {
    // if ERR
    unsigned len = test_inp.length();
    if (len < 10) {
        //invalid inp
    }

    int err;
    if (test_inp)


    stoi()
}

void run_suite(String filename) {

}

int main (int argc, char *argv[]) 
{
    if (argc != 2) {
        cout << "usage: " << argv[0] << " <filename>\n";
        return 1;
    }

    ifstream file;
    file.exceptions(ifstream::failbit | ifstream::badbit);

    try {
        file.open(argv[1]);
        string line;
        int total_count = 0;
        int successful_count = 0;
        while(!file.eof()) {
            // process line
            file.getline(line);
            struct test_case parsed_case = parse_case(line);
            int result = run_case(test_reult);
            total_count += 1;
            if (result) {
                cout << "Test \"" << parsed_case.title
                    << "\" failed: expected " << parsed_case.solution
                    << " but received " << result;
            } else {
                cout << "Test \"" << parsed_case.title << "\" passed!\n"
                successful_count += 1;
            }
            cout << "\n";
        }
    } catch(ifstream::failure e) {
        cerr << "Exception opening/reading/closing "
            << argv[1];
        return 1;
    }

    cout << successful_count << "/" << total_count << " test cases passed.\n"
    return 0;
}


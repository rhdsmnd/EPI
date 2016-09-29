#include <iostream>
#include <string>
#include <vector>
using namespace std;



void swap(int *inp, unsigned ind_one, unsigned ind_two) {
    int temp = inp[ind_one];

    inp[ind_one] = inp[ind_two];
    inp[ind_two] = temp;
}

void dutch_flag(int inp[], int size, int ind)
{

    int swap_val = inp[ind];

    unsigned less_end = 0;
    unsigned greater_end = size - 1;

    unsigned scan_ind = 0;

    while (scan_ind <= greater_end) {
        if (inp[scan_ind] < swap_val) {
            swap(inp, scan_ind, less_end);
            less_end += 1;
            scan_ind += 1;
        } else if (inp[scan_ind] > swap_val) {
            swap(inp, scan_ind, greater_end);
            greater_end -= 1;
        } else {
            scan_ind += 1;
        }
    }
}

void run_case(int test[]) {

    int length = test[0];
    int ind_val = test[1];

    cout << ind_val << "\n";

    cout << "{ ";
    for (int i = 0; i < length; i += 1) {
        int item = test[i];
        cout << item << " ";
    }
    cout << "}\n";


    dutch_flag(test, length, ind_val);

    cout << "{ ";
    for (int i = 0; i < length; i += 1) {
        int item = test[i];
        cout << item << " ";
    }
    cout << "}\n\n";

}

int main() {
    int testCase1[] = {2, 0};
    run_case(testCase1);
    int testCase2[] = {5, 2, 0, 2, 3};
    run_case(testCase2);
    int testCase3[] = {5, 2, 3, 2, 0};
    run_case(testCase3);
    int testCase4[] = {5, 2, 2, 2, 1};
    run_case(testCase4);
    int testCase5[] = {5, 2, 3, 2, 2};
    run_case(testCase5);
    int testCase6[] = {13, 4, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    run_case(testCase6);
    int testCase7[] = {12, 5, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    run_case(testCase7);
    int testCase8[] = {12, 1, 4, 6, 4, 4, 4, 4, 4, 4, 4, 4};
    run_case(testCase8);
    int testCase9[] = {6, 3, 1, 2, 1, 0};
    run_case(testCase9);
}


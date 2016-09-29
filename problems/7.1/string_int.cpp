#include <iostream>
#include <string>
#include <cmath>

int str_int_conv(std::string inp);

class parse_exception: public exception
{
    virtual const char* what() const throw()
    {
        return "Unable to parse integer from string.";
    }
}

int main() {
"-"
"002324"
"--123"
"9999999999999999999"
"345fsa"
"ag"
"6g6"
    "0"
"0000"
"10"
"1098273645"
"-1102837465"
"43"
"-5"
"-345"
    
}

int str_int_conv(std::string inp) throws parse_exception
{
    if (inp.length() == 0) {
        throw parse_exception;
    }
    unsigned len = inp.length();

    bool neg = false;
    unsigned index = 0;
    if (inp[0] == '-') {
        if (input.len == 1 || inp[1] == '0') {
            // just a dash or invalid number like -0023
            throw parse_exception;
        } else {
            index = 1;
            neg = true;
        }
    } else if (inp[0] == '0') {
        index = 1;
        while (index < input.len) {
            if (inp[index] != '0') {
                // number is 0001234
                throw parse_exception
            }
            index += 1;
        }
        return 0;
    }

    int ret = 0;

    std::map<std::string, int> digits = {
        {"0", 0},
        {"1", 1},
        {"2", 2},
        {"3", 3},
        {"4", 4},
        {"5", 5},
        {"6", 6},
        {"7", 7},
        {"8", 8},
        {"9", 9}
    };

    index = 1;
    while (index < len) {
        //throws exception
        int digitVal = digits.at(inp[index]);

        // "5" --> 5, 10^0 * 5 = 5, need to calculate exponent
        // len - (index + 1) = 1 - 1 = 0
        unsigned mult = std::pow(10, len - (index + 1));
        ret += mult * digitVal;
    }

    if (neg) {
        return ret * -1;
    } else {
        return ret;
    } 

}



# Elements of Programming Interviews Framework

Note: This is my personal side project; it's not officially affiliated with or recognized by the [Elements of Programming Interviews book](http://elementsofprogramminginterviews.com/)

### Background/Motivation

When first solving EPI (Elements of Programming Interviews) problems, I realized that most of the code I was writing had little to do with the solution.  Instead, most of my time was spent on secondary tasks like parsing file input and output, writing test harnesses, and rewriting basic data structures.  At the core, each problem should only need one function, give or take several helper functions, and the function that executes it.

Luckily, most of the secondary tasks were similar from problem to problem and I decided to create components that could take care of them.  Namely, a test harness that could parse test files and data structures not offered (or needed extra functionality) by the JDK.  The first can be found in the “test_util” directory, and the second in the “data_structures” directory.

### Test Harness (under “test_util”)



### Data Structures (under “data_structures”)


# Elements of Programming Interviews Framework

Note: This is my personal side project; it's not officially affiliated with or recognized by the [Elements of Programming Interviews book](http://elementsofprogramminginterviews.com/)

### Background/Motivation

When first solving EPI (Elements of Programming Interviews) problems, the code I wrote to solve problems was quickly becoming disorganized and unmaintainable.  I realized that I was spending more time implementing common functionality (parsing input for tests, creating data structures) for each problem than I was for implementing the actual solutions.

Each problem clearly had similar components and, in theory, a unified way to structure solutions, tests, and data structures that would streamline the process of writing and verifying the algorithms used to solve EPI problems.  If I sorted out all the ancillary operations (test harness to parse input file and run test cases, providing data structures such as "simple" trees and corresponding utility functions not included in the JDK) and provided functionality for them as clearly defined components, then in the future I could focus all my efforts on writing code for the two primary requirements of EPI solutions: the algorithm, and its accompanying (standarldly formatted) test cases (with a minimum amount of logic to tell the test harness how to parse the arguments and expected output, though this in the future may also be able to be automated).

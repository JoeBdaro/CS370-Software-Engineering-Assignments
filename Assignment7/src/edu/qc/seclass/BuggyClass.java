package edu.qc.seclass;

public class BuggyClass {

    public int buggyMethod1(int a, int b){

        int result = 1;

        if (b % 2 == 0){
             b = 0;
        }

        result = a / b;


        if(result % 2 == 0){
            result = a/result;
        }

        else {
            result = a/result;
        }

        return result;
    }



    public int buggyMethod2(int a, int b){

        int result = 1;

        if (b % 2 == 0){
            result = 0;
        }

        if(b == 0){
            result = a/result;
        }

        else {
            result = a/result;
        }

        return result;
    }

    public void buggyMethod3(int a, int b){
        /*this is impossible to make. While we can create a method that satisfies 100% coverage of branches without failure (i.e division by 0)
        we cannot in the case go ahead and and get 100% statement coverage even with less than 100% branch coverage by putting it one of the branches
        we trigger as the previous test of 100% branch coverage would not be satisfies which means we would have to put the error in the statement coverage
        portion which would be impossible to reach 100% coverage and identify the error without the program throwing an error before 100% statement coverage is reached
        */
    }

    public void buggyMethod4(int a, int b){
        /* this is impossible as I stated and explained above that it is not feasible to have 100% statement coverage while being able to trigger
        the divisible by 0 error as the error would halt the program before 100% statement coverage is reached. The error cannot exists in branches
        as the second part of the test requires there be 100% coverage of branches without showing the error.
         */
    }

    public void buggyMethod5(){
        /*This is also not possible. As I have stated and we saw in method 3 & 4 it is impossible to reach 100% statement coverage when there is an error present as
        the error would interrupt the test/ program thus not letting us reach 100%  statement coverage.
         */
    }
}

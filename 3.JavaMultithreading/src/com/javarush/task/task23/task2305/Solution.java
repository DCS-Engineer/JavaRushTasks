package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution[] solutions = new Solution[2];
        for (int i = 0; i < 2; i++){
            Solution solution = new Solution();
            solution.innerClasses[0] = solution.new InnerClass();
            solution.innerClasses[1] = solution.new InnerClass();
            solutions[i] = solution;
        }
        return solutions;
    }

    public static void main(String[] args) {

    }
}

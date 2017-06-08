package Attempt2.Data;
/*
    Project: Dissertation
    Created by: Joker
    Created date: 13/02/2017
*/

import Attempt2.Algorithm;

public class TimeTableTester {
    public static void main(String[] args) {
        TimeTable test = new TimeTable();
        test.generate();
        Helper.sortTimeTable(test);
        System.out.println(test.toString());
        System.out.printf("fitness=%d\n", Algorithm.getFitness(test));
    }
}

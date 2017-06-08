package Attempt2.Data;/*
    Project: Dissertation
    Created by: Joker
    Created date: 16/02/2017
*/

public class Course
{
    private int studentsNumber;
    private String name;
    private Module[] modules;

    Course(int studentsNumber, String name, Module[] modules) {
        this.studentsNumber = studentsNumber;
        this.name = name;
        this.modules = modules;
    }

    public String getName() {
        return name;
    }

    public int getStudentsNumber() {
        return studentsNumber;
    }

    public Module[] getModules() {
        return modules;
    }
}

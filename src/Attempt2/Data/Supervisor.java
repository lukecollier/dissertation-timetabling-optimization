package Attempt2.Data;/*
    Project: Dissertation
    Created by: Joker
    Created date: 15/02/2017
*/

public class Supervisor
{
    private String fullname;

    Supervisor(String fullname) {
        this.fullname=fullname;
    }

    Supervisor(Supervisor supervisor) {
        this.fullname = supervisor.getFullname();
    }

    public String getFullname() {
        return fullname;
    }

    public boolean equals(Supervisor supervisor) { return (getFullname().equals(supervisor.getFullname())); }

    @Override
    public String toString() {
        return String.format("%s[fullname=%s]",
                this.getClass().getName(), getFullname());
    }
}
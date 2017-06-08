package Attempt2.Data;
/*
    Project: Dissertation
    Created by: Joker
    Created date: 15/02/2017
*/

public class Module
{
    private String name;

    private int contactHours;

    /**
     * Constructor
     * @param name the name of the module
     */
    Module(String name) {
        this.name=name;
    }

    /**
     * Copy constructor
     * @param module the module to copy
     */
    Module(Module module) {
        this.name = module.getName();
        this.contactHours = module.getContactHours();
    }

    /**
     * Get's the name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get's the contact hours for the modules
     * @return returns the contact hours
     */
    public int getContactHours() { return contactHours; }


    public boolean equals(Module module) {
        return (getName().equals(module.getName())
                && getContactHours() == module.getContactHours());
    }

    @Override
    public String toString() {
        return String.format("%s[name=%s, contact hours=%d]",
                this.getClass().getName(), getName(), getContactHours());
    }
}
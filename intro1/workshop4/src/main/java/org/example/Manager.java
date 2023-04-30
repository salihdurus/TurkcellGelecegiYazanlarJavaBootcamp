package org.example;

public class Manager extends Employee{
    private String projectBudget;

    public Manager(int id, String firstName, String lastName, String projectBudget) {
        super(id, firstName, lastName);
        this.projectBudget = projectBudget;
    }

    public void setProjectBudget(String projectBudget) {
        this.projectBudget = projectBudget;
    }

    public String getProjectBudget() {
        return projectBudget;
    }

}

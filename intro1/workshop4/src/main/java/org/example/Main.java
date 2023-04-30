package org.example;

public class Main {
    public static void main(String[] args) {
        EmployeeManager employeeManager = new EmployeeManager(new SmsNotification());
       employeeManager.add(new Engineer(1,"Salih","Dürüs","Mühendis"));
       employeeManager.add(new Manager(2,"Emre","Bozkurt","Yönetici"));
       employeeManager.add(new Executive(3,"Engin","Demiroğ","Hisse Senedi"));

//        employeeManager.multipleAdd(new Employee[]{new Engineer(1, "Salih", "Dürüs", "Mühendis"),
//                new Manager(2, "Emre", "Bozkurt", "Yönetici"),
//                new Executive(3, "Engin", "Demiroğ", "Hisse Senedi")
//        });

    }
}
package org.example;

public class Main {
    public static void main(String[] args) {
//        Applicant applicant=new Applicant();
        UserManager userManager=new UserManager();
        Instructor instructor=new Instructor(1,"Salih","Dürüs","kodlamaio");
        Applicant applicant=new Applicant(1,"SalihAday","Dürüs","kodlamaio");
        Employee employee=new Employee(1,"SalihÇalışan","Dürüs",30000);


//        userManager.add(instructor);
//        userManager.add(applicant);
//        userManager.add(employee);

        userManager.addMultiple(new User[]{instructor,applicant,employee});
    }
}
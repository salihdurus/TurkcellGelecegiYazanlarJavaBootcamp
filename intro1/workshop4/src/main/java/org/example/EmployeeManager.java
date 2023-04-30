package org.example;

public class EmployeeManager {
    Notification notification;
    Notification[] notifications;

    public EmployeeManager(Notification[] notifications) {
        this.notifications = notifications;
    }

    public EmployeeManager(Notification notification) {
        this.notification = notification;
    }

    public void add(Employee employee){
        System.out.println(employee.getFirstName()+" Eklendi");
        notification.sendNotify();
    }

    public void multipleAdd(Employee[] employees){
        for (Employee employee : employees) {
            add(employee);
        }
    }

}

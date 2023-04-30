package org.example;

public class Main {
    public static void main(String[] args) {
//        LogManager logManager=new LogManager();
//        logManager.log(1);
//        logManager.log(2);
//        logManager.log(3);
        CustomerManager customerManager=new CustomerManager();
        customerManager.add(new SmsLogger());
    }
}
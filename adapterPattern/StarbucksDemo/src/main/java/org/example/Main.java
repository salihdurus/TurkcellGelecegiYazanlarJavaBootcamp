package org.example;

public class Main {
    public static void main(String[] args) {
        Customer customer=new Customer("Salih","Dürüs","1234567980","1999");
        Customer customer2=new Customer("Emre","Dürüs","1234567980","1999");
        CustomerService customerService=new CustomerManager(new EDevletAdapter());
        customerService.registerCustomer(customer);
        customerService.registerCustomer(customer2);
    }
}
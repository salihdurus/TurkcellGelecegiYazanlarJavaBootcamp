package org.example;

public class EDevletAdapter implements CheckCustomerService{
    @Override
    public boolean checkCustomerRealPerson(Customer customer) {
        EDevletManager manager=new EDevletManager();
        return manager.checkRealPerson(customer.getFirstName(), customer.getLastName(),
                customer.getIdentityNumber(), customer.getBirthOfYear());
    }
}

package org.example;

public class CustomerManager implements CustomerService{
    private CheckCustomerService service;

    public CustomerManager(CheckCustomerService service) {
        this.service = service;
    }

    @Override
    public void registerCustomer(Customer customer) {
        if (service.checkCustomerRealPerson(customer)){
            System.out.println("Başarıyla kayıt olundu");
        }
        else{
            System.out.println("Böyle bir kişi bulunamadı !");
        }
    }
}

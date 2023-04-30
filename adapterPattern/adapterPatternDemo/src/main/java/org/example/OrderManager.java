package org.example;

public class OrderManager {
    private PosService posService;

    public OrderManager(PosService posService) {
        this.posService = posService;
    }

    public void MakePayment(){
        IsBankPosService isBankPosService=new IsBankPosService();
        if(posService.checkPayment())
        {
        System.out.println("Payment Successful");
        }
        else{
            System.out.println("Payment failure");
        }

    }
}

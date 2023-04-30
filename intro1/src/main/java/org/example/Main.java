package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*for (int i=2;i<1001;i++){
            int sum=0;
            for(int j=1;j<i;j++){
                if(i%j==0){
                    sum+=j;
                }
            }
            if(sum==i){
                System.out.println("Süper Sayı :"+i);
            }

        }*/

        Product product1=new Product(1,"IPhone14",30000,"telefon",10);
        Product product2=new Product(2,"Samsung Galaxy S21",20000,"telefon",10);
        Product product3=new Product(3,"PS5",15000,"telefon",10);

        Product[] products={product1,product2,product3};

        for (Product product:products){
            System.out.println(product.getName() + " : "+product.getUnitPrice() + " %"+
                    product.getDiscount() + " uygulandıktan sonra birim fiyatı : "+product.getUnitPriceAfterDiscount());
        }
    }
}
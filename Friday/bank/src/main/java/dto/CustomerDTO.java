
package dto;

import entity.BankCustomer;


public class CustomerDTO {
    
    int customerID;
    String fullName; 
    String accountNumber;
    double balance;

    public CustomerDTO(){
    }
    
    public CustomerDTO(BankCustomer customer){
        long l = customer.getId();
        int i = (int) l;
        this.customerID = i;
        this.fullName = customer.getFirstName() + " " + customer.getLastName();
        this.balance = customer.getBalance();
    }
}

package org.example;

public class Customer {
    private String firstName;
    private String lastName;
    private String identityNumber;
    private String birthOfYear ;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String identityNumber, String birthOfYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.identityNumber = identityNumber;
        this.birthOfYear = birthOfYear;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getBirthOfYear() {
        return birthOfYear;
    }

    public void setBirthOfYear(String birthOfYear) {
        this.birthOfYear = birthOfYear;
    }
}

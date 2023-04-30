package org.example;

import java.util.ArrayList;
import java.util.List;

public class EDevletManager {
    private List<Person> people = new ArrayList<>();

    public EDevletManager() {
        people.add(new Person("Salih", "Dürüs", "1234567980", "1999"));
        people.add(new Person("Ali", "Bozkurt", "1234567980", "2000"));
    }

    public boolean checkRealPerson(String firstName, String lastName, String identityNumber, String birthOfYear) {
        for (Person p :people) {
            if (p.getFirstName().equalsIgnoreCase(firstName)&&
                p.getLastName().equalsIgnoreCase(lastName)&&
                p.getIdentityNumber().equalsIgnoreCase(identityNumber)&&
                p.getBirthOfYear().equalsIgnoreCase(birthOfYear)){
                return true;
            }
        }
        return false;
    }
}

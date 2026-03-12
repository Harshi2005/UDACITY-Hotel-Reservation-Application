package service;

import model.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private static final ArrayList<Customer> guestList = new ArrayList<>();

    public void addCustomer(String email, String firstName, String lastName) {
        Customer guest = new Customer(firstName, lastName, email);
        guestList.add(guest);
        // test record
        Customer testGuest = new Customer("jdhcdbchkdb", "ncducnucn", "hbhbfhh@gmail.com");
        guestList.add(testGuest);
    }

    public Customer getCustomer(String customerEmail) {
        for (Customer g : guestList) if (g.getEmail().equals(customerEmail)) return g;
        return null;
    }

    public List<Customer> getAllCustomers() { return guestList; }
}

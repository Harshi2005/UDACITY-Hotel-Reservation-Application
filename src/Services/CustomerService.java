// CustomerService.java
package service;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer responsible for storing and retrieving all customer records.
 * Includes basic search and addition functionality.
 */
public class CustomerService {
    private static final ArrayList<Customer> customers = new ArrayList<>();

    public List<Customer> getCustomerCollection(String customerEmail) {
        List<Customer> matchingCustomers = new ArrayList<>();
        for (Customer currentCust : customers) {
            if (currentCust.getEmail().equals(customerEmail)) {
                matchingCustomers.add(currentCust);
            }
        }
        return matchingCustomers;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        customers.add(customer);
        // placeholder record for testing
        Customer customer1 = new Customer("jdhcdbchkdb", "ncducnucn", "hbhbfhh@gmail.com");
        customers.add(customer1);
    }

    public Customer getCustomer(String customerEmail) {
        for (Customer currentCust : customers) {
            if (currentCust.getEmail().equals(customerEmail)) {
                return currentCust;
            }
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }
}

package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Admin gateway – updated to accept IRoom for polymorphism.
 */
public class AdminResource {
    private static final CustomerService custManager = new CustomerService();
    private static final ReservationService bookingManager = new ReservationService();

    public void addCustomer(String email, String firstName, String lastName) {
        custManager.addCustomer(email, firstName, lastName);
    }

    public Customer getCustomer(String customerEmail) {
        return custManager.getCustomer(customerEmail);
    }

    public List<Customer> getAllCustomers() {
        return custManager.getAllCustomers();
    }

    public void addRoom(IRoom room) {
        bookingManager.addRoom(room);
    }

    public Reservation reserveRoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        return bookingManager.reserveRoom(customer, room, checkInDate, checkOutDate);
    }

    public List<Reservation> getCustomerReservation(Customer customer) {
        return bookingManager.getCustomerReservation(customer);
    }

    public void displayAllReservations() {
        bookingManager.printAllReservations();
    }

    public List<Reservation> getCustomerReservations(String customerEmail) {
        return bookingManager.getCustomerReservations(customerEmail);
    }

    public List<IRoom> allRooms() {
        return bookingManager.allRooms();
    }

    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate) {
        return bookingManager.findARoom(checkInDate, checkOutDate);
    }
}

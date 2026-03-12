package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.RoomType;

import service.CustomerService;
import service.ReservationService;

import java.util.Date;
import java.util.List;

public class HotelResource {
    private static final CustomerService customerHandler = new CustomerService();
    private static final ReservationService reservationHandler = new ReservationService();

    public void createACustomer(String email, String firstName, String lastName) {
        customerHandler.addCustomer(email, firstName, lastName);
    }

    public Customer getCustomer(String customerEmail) {
        return customerHandler.getCustomer(customerEmail);
    }

    public List<Customer> getAllCustomers() {
        return customerHandler.getAllCustomers();
    }

    public void addRoom(IRoom room) {
        reservationHandler.addRoom(room);
    }

    public IRoom getRoom(String roomNumber) {
        return reservationHandler.getARoom(roomNumber);
    }

    public Reservation bookARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        return reservationHandler.reserveRoom(customer, room, checkInDate, checkOutDate);
    }

    public List<IRoom> findARoom(Date checkInDate, Date checkOutDate) {
        return reservationHandler.findARoom(checkInDate, checkOutDate);
    }

    public void printAllReservations() {
        reservationHandler.printAllReservations();
    }

    public List<Reservation> getCustomerReservations(String customerEmail) {
        return reservationHandler.getCustomerReservations(customerEmail);
    }
}

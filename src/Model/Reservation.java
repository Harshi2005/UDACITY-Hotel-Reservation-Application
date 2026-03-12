package model;

import java.util.Date;

public class Reservation {
    private Customer customer;
    private IRoom Iroom;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, IRoom iroom, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        Iroom = iroom;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customer=" + customer +
                ", Iroom=" + Iroom +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }

    public Customer getCustomer() { return customer; }
    public IRoom getIroom() { return Iroom; }
    public Date getCheckInDate() { return checkInDate; }
    public Date getCheckOutDate() { return checkOutDate; }
}

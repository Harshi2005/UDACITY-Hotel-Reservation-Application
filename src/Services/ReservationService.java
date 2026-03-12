// ReservationService.java
package service;

import model.Customer;
import model.FreeRoom;
import model.IRoom;
import model.Reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Central service managing room inventory and all reservation records.
 * Handles booking creation, room lookup, and reservation queries.
 */
public class ReservationService {
    private static final ArrayList<IRoom> rooms = new ArrayList<>();
    private static final ArrayList<Reservation> reservations = new ArrayList<>();

    public void addRoom(IRoom room) {
        rooms.add(room);
    }

    public IRoom getARoom(String roomId) {
        for (IRoom room : rooms) {
            if (room.getRoomNumber().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    public Reservation reserveRoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public List<IRoom> findARoom(Date checkInDate, Date checkOutDate) {
        List<IRoom> availableRooms = new ArrayList<>();
        try {
            for (Reservation currentRes : reservations) {
                if (currentRes.getCheckInDate() == checkInDate &&
                        currentRes.getCheckOutDate() == checkOutDate) {
                    availableRooms.add(currentRes.getIroom());
                }
            }
        } catch (Exception e){
            if (reservations.isEmpty()) return null;
        }
        return availableRooms;
    }

    public List<Reservation> getCustomerReservation(Customer customer) {
        List<Reservation> customerBookings = new ArrayList<>();
        for (Reservation res : reservations) {
            if (res.getCustomer().equals(customer)) {
                customerBookings.add(res);
            }
        }
        return customerBookings;
    }

    public void printAllReservations() {
        System.out.println(reservations);
    }

    public List<Reservation> getCustomerReservations(String customerEmail) {
        List<Reservation> customerBookingsByEmail = new ArrayList<>();
        for (Reservation res : reservations) {
            if (res.getCustomer().getEmail().equals(customerEmail)) {
                customerBookingsByEmail.add(res);
            }
        }
        return customerBookingsByEmail;
    }

    public List<IRoom> allRooms() {
        return rooms;
    }

    static class FindRoom {
        Date checkInDate;
        Date checkOutDate;

        List<FreeRoom> availableRooms = new ArrayList<>();
        List<FreeRoom> suggestedRooms = new ArrayList<>();

        public List<FreeRoom> getOpenRooms() {
            for (FreeRoom fr : availableRooms) {
                System.out.println(fr);
            }
            return availableRooms;
        }

        public List<FreeRoom> getRecommendedRooms() {
            for (FreeRoom fr : suggestedRooms) {
                System.out.println(fr);
            }
            return suggestedRooms;
        }
    }
}

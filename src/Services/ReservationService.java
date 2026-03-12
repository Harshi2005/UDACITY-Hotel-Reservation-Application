// ReservationService.java
package service;

import model.Customer;
import model.FreeRoom;
import model.IRoom;
import model.Reservation;
import model.RoomType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Central service managing room inventory and all reservation records.
 * Handles booking creation, room lookup, and reservation queries.
 */
public class ReservationService {
    private static final ArrayList<IRoom> roomInventory = new ArrayList<>();
    private static final ArrayList<Reservation> bookingRecords = new ArrayList<>();

    public void addRoom(IRoom room) {
        if (getARoom(room.getRoomNumber()) != null)
            throw new IllegalArgumentException("Room number already exists!");
        roomInventory.add(room);
    }

    public IRoom getARoom(String roomId) {
        for (IRoom r : roomInventory) if (r.getRoomNumber().equals(roomId)) return r;
        return null;
    }

    public Reservation reserveRoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        if (checkInDate.before(new Date())) throw new IllegalArgumentException("Check-in cannot be in the past!");
        if (!checkOutDate.after(checkInDate)) throw new IllegalArgumentException("Check-out must be after check-in!");

        for (Reservation existing : bookingRecords) {
            if (existing.getIroom().getRoomNumber().equals(room.getRoomNumber())) {
                if (!(checkOutDate.before(existing.getCheckInDate()) || checkInDate.after(existing.getCheckOutDate()))) {
                    throw new IllegalArgumentException("Room is already booked for overlapping dates!");
                }
            }
        }
        Reservation booking = new Reservation(customer, room, checkInDate, checkOutDate);
        bookingRecords.add(booking);
        return booking;
    }

    public List<IRoom> findARoom(Date checkInDate, Date checkOutDate) {
        List<IRoom> available = new ArrayList<>();
        for (IRoom room : roomInventory) {
            boolean booked = false;
            for (Reservation res : bookingRecords) {
                if (res.getIroom().getRoomNumber().equals(room.getRoomNumber())) {
                    if (!(checkOutDate.before(res.getCheckInDate()) || checkInDate.after(res.getCheckOutDate()))) {
                        booked = true; break;
                    }
                }
            }
            if (!booked) available.add(room);
        }
        if (available.isEmpty()) {
            System.out.println("No rooms available on selected dates.");
            System.out.println("→ Recommended dates (7 days later):");
            Date recIn = new Date(checkInDate.getTime() + 7L * 86400000);
            Date recOut = new Date(checkOutDate.getTime() + 7L * 86400000);
            System.out.println(recIn + " to " + recOut);
            return roomInventory;
        }
        return available;
    }

    public List<Reservation> getCustomerReservation(Customer customer) {
        List<Reservation> list = new ArrayList<>();
        for (Reservation r : bookingRecords) if (r.getCustomer().equals(customer)) list.add(r);
        return list;
    }

    public void printAllReservations() { System.out.println(bookingRecords); }

    public List<Reservation> getCustomerReservations(String email) {
        List<Reservation> list = new ArrayList<>();
        for (Reservation r : bookingRecords) if (r.getCustomer().getEmail().equals(email)) list.add(r);
        return list;
    }

    public List<IRoom> allRooms() { return roomInventory; }

    // Original inner class kept for similarity
    static class FindRoom {
        Date checkInDate; Date checkOutDate;
        List<FreeRoom> openRooms = new ArrayList<>();
        List<FreeRoom> suggestedRooms = new ArrayList<>();
    }
}

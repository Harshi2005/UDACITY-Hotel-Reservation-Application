package menu;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.RoomType;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Main user menu – now with proper error handling and real room search.
 */
public class MainMenu {
    private static final AdminResource adminAccess = new AdminResource();
    private static final HotelResource hotelAccess = new HotelResource();
    public static int exitCode = 5;
    public static Scanner keyboard;

    public static void main(String[] args) {
        keyboard = new Scanner(System.in);
        int choice = 0;
        while (choice != exitCode) {
            try {
                choice = displayMainOptions();
                processUserChoice(choice);
            } catch (Exception e) {
                System.out.println("Invalid input! " + e.getMessage());
            }
        }
    }

    private static void processUserChoice(int choice) {
        switch (choice) {
            case 0: searchAndBookRoom(); break;
            case 1: viewUserBookings(); break;
            case 2: registerNewCustomer(); break;
            case 3: AdminMenu.linkAdminResource(adminAccess); AdminMenu.launchAdminPanel(); break;
            case 4: keyboard.close(); System.exit(0);
            default: System.out.println("Invalid choice!");
        }
    }

    private static void viewUserBookings() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Customer Email:");
        String email = sc.next();
        System.out.println(hotelAccess.getCustomerReservations(email));
    }

    private static Customer registerNewCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Customer First Name:");
        String fname = sc.next();
        System.out.println("Enter Customer Last Name:");
        String lname = sc.next();
        System.out.println("Enter Customer Email:");
        String mail = sc.next();
        hotelAccess.createACustomer(mail, fname, lname);
        return new Customer(fname, lname, mail);
    }

    private static void searchAndBookRoom() {
        Scanner sc = new Scanner(System.in);
        Date checkIn = new Date();
        Date checkOut = new Date(checkIn.getTime() + 86400000L); // next day

        List<IRoom> available = hotelAccess.findARoom(checkIn, checkOut);
        if (available.isEmpty()) {
            System.out.println("No rooms available on selected dates.");
            return;
        }
        IRoom chosen = available.get(0);
        Customer cust = registerNewCustomer();
        hotelAccess.bookARoom(cust, chosen, checkIn, checkOut);
        System.out.println("Reservation was Created" + (chosen.isFree() ? " ★★★ FREE ROOM ★★★" : ""));
    }

    private static int displayMainOptions() {
        String[] options = {
                "Find and reserve a room",
                "See my reservations",
                "Create an account",
                "Admin",
                "Exit"
        };
        for (int i = 0; i < options.length; i++) {
            System.out.println(" " + i + " " + options[i]);
        }
        System.out.println("...........................");
        System.out.println("Enter menu:");
        return keyboard.nextInt();
    }
}

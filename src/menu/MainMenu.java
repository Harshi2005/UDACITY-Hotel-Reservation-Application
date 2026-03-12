// MainMenu.java
package menu;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.Room;
import model.RoomType;

import java.util.Date;
import java.util.Scanner;

/**
 * Primary user interface menu for the hotel reservation application.
 * Routes user choices to booking, account creation, and admin features.
 */
public class MainMenu {
    private static final AdminResource adminResource = new AdminResource();
    private static final HotelResource hotelResource = new HotelResource();
    public static int exitApp = 5;
    public static Scanner input;

    public static void main(String[] args) {
        input = new Scanner(System.in);
        int menuSelection = 0;

        while (menuSelection != exitApp) {
            menuSelection = showMenu();
            switch (menuSelection) {
                case 0:
                    findAndReserveRoom();
                    break;
                case 1:
                    seeMyReservations();
                    break;
                case 2:
                    createAccount();
                case 3:
                    AdminMenu.setAdminResource(adminResource);
                    AdminMenu.startAdmin();
                    break;
                case 4:
                    input.close();
                    System.exit(0);
                default:
                    showMenu();
                    break;
            }
        }
    }

    private static void seeMyReservations() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter Customer Email:");
        String custEmail = userInput.next();
        System.out.println(hotelResource.getCustomerReservations(custEmail));
    }

    private static Customer createAccount() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter Customer First Name:");
        String custFirstName = userInput.next();
        System.out.println("Enter Customer Last Name:");
        String custLastName = userInput.next();
        System.out.println("Enter Customer Email:");
        String custEmail = userInput.next();

        hotelResource.createACustomer(custEmail, custFirstName, custLastName);
        return new Customer(custFirstName, custLastName, custEmail);
    }

    private static void findAndReserveRoom() {
        Room hotelRoom = new Room();
        hotelRoom.roomType = RoomType.SINGLE;
        hotelRoom.price = 12.0;
        hotelRoom.roomNumber = "133";

        hotelResource.addRoom(hotelRoom);
        hotelResource.getRoom("133");

        hotelResource.bookARoom(createAccount(), hotelRoom, new Date(),
                new Date());
        System.out.println("Reservation was Created");
    }

    private static int showMenu() {
        String[] menuOptions = new String[]{
                "Find and reserve a room",
                "See my reservations",
                "Create an account",
                "Admin",
                "Exit"
        };

        for (int i = 0; i < menuOptions.length; i++) {
            System.out.println(" " + i + " " + menuOptions[i]);
        }

        System.out.println("...........................");
        System.out.println("Enter menu:");
        return input.nextInt();
    }
}

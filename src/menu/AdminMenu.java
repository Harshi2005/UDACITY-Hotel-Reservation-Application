// AdminMenu.java
package menu;

import api.AdminResource;
import model.Room;
import model.RoomType;

import java.util.Scanner;

/**
 * Dedicated menu for administrative operations including viewing
 * all customers, rooms, reservations, and adding new rooms.
 */
public class AdminMenu {
    public static int navigateToMainMenu = 4;
    public static Scanner input;
    private static AdminResource adminResource;

    public static void startAdmin() {
        input = new Scanner(System.in);
        int menuChoice = 0;

        while (menuChoice != navigateToMainMenu) {
            menuChoice = showAdminMenu();
            switch (menuChoice) {
                case 0:
                    System.out.println(adminResource.getAllCustomers());
                    break;
                case 1:
                    System.out.println(adminResource.allRooms());
                    break;
                case 2:
                    adminResource.displayAllReservations();
                    break;
                case 3:
                    addRoom();

                case 4:
                    String[] arguments = new String[] {"123"};
                    MainMenu.main(arguments);
                default:
                    showAdminMenu();
                    break;
            }
        }
    }

    private static void addRoom() {
        Room hotelRoom = new Room();
        Scanner roomInput = new Scanner(System.in);
        System.out.println("Enter Room Number:");
        hotelRoom.roomNumber = roomInput.nextLine().trim();
        System.out.println("Enter Room Price:");
        hotelRoom.price = roomInput.nextDouble();
        System.out.println("Choose Room Type: \n 1 -> SINGLE \n 2-> DOUBLE");
        int roomTypeChoice = roomInput.nextInt();
        if (roomTypeChoice == 1) {
            hotelRoom.roomType = RoomType.SINGLE;
        } else {
            hotelRoom.roomType = RoomType.DOUBLE;
        }
        adminResource.addRoom(hotelRoom);
    }

    private static int showAdminMenu() {
        String[] adminOptions = new String[]{
                "See all Customers",
                "See all Rooms",
                "See all Reservations",
                "Add a Room",
                "Back to Main Menu"
        };
        for (int i = 0; i < adminOptions.length; i++) {
            System.out.println(" " + i + " " + adminOptions[i]);
        }
        System.out.println("...........................");
        System.out.println("Enter menu:");
        return input.nextInt();
    }

    public static void setAdminResource(AdminResource adminResource) {
        AdminMenu.adminResource = adminResource;
    }
}

package menu;

import api.AdminResource;
import model.FreeRoom;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.Scanner;

/**
 * Admin panel – now creates FreeRoom when price = 0 and has validation.
 */
public class AdminMenu {
    public static int returnToMain = 4;
    public static Scanner keyboard;
    private static AdminResource adminAccess;

    public static void launchAdminPanel() {
        keyboard = new Scanner(System.in);
        int choice = 0;
        while (choice != returnToMain) {
            try {
                choice = showAdminOptions();
                handleAdminSelection(choice);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void handleAdminSelection(int choice) {
        switch (choice) {
            case 0: System.out.println(adminAccess.getAllCustomers()); break;
            case 1: System.out.println(adminAccess.allRooms()); break;
            case 2: adminAccess.displayAllReservations(); break;
            case 3: createNewRoom(); break;
            case 4: String[] dummy = {"123"}; MainMenu.main(dummy); break;
            default: System.out.println("Invalid choice!");
        }
    }

    private static void createNewRoom() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Room Number:");
        String num = sc.nextLine().trim();
        System.out.println("Enter Room Price (0 for FREE):");
        double price = sc.nextDouble();
        System.out.println("Choose Room Type: 1 -> SINGLE  2 -> DOUBLE");
        int t = sc.nextInt();
        RoomType type = (t == 1) ? RoomType.SINGLE : RoomType.DOUBLE;

        IRoom newRoom = (price == 0.0) ? new FreeRoom(num, type) : new Room(num, price, type);
        adminAccess.addRoom(newRoom);
        System.out.println("Room added!" + (price == 0.0 ? " ★★★ FREE ROOM ★★★" : ""));
    }

    private static int showAdminOptions() {
        String[] opts = {
                "See all Customers",
                "See all Rooms",
                "See all Reservations",
                "Add a Room",
                "Back to Main Menu"
        };
        for (int i = 0; i < opts.length; i++) {
            System.out.println(" " + i + " " + opts[i]);
        }
        System.out.println("...........................");
        System.out.println("Enter menu:");
        return keyboard.nextInt();
    }

    public static void linkAdminResource(AdminResource r) {
        adminAccess = r;
    }
}

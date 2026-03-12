package model;

/**
 * Standard room with encapsulation and validation.
 */
public class Room implements IRoom {
    private final String roomNumber;
    private final Double price;
    private final RoomType roomType;

    public Room(String roomNumber, Double price, RoomType roomType) {
        if (roomNumber == null || roomNumber.trim().isEmpty())
            throw new IllegalArgumentException("Room number cannot be empty!");
        if (price < 0)
            throw new IllegalArgumentException("Price cannot be negative!");
        this.roomNumber = roomNumber.trim();
        this.price = price;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "model.Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", roomType=" + roomType +
                '}';
    }

    @Override public String getRoomNumber() { return roomNumber; }
    @Override public Double getRoomPrice() { return price; }
    @Override public RoomType getRoomType() { return roomType; }
    @Override public boolean isFree() { return false; }
}

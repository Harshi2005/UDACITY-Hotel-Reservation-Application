// Room.java
package model;

/**
 * Standard room implementation that fulfills the IRoom contract.
 * Holds room identifier, pricing, and type information.
 */
public class Room implements IRoom {
    public String roomNumber ;
    public Double price;
    public RoomType roomType;

    @Override
    public String toString() {
        return "model.Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", roomType=" + roomType +
                '}';
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

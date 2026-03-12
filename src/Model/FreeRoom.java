package model;

/**
 * Free room – demonstrates inheritance and is displayed differently.
 */
public class FreeRoom extends Room {
    public FreeRoom(String roomNumber, RoomType roomType) {
        super(roomNumber, 0.0, roomType);
    }

    @Override
    public boolean isFree() {
        return true;
    }

    @Override
    public String toString() {
        return "★★★ FREE ROOM (Price $0.0) ★★★ {" +
                "roomNumber='" + getRoomNumber() + '\'' +
                ", roomType=" + getRoomType() +
                '}';
    }
}

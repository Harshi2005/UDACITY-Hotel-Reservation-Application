// FreeRoom.java
package model;

/**
 * Extension of Room that represents a complimentary room with zero cost.
 * Used for special availability cases.
 */
public class FreeRoom extends Room {
    public FreeRoom() {
        this.price = 0.0;
    }

    @Override
    public String toString() {
        return "model.FreeRoom{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", roomType=" + roomType +
                '}';
    }
}

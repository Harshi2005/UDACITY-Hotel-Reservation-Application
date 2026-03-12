// IRoom.java
package model;

/**
 * Contract for any room entity in the hotel booking system.
 * Exposes essential room properties and availability check.
 */
public interface IRoom {
    String getRoomNumber();
    Double getRoomPrice();
    RoomType getRoomType();
    boolean isFree();
}

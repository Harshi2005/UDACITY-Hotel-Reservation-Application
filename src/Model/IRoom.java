package model;

/**
 * Interface for all room types – enables polymorphism.
 */
public interface IRoom {
    String getRoomNumber();
    Double getRoomPrice();
    RoomType getRoomType();
    boolean isFree();
}

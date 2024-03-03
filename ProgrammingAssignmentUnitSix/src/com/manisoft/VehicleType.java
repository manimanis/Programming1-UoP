package com.manisoft;

/**
 * An enumeration of Vehicle types.
 *
 * @author Cyberbox
 */
public enum VehicleType {
    CAR,
    MOTORCYCLE,
    TRUCK;

    /**
     * Vehicle types in String format.
     */
    public static final String[] TYPES = new String[]{
        "Car", "Motorcycle", "Truck"};

    /**
     * Search for index of vehicle name.
     * @param str String
     * @return int
     */
    public int indexOf(String str) {
        for (int i = 0; i < TYPES.length; i++) {
            if (str.equalsIgnoreCase(TYPES[i])) {
                return i;
            }
        }
        return -1;
    }    

    /**
     * Provides a String representation of this object.
     * @return String
     */
    @Override
    public String toString() {
        return TYPES[ordinal()];
    }

    /**
     * Converts an int value to a VehicleType data type.
     *
     * @param index int
     * @return VehicleType
     */
    public static VehicleType valueOf(int index) {
        return switch (index) {
            case 0 ->
                VehicleType.CAR;
            case 1 ->
                VehicleType.MOTORCYCLE;
            case 2 ->
                VehicleType.TRUCK;
            default ->
                null;
        };
    }
}

package com.manisoft;

/**
 *
 * @author Cyberbox
 */
public enum VehicleType {
    CAR,
    MOTORCYCLE,
    TRUCK;

    public static final String[] TYPES = new String[]{"Car", "Motorcycle", "Truck"};

    public int fromString(String str) {
        for (int i = 0; i < TYPES.length; i++) {
            if (str.equalsIgnoreCase(TYPES[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return TYPES[ordinal()];
    }

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

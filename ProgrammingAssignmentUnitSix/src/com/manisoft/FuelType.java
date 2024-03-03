package com.manisoft;

/**
 * An enumeration of fuel types.
 *
 * @author manianis
 */
public enum FuelType {
    PETROL,
    DIESEL,
    ELECTRIC;

    /**
     * Converts an int value to a FuelType data type.
     *
     * @param index int
     * @return FuelType
     */
    public static FuelType valueOf(int index) {
        return switch (index) {
            case 0 ->
                FuelType.PETROL;
            case 1 ->
                FuelType.DIESEL;
            case 2 ->
                FuelType.ELECTRIC;
            default ->
                null;
        };
    }
}

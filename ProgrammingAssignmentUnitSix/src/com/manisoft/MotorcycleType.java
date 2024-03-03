package com.manisoft;

/**
 * An enumeration of motorcycles types.
 *
 * @author manianis
 */
public enum MotorcycleType {
    SPORT,
    CRIUSER,
    OFF_ROAD;

    /**
     * Converts an int value to a MotorcycleType data type.
     *
     * @param index int
     * @return MotorcycleType
     */
    public static MotorcycleType valueOf(int index) {
        return switch (index) {
            case 0 ->
                MotorcycleType.SPORT;
            case 1 ->
                MotorcycleType.CRIUSER;
            case 2 ->
                MotorcycleType.OFF_ROAD;
            default ->
                null;
        };
    }
}

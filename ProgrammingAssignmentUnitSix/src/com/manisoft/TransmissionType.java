package com.manisoft;

/**
 * An enumeration of Transmission types.
 *
 * @author manianis
 */
public enum TransmissionType {
    MANUAL,
    AUTOMATIC;

    /**
     * Converts an int value to a TransmissionType data type.
     *
     * @param index int
     * @return TransmissionType
     */
    public static TransmissionType valueOf(int index) {
        return switch (index) {
            case 0 ->
                TransmissionType.MANUAL;
            case 1 ->
                TransmissionType.AUTOMATIC;
            default ->
                null;
        };
    }
}


package com.manisoft;

/**
 *
 * @author manianis
 */
public enum TransmissionType {
    MANUAL,
    AUTOMATIC;
    
    public static TransmissionType valueOf(int index) {
        return switch (index) {
            case 0 -> TransmissionType.MANUAL;
            case 1 -> TransmissionType.AUTOMATIC;
            default -> null;
        };
    }
}

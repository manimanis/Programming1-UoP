
package com.manisoft;

/**
 *
 * @author manianis
 */
public enum MotorcycleType {
    SPORT,
    CRIUSER,
    OFF_ROAD;
    
    public static MotorcycleType valueOf(int index) {
        return switch (index) {
            case 0 -> MotorcycleType.SPORT;
            case 1 -> MotorcycleType.CRIUSER;
            case 2 -> MotorcycleType.OFF_ROAD;
            default -> null;
        };
    }
}

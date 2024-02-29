
package com.manisoft;

/**
 *
 * @author manianis
 */
public enum FuelType {
    PETROL,
    DIESEL,
    ELECTRIC;
    
    public static FuelType valueOf(int index) {
        return switch (index) {
            case 0 -> FuelType.PETROL;
            case 1 -> FuelType.DIESEL;
            case 2 -> FuelType.ELECTRIC;
            default -> null;
        };
    }
}

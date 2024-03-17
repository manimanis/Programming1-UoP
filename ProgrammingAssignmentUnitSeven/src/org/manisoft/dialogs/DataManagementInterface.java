
package org.manisoft.dialogs;

/**
 * A contract that all dialog should implements.
 * @author manianis
 * @param <T>
 */
public interface DataManagementInterface<T extends Object> {
    
    T addNew();
    T editItem(int index);
    T removeItem(int index);
}

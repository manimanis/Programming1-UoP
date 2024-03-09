
package org.manisoft.forms;

/**
 *
 * @author manianis
 * @param <T>
 */
public interface DataManagementInterface<T extends Object> {
    T addNew();
    T editItem(int index);
    T removeItem(int index);
}

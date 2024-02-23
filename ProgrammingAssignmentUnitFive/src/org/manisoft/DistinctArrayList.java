
package org.manisoft;

import java.util.ArrayList;

/**
 *
 * @author manianis
 * @param <T>
 */
public class DistinctArrayList<T> extends ArrayList<T> {

    @Override
    public boolean add(T item) {
        int pos = indexOf(item);
        if (pos != -1) {
            return false;
        }
        return super.add(item);
    }

    @Override
    public void add(int index, T item) {
        this.add(item);
    }
    
}

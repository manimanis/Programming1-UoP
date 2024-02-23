
package org.manisoft;

import java.util.ArrayList;

/**
 *
 * @author manianis
 */
public class DistinctArrayList<T> extends ArrayList<T> {

    @Override
    public boolean add(T item) {
        int pos = indexOf(item);
        if (pos != 0) {
            return false;
        }
        return super.add(item);
    }

    @Override
    public void add(int index, T item) {
        add(item);
    }
    
}

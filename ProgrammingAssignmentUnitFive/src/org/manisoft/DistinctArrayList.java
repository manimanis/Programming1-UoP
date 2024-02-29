
package org.manisoft;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author manianis
 * @param <T>
 */
public class DistinctArrayList<T> extends ArrayList<T> {

    /**
     * Add the item only if it does not exists.
     * @param item
     * @return 
     */
    @Override
    public boolean add(T item) {
        int pos = indexOf(item);
        if (pos != -1) {
            return false;
        }
        return super.add(item);
    }

    /**
     * Replaces the default implementation, we cannot insert an
     * item at a position.
     * @param index
     * @param item 
     */
    @Override
    public void add(int index, T item) {
        this.add(item);
    }
    
    
    /**
     * Reads a StudentList from the input stream.
     *
     * @param ois
     * @throws IOException
     * @throws ClassNotFoundException
     */
    protected void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        int count = ois.readInt();
        clear();
        for (int i = 0; i < count; i++) {
            add((T)ois.readObject());
        }
    }

    /**
     * Writes a StudentList to the output stream.
     *
     * @param oos
     * @throws IOException
     */
    protected void writeObject(ObjectOutputStream oos)
            throws IOException {
        oos.writeInt(size());
        for (T item : this) {
            oos.writeObject(item);
        }
    }
}

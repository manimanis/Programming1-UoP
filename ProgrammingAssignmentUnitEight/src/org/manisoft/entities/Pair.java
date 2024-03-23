package org.manisoft.entities;

import java.util.Objects;

/**
 *
 * @author manianis
 * @param <T1>
 * @param <T2>
 */
public class Pair<T1 extends Comparable, T2 extends Comparable> implements Comparable<Pair<T1, T2>> {

    public T1 first;
    public T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.first);
        hash = 17 * hash + Objects.hashCode(this.second);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair<?, ?> other = (Pair<?, ?>) obj;
        if (!Objects.equals(this.first, other.first)) {
            return false;
        }
        return Objects.equals(this.second, other.second);
    }

    @Override
    public int compareTo(Pair<T1, T2> o) {
        if (o == null) {
            return 1;
        }
        if (o == this) {
            return 0;
        }
        Pair<T1, T2> p = (Pair<T1, T2>)o;
        int cmp = first.compareTo(p.first);
        if (cmp != 0) {
            return cmp;
        }
        
        return second.compareTo(p.second);
    }

}

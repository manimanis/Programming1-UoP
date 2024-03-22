
package org.manisoft.entities;

import java.util.stream.DoubleStream;

/**
 *
 * @author manianis
 * @param <T>
 */
public interface DescriptiveStatisticsInterface<T> {
    void calcStatistics();
    
    int count();
    double getMin();
    double getMax();
    double getAvg();
    double getStd();
    double getQuantile(int percent);
    
    DoubleStream getDoubleStream();
}

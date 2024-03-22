package org.manisoft.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.stream.DoubleStream;

/**
 *
 * @author manianis
 * @param <T>
 */
public class DescriptiveStatistics implements DescriptiveStatisticsInterface {

    protected ToDoubleFunction<Employee> mapFunc;
    protected List<Employee> employees;
    protected double[] sortedValues;
    protected double avg;
    protected double std;

    public DescriptiveStatistics(List<Employee> employees, ToDoubleFunction<Employee> mapFunc) {
        this.employees = employees;
        this.mapFunc = mapFunc;
        this.sortedValues = getDoubleStream()
                .sorted()
                .toArray();
        calcStatistics();
    }

    @Override
    public final void calcStatistics() {
        avg = getDoubleStream()
                .average()
                .getAsDouble();
        if (employees.isEmpty()) {
            std = 0.0;
        }
        std = Math.sqrt(getDoubleStream()
                .reduce(0, (total, x)
                        -> (x - getAvg()) * (x - getAvg()) / employees.size()));
    }

    @Override
    public int count() {
        return sortedValues.length;
    }
    
    @Override
    public double getMin() {
        return sortedValues[0];
    }

    @Override
    public double getMax() {
        return sortedValues[sortedValues.length - 1];
    }

    @Override
    public double getAvg() {
        return avg;
    }

    @Override
    public double getStd() {
        return std;
    }

    @Override
    public double getQuantile(int percent) {
        double idx = sortedValues.length * percent / 100;
        if (idx == (int) idx) {
            return sortedValues[(int) idx];
        } else {
            int idx1 = (int) Math.floor(idx);
            int idx2 = (int) Math.ceil(idx);
            return (sortedValues[idx1] + sortedValues[idx2]) / 2;
        }
    }

    @Override
    public final DoubleStream getDoubleStream() {
        return employees.stream()
                .mapToDouble(mapFunc);
    }
}

package org.manisoft.entities;

import java.util.List;

/**
 *
 * @author Cyberbox
 */
public class DistributionByCountry {

    public String country;
    public DescriptiveStatistics stats;

    public DistributionByCountry(List<Employee> employees, String country) {
        this.country = country;
        this.stats = new DescriptiveStatistics(
                employees.stream()
                        .filter((emp) -> emp.getCountry().equals(country))
                        .toList(),
                (emp) -> emp.getAnnualSalary()
        );
    }

}

package org.manisoft.entities;

import java.util.List;

/**
 *
 * @author Cyberbox
 */
public class DistributionByCountryAndGender {

    public String country;
    public String gender;
    public DescriptiveStatistics stats;

    public DistributionByCountryAndGender(List<Employee> employees,
            String country, String gender) {
        this.country = country;
        this.gender = gender;
        this.stats = new DescriptiveStatistics(
                employees.stream()
                        .filter((emp)
                                -> emp.getCountry().equals(country)
                        && emp.getGender().equals(gender)
                        )
                        .toList(),
                (emp) -> emp.getAnnualSalary()
        );
    }

}

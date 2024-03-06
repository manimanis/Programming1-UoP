
package org.manisoft.containers;

import org.manisoft.entities.Course;

/**
 *
 * @author manianis
 */
public class CourseList extends DistinctArrayList<Course> {

    /**
     * Find a course by his name.
     * 
     * @param name the course to search.
     * @return null if not found.
     */
    public Course getByName(String name) {
        for (Course cs : this) {
            if (name.equalsIgnoreCase(cs.getName())) {
                return cs;
            }
        }
        return null;
    }

    /**
     * Find a course by its code.
     * 
     * @param code the course to search.
     * @return null if not found.
     */
    public Course getByCode(String code) {
        for (Course cs : this) {
            if (code.equalsIgnoreCase(cs.getCode())) {
                return cs;
            }
        }
        return null;
    }
    
}

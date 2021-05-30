package org.wecancodeit.courses;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CourseRepository {

    Map<Long, Course> coursesList = new HashMap<>();

    //constructor to handle our database
    public CourseRepository(){
        Course reading = new Course(1L, "reading", "I love sci fi and horror");
        Course music = new Course(2L, "Music", "Rap and country with some Rock & Roll");
        Course java = new Course(3L, "Java", "The class of all classes of class");

        coursesList.put(reading.getId(), reading);
        coursesList.put(music.getId(), music);
        coursesList.put(java.getId(), java);
    }

    //Constructor for testing purposes only
    public CourseRepository(Course ...coursesToAdd) {
        for(Course course: coursesToAdd){
            coursesList.put(course.getId(), course);
        }
    }

    public Course findOne(long id) {
        return coursesList.get(id);
    }

    public Collection<Course> findAll() {
        return coursesList.values();
    }
}

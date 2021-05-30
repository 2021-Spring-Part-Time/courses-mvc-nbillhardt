package org.wecancodeit.courses;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collection;

@WebMvcTest(CourseController.class)
public class CourseControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private Course courseOne;

    @Mock
    private Course courseTwo;

    @MockBean
    private CourseRepository courseRepo;

    @Test
    public void shouldBeOkForAllCoursesInTheCoursesTemplate() throws Exception {
        mockMvc.perform(get("/courses")).andExpect(status().isOk()).andExpect(view().name("coursesTemplate"));
    }

    @Test
    public void shouldFindAllCoursesInModel() throws Exception {
        Collection<Course> allCoursesInModel = Arrays.asList(courseOne, courseTwo);
        when(courseRepo.findAll()).thenReturn(allCoursesInModel);
        mockMvc.perform(get("/courses")).andExpect(model().attribute("coursesModel", allCoursesInModel));
    }

    @Test
    public void shouldBeOkForAllCourseInTheCourseTemplate() throws Exception {
        Long courseOneId = 1L;
        when(courseRepo.findOne(courseOneId)).thenReturn(courseOne);
        mockMvc.perform(get("/course?id=1")).andExpect(status().isOk()).andExpect(view().name("courseTemplate"));
    }

    @Test
    public void shouldFindCourseOneInModel() throws Exception {
        Long courseOneId = 1L;
        when(courseRepo.findOne(courseOneId)).thenReturn(courseOne);
        mockMvc.perform(get("/course?id=1")).andExpect(model().attribute("courseModel", courseOne));
    }

    @Test
    public void shouldBeNotFoundForRequestNotInModel() throws Exception {
        Long courseTwoId = 2L;
        when(courseRepo.findOne(courseTwoId)).thenReturn(courseTwo);
        mockMvc.perform(get("/course?id=3")).andExpect(status().isNotFound());
    }
}

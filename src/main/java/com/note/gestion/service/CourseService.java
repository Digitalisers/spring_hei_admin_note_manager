package com.note.gestion.service;

import com.note.gestion.model.Course;
import com.note.gestion.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
    private CourseRepository courseRepository;

    //GET mapping
        //1.get all course
    public List<Course> getAllCourses(int page, int pageSize){
        Pageable pageable = PageRequest.of(page-1, pageSize);
        return courseRepository.findAll(pageable).toList();
    }

    //POST mapping
    public Course insertCourse(Course newCourse){
        Course course = new Course();
        course.setName(newCourse.getName());
        course.setCoef(newCourse.getCoef());
        course.setCredits(newCourse.getCredits());
        courseRepository.save(course);
        return course;
    }

    //PUT mapping
    public Course putModification(Long id, Course courseModified){
        Course thisCourse = courseRepository.findById(id).orElseThrow(()-> new NullPointerException("not found"));
        if(thisCourse.getCoef() != courseModified.getCoef()){
            thisCourse.setCoef(courseModified.getCoef());
        }
        if(thisCourse.getName() != courseModified.getName()){
            thisCourse.setName(courseModified.getName());
        }
        if(thisCourse.getCredits() != courseModified.getCredits()){
            thisCourse.setCredits(courseModified.getCredits());
        }
        courseRepository.save(thisCourse);
        return thisCourse;
    }

    //DELETE mapping
    public String deleteCourse(Long id){
        courseRepository.deleteById(id);
        return "course is delete successfully";
    }
}

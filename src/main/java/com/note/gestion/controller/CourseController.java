package com.note.gestion.controller;

import com.note.gestion.model.Course;
import com.note.gestion.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CourseController {
    private CourseService courseService;

    @GetMapping("/course")
    public List<Course> getAllCourses(
            @RequestParam(name = "page")int page,
            @RequestParam(name = "page_size")int pageSize
    ){
        return courseService.getAllCourses(page, pageSize);
    }

    @PostMapping("/course")
    public Course insertCourse(@RequestBody Course newCourse){
        return courseService.insertCourse(newCourse);
    }

    @PutMapping("/course/{id_course}")
    public Course putModification(
            @PathVariable(name = "id_course")Long idCourse,
            @RequestParam Course courseModified
    ){
        return courseService.putModification(idCourse, courseModified);
    }

    @DeleteMapping("/course/{id_course}")
    public String deleteCourse(@PathVariable(name = "id_course")Long idCourse){
        return courseService.deleteCourse(idCourse);
    }
}

package kz.org.iqro.iqro.controllers;

import kz.org.iqro.iqro.entities.Course;
import kz.org.iqro.iqro.services.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final CourseService courseService;

    public AdminController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public String getAllCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "admin/courses";
    }

    @GetMapping("/courses/new")
    public String formNewCourse(Model model) {
        model.addAttribute("course", new Course());
        return "admin/course-form";
    }

    @GetMapping("/courses/edit/{id}")
    public String formEditCourse(Model model, @PathVariable int id) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "admin/course-form";
    }

    @PostMapping("/courses")
    public String saveCourse(Course course) {
        course.setLastUpdatedAt(new Timestamp(System.currentTimeMillis()));
        courseService.saveCourse(course);
        return "redirect:/admin/courses";
    }

    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
        return "redirect:/admin/courses";
    }

    @GetMapping("/courses/{id}")
    public String getCourseById(Model model, @PathVariable int id) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "admin/course";
    }
}

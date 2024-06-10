package kz.org.iqro.iqro.controllers;

import kz.org.iqro.iqro.entities.Course;
import kz.org.iqro.iqro.entities.Module;
import kz.org.iqro.iqro.services.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String getAllCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "course/courses";
    }

    @GetMapping("/all")
    public String allCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "course/catalog";
    }

    @GetMapping("{id}")
    public String getCourseById(Model model, @PathVariable int id) {
        List<Module> modules = courseService.getCourseById(id).getModules();
        modules.sort(Comparator.comparing(Module::getId));

        model.addAttribute("course", courseService.getCourseById(id));
        model.addAttribute("modules", modules);
        return "course/course-info";
    }
}

package kz.org.iqro.iqro.controllers;

import kz.org.iqro.iqro.entities.Course;
import kz.org.iqro.iqro.entities.Module;
import kz.org.iqro.iqro.services.CourseService;
import kz.org.iqro.iqro.services.ModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final CourseService courseService;
    private final ModuleService moduleService;

    public AdminController(CourseService courseService, ModuleService moduleService) {
        this.courseService = courseService;
        this.moduleService = moduleService;
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
        model.addAttribute("modules", moduleService.getAllModulesByCourseId((long) id));
        return "admin/course-details";
    }

    @GetMapping("/courses/{id}/modules/new")
    public String formNewModule(Model model, @PathVariable int id) {
        model.addAttribute("module", new Module());
        model.addAttribute("courseId", id);
        return "admin/module-form";
    }

    @PostMapping("/courses/{id}/modules")
    public String saveModule(Module module, @PathVariable int id) {
        module.setCourse(courseService.getCourseById(id));
        moduleService.saveModule(module);
        return "redirect:/admin/courses/" + id;
    }

    @GetMapping("/courses/{courseId}/modules/edit/{moduleId}")
    public String formEditModule(Model model, @PathVariable int courseId, @PathVariable int moduleId) {
        model.addAttribute("module", moduleService.getModuleById(moduleId));
        model.addAttribute("courseId", courseId);
        return "admin/module-form";
    }

    @GetMapping("/courses/{courseId}/modules/delete/{moduleId}")
    public String deleteModule(@PathVariable int courseId, @PathVariable int moduleId) {
        moduleService.deleteModule(moduleId);
        return "redirect:/admin/courses/" + courseId;
    }
}

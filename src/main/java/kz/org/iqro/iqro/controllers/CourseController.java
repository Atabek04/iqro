package kz.org.iqro.iqro.controllers;

import kz.org.iqro.iqro.entities.Module;
import kz.org.iqro.iqro.entities.User;
import kz.org.iqro.iqro.services.CourseService;
import kz.org.iqro.iqro.services.EnrollmentService;
import kz.org.iqro.iqro.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;
    private final UserService userService;

    public CourseController(CourseService courseService, EnrollmentService enrollmentService, UserService userService) {
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
        this.userService = userService;
    }

    @GetMapping
    public String getAllCourses(Model model, Principal principal) {
        String username = principal.getName();
        Optional<User> user = userService.getUserByUsername(username);
        model.addAttribute("courses", enrollmentService.getAllEnrolledCourses(user));
        return "course/courses";
    }

    @GetMapping("/all")
    public String allCourses(Model model, Principal principal) {
        String username = principal.getName();
        Optional<User> user = userService.getUserByUsername(username);
        model.addAttribute("enrolledCourses", enrollmentService.getAllEnrolledCourses(user));
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

    @GetMapping("/enroll/{id}")
    public String enrollCourse(@PathVariable int id, Principal principal) {
        String username = principal.getName();
        Optional<User> user = userService.getUserByUsername(username);
        enrollmentService.enrollCourse(courseService.getCourseById(id), user.orElse(null));
        return "redirect:/courses";
    }


}

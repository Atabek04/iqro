package kz.org.iqro.iqro.controllers;

import kz.org.iqro.iqro.entities.Course;
import kz.org.iqro.iqro.entities.Lesson;
import kz.org.iqro.iqro.entities.Module;
import kz.org.iqro.iqro.services.CourseService;
import kz.org.iqro.iqro.services.LessonService;
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
    private final LessonService lessonService;

    public AdminController(CourseService courseService, ModuleService moduleService, LessonService lessonService) {
        this.courseService = courseService;
        this.moduleService = moduleService;
        this.lessonService = lessonService;
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
        model.addAttribute("courseModules", moduleService.getAllModulesByCourseId((long) id));
        return "admin/course-details";
    }

    @GetMapping("/courses/{id}/modules/new")
    public String formNewModule(Model model, @PathVariable int id) {
        model.addAttribute("courseModule", new Module());
        model.addAttribute("courseId", id);
        return "admin/module-form";
    }

    @PostMapping("/courses/{id}/modules")
    public String saveModule(Module module, @PathVariable int id) {
        Course course = courseService.getCourseById(id);
        module.setCourse(course);
        course.getModules().add(module); // Add the new module to the course's module list
        courseService.saveCourse(course); // Save the course along with the new module
        return "redirect:/admin/courses/" + id;
    }

    @GetMapping("/courses/{courseId}/modules/edit/{moduleId}")
    public String formEditModule(Model model, @PathVariable int courseId, @PathVariable int moduleId) {
        model.addAttribute("courseModule", moduleService.getModuleById(moduleId));
        model.addAttribute("courseId", courseId);
        return "admin/module-form";
    }

    @GetMapping("/courses/{courseId}/modules/delete/{moduleId}")
    public String deleteModule(@PathVariable int courseId, @PathVariable int moduleId) {
        moduleService.deleteModule(moduleId);
        return "redirect:/admin/courses/" + courseId;
    }

    @GetMapping("/courses/{courseId}/modules/{moduleId}")
    public String getModuleById(Model model, @PathVariable int courseId, @PathVariable int moduleId) {
        model.addAttribute("course", courseService.getCourseById(courseId));
        model.addAttribute("courseModule", moduleService.getModuleById(moduleId));
        model.addAttribute("lessons", lessonService.getAllLessonsByModuleId(moduleId));
        return "admin/module-details";
    }

    @PostMapping("/courses/{courseId}/modules/{moduleId}/lessons")
    public String saveLesson(Lesson lesson, @PathVariable int courseId, @PathVariable int moduleId) {
        lesson.setModule(moduleService.getModuleById(moduleId));
        lessonService.saveLesson(lesson);
        //noinspection SpringMVCViewInspection
        return "redirect:/admin/courses/" + courseId + "/modules/" + moduleId;
    }

    @GetMapping("/courses/{courseId}/modules/{moduleId}/lessons/new")
    public String formNewLesson(Model model, @PathVariable int courseId, @PathVariable int moduleId) {
        model.addAttribute("lesson", new Lesson());
        model.addAttribute("moduleId", moduleId);
        model.addAttribute("courseId", courseId);
        return "admin/lesson-form";
    }

    @GetMapping("/courses/{courseId}/modules/{moduleId}/lessons/edit/{lessonId}")
    public String formEditLesson(Model model, @PathVariable int courseId, @PathVariable int moduleId, @PathVariable int lessonId) {
        model.addAttribute("lesson", lessonService.getLessonById(lessonId));
        model.addAttribute("moduleId", moduleId);
        model.addAttribute("courseId", courseId);
        return "admin/lesson-form";
    }

    @GetMapping("/courses/{courseId}/modules/{moduleId}/lessons/delete/{lessonId}")
    public String deleteLesson(@PathVariable int courseId, @PathVariable int moduleId, @PathVariable int lessonId) {
        lessonService.deleteLesson(lessonId);
        //noinspection SpringMVCViewInspection
        return "redirect:/admin/courses/" + courseId + "/modules/" + moduleId;
    }
}

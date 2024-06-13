package kz.org.iqro.iqro.controllers;

import kz.org.iqro.iqro.entities.Course;
import kz.org.iqro.iqro.entities.Lesson;
import kz.org.iqro.iqro.entities.Module;
import kz.org.iqro.iqro.entities.User;
import kz.org.iqro.iqro.services.CourseService;
import kz.org.iqro.iqro.services.EnrollmentService;
import kz.org.iqro.iqro.services.LessonService;
import kz.org.iqro.iqro.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;
    private final LessonService lessonService;
    private final UserService userService;

    public CourseController(CourseService courseService, EnrollmentService enrollmentService, LessonService lessonService, UserService userService) {
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
        this.lessonService = lessonService;
        this.userService = userService;
    }

    @GetMapping
    public String getAllCourses(Model model, Principal principal) {
        Optional<User> user = userService.getUserByUsername(principal.getName());
        List<Course> courses = enrollmentService.getAllEnrolledCourses(user);

        for (Course course : courses) {
            List<Long> completedLessonIds = courseService.getCompletedLessons(user.orElse(new User()).getId(), course.getId());

            int totalLessons = course.getTotalLessons();
            int completedLessons = completedLessonIds.size();
            int progressPercentage = (int) Math.round(((double) completedLessons / totalLessons) * 100);

            Optional<Lesson> firstUncompletedLesson = course.getModules().stream()
                    .flatMap(module -> module.getLessons().stream())
                    .filter(lesson -> !completedLessonIds.contains(lesson.getId()))
                    .findFirst();

            Optional<Lesson> lastLesson = course.getModules().stream()
                    .flatMap(module -> module.getLessons().stream())
                    .reduce((first, second) -> second);

            course.setLastLesson(lastLesson.orElse(null));
            course.setProgressPercentage(progressPercentage);
            course.setFirstUncompletedLesson(firstUncompletedLesson.orElse(null));
        }
        model.addAttribute("courses", courses);

        return "course/courses";
    }

    @GetMapping("/catalog")
    public String allCourses(Model model, Principal principal) {
        String username = principal.getName();
        Optional<User> user = userService.getUserByUsername(username);
        List<Course> enrolledCourses = enrollmentService.getAllEnrolledCourses(user);
        List<Course> allCourses = courseService.getAllCourses();

        for (Course course : enrolledCourses) {
            setCourseProgress(course, user.orElse(new User()).getId());
        }

        model.addAttribute("enrolledCourses", enrolledCourses);
        model.addAttribute("courses", allCourses);
        return "course/catalog";
    }

    @GetMapping("/catalog/{id}")
    public String getCourseInfo(Model model, @PathVariable int id, Principal principal) {
        List<Module> modules = courseService.getCourseById(id).getModules();
        modules.sort(Comparator.comparing(Module::getId));

        String username = principal.getName();
        Optional<User> user = userService.getUserByUsername(username);
        model.addAttribute("enrolledCourses", enrollmentService.getAllEnrolledCourses(user));
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

    @GetMapping("/{course_id}/{lesson_id}")
    public String getCourseLessons(Model model, @PathVariable int course_id, @PathVariable int lesson_id, Principal principal) {
        User user = userService.getUserByUsername(principal.getName()).orElse(new User());
        model.addAttribute("lesson", lessonService.getLessonById(lesson_id));
        model.addAttribute("completedLessons",
                courseService.getCompletedLessons(user.getId(), (long) course_id));
        List<Module> courseModules = courseService.getCourseById(course_id).getModules();
        model.addAttribute("courseModules", courseModules);
        model.addAttribute("course", courseService.getCourseById(course_id));

        Long previousLessonId = null;
        Long nextLessonId = null;
        boolean found = false;

        outerLoop:
        for (Module module : courseModules) {
            List<Lesson> lessons = module.getLessons();
            for (int i = 0; i < lessons.size(); i++) {
                if (lessons.get(i).getId().equals((long) lesson_id)) {
                    if (i > 0) {
                        previousLessonId = lessons.get(i - 1).getId();
                    }
                    if (i < lessons.size() - 1) {
                        nextLessonId = lessons.get(i + 1).getId();
                    }
                    found = true;
                    break outerLoop;
                }
            }
        }

        model.addAttribute("previousLessonId", previousLessonId);
        model.addAttribute("nextLessonId", nextLessonId);

        return "course/lessons";
    }

    @PostMapping("/toggle-complete/{course_id}/{lesson_id}")
    public String toggleLessonComplete(@PathVariable Long course_id, @PathVariable Long lesson_id, @RequestParam("completeLesson") Optional<String> completeLesson, Principal principal) {
        String username = principal.getName();
        User user = userService.getUserByUsername(username).orElse(new User());

        if (completeLesson.isPresent()) {
            courseService.markLessonComplete(user.getId(), course_id, lesson_id);
        } else {
            courseService.unmarkLessonComplete(user.getId(), course_id, lesson_id);
        }

        return "redirect:/courses/" + course_id + "/" + lesson_id;
    }

    private void setCourseProgress(Course course, Long userId) {
        List<Long> completedLessonIds = courseService.getCompletedLessons(userId, course.getId());
        int totalLessons = course.getTotalLessons();
        int completedLessons = completedLessonIds.size();
        int progressPercentage = (int) Math.round(((double) completedLessons / totalLessons) * 100);

        Optional<Lesson> firstUncompletedLesson = course.getModules().stream()
                .flatMap(module -> module.getLessons().stream())
                .filter(lesson -> !completedLessonIds.contains(lesson.getId()))
                .findFirst();

        Optional<Lesson> lastLesson = course.getModules().stream()
                .flatMap(module -> module.getLessons().stream())
                .reduce((first, second) -> second);

        course.setProgressPercentage(progressPercentage);
        course.setFirstUncompletedLesson(firstUncompletedLesson.orElse(null));
        course.setLastLesson(lastLesson.orElse(null));
    }
}

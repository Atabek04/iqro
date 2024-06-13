package kz.org.iqro.iqro.services;

import kz.org.iqro.iqro.entities.Course;
import kz.org.iqro.iqro.entities.CourseProgress;
import kz.org.iqro.iqro.entities.Lesson;
import kz.org.iqro.iqro.entities.User;
import kz.org.iqro.iqro.repositories.CourseProgressRepository;
import kz.org.iqro.iqro.repositories.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseProgressRepository courseProgressRepository;
    public CourseService(CourseRepository courseRepository, CourseProgressRepository courseProgressRepository) {
        this.courseRepository = courseRepository;
        this.courseProgressRepository = courseProgressRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(int id) {
        return courseRepository.findById(id).orElse(null);
    }

    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    public List<Long> getCompletedLessons(Long userId, Long courseId) {
        return courseProgressRepository.findCompletedLessonIdsByUserIdAndCourseId(userId, courseId);
    }

    @Transactional
    public void markLessonComplete(Long userId, Long courseId, Long lessonId) {
        CourseProgress courseProgress = new CourseProgress();
        courseProgress.setUser(new User(userId));
        courseProgress.setCourse(new Course(courseId));
        courseProgress.setLesson(new Lesson(lessonId));
        courseProgressRepository.save(courseProgress);
    }

    @Transactional
    public void unmarkLessonComplete(Long userId, Long courseId, Long lessonId) {
        courseProgressRepository.deleteByUserIdAndCourseIdAndLessonId(userId, courseId, lessonId);
    }
}

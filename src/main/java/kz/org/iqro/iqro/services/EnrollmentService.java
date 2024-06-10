package kz.org.iqro.iqro.services;

import kz.org.iqro.iqro.entities.Course;
import kz.org.iqro.iqro.entities.Enrollment;
import kz.org.iqro.iqro.entities.User;
import kz.org.iqro.iqro.repositories.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public void saveEnrollment(Enrollment enrollment) {
        enrollmentRepository.save(enrollment);
    }

    public void enrollCourse(Course course, User user) {
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setUser(user);
        saveEnrollment(enrollment);
    }

    public List<Course> getAllEnrolledCourses(Optional<User> userId) {
        List<Course> courses = new ArrayList<>();
        List<Enrollment> enrollments = enrollmentRepository.findByUserId(userId.orElseThrow().getId());
        for (Enrollment enrollment : enrollments) {
            courses.add(enrollment.getCourse());
        }
        return courses;
    }
}

package kz.org.iqro.iqro.repositories;

import kz.org.iqro.iqro.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByStatus(Short status);
}

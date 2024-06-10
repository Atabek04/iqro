package kz.org.iqro.iqro.repositories;

import kz.org.iqro.iqro.entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    List<Enrollment> findByUserId(Long userId);
}

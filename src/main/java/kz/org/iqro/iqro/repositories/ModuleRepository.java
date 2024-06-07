package kz.org.iqro.iqro.repositories;

import kz.org.iqro.iqro.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, Integer> {
    List<Module> findAllByCourseId(Long course_id);
}

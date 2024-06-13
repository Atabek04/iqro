package kz.org.iqro.iqro.repositories;

import kz.org.iqro.iqro.entities.CourseProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseProgressRepository extends JpaRepository<CourseProgress, Integer> {
    @Query("SELECT cp.lesson.id FROM CourseProgress cp WHERE cp.user.id = :userId AND cp.course.id = :courseId")
    List<Long> findCompletedLessonIdsByUserIdAndCourseId(@Param("userId") Long userId, @Param("courseId") Long courseId);

    void deleteByUserIdAndCourseIdAndLessonId(Long userId, Long courseId, Long lessonId);
}

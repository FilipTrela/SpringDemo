package pl.sda.gda25.spring.students.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.gda25.spring.students.model.Grade;

public interface GradeRepository extends JpaRepository<Grade,Long> {
}

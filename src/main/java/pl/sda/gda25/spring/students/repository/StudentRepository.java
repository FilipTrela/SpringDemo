package pl.sda.gda25.spring.students.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.gda25.spring.students.model.Student;

public interface StudentRepository extends JpaRepository<Student,Long> {


}

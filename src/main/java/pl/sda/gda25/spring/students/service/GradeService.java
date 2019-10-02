package pl.sda.gda25.spring.students.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.gda25.spring.students.model.Grade;
import pl.sda.gda25.spring.students.model.Student;
import pl.sda.gda25.spring.students.repository.GradeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GradeService {
    private final GradeRepository gradeRepository;
    private final StudentService studentService;

    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    public void add(Grade grade, Long studentId) {
        Optional<Student> optionalStudent = studentService.findById(studentId);
        if (optionalStudent.isPresent()) {
            grade.setStudent(optionalStudent.get());
        }
        gradeRepository.save(grade);
    }

    public Optional<Grade> findById(Long id) {
        return gradeRepository.findById(id);
    }

    public void deleteGrade(Grade grade) {
        gradeRepository.delete(grade);
    }
}

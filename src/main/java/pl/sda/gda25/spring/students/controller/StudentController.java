package pl.sda.gda25.spring.students.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.gda25.spring.students.model.Grade;
import pl.sda.gda25.spring.students.model.Student;
import pl.sda.gda25.spring.students.service.StudentService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping(path = "/student/")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/add")
    public String add(Model model, Student student) {
        model.addAttribute("student", student);
        return "student-add";
    }

    @PostMapping("/add")
    public String add(Student student) {
        studentService.add(student);

        return "redirect:/student/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Student> studentList = studentService.getAllStudents();

        model.addAttribute("students", studentList);

        return "student-list";

    }

    @GetMapping("/edit/{edited_student_id}")
    public String edit(Model model,
                       @PathVariable(name = "edited_student_id") Long studentId) {
        Optional<Student> studentOptional = studentService.findById(studentId);
        if (studentOptional.isPresent()) {
            model.addAttribute("student", studentOptional.get());

            return "student-add";
        }
        return "redirect:/student/list";
    }

    @GetMapping("/edit")
    public String editRP(Model model,
                         @RequestParam(name = "studentId") Long studentId) {
        Optional<Student> studentOptional = studentService.findById(studentId);
        if (studentOptional.isPresent()) {
            model.addAttribute("student", studentOptional.get());

            return "student-add";
        }
        return "redirect:/student/list";
    }

    @GetMapping("/delete")
    public String delete(Model model,
                         @RequestParam(name = "studentId") Long id) {
        Optional<Student> studentOptional = studentService.findById(id);
        studentOptional.ifPresent(studentService::deleteStudent);
        return "redirect:/student/list";

    }

    @GetMapping("/delete/{studentId}")
    public String deletePV(Model model,
                         @PathVariable(name = "studentId") Long id) {
        Optional<Student> studentOptional = studentService.findById(id);
        studentOptional.ifPresent(studentService::deleteStudent);
        return "redirect:/student/list";

    }
    @GetMapping("/{studentId}/grades")
    public String showGrades(Model model,
                             @PathVariable(name = "studentId") Long id){
        Optional<Student> optionalStudent = studentService.findById(id);
        if(optionalStudent.isPresent()){
            List<Grade> gradeList = optionalStudent.get().getGrades();
            model.addAttribute("grades",gradeList);
            return "grade-list";
        }
        return "redirect:/demo/hello";
    }
}

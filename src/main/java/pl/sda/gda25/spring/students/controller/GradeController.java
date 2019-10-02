package pl.sda.gda25.spring.students.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.sda.gda25.spring.students.model.Grade;

import pl.sda.gda25.spring.students.model.GradeSubject;
import pl.sda.gda25.spring.students.model.Student;
import pl.sda.gda25.spring.students.service.GradeService;

import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping(path = "/grade/")
public class GradeController {
    private final GradeService gradeService;

    @GetMapping("/add/{studentId}")
    public String add(Model model, Grade grade,
                      @PathVariable(name = "studentId") Long studentId) {
        model.addAttribute("grade", grade);
        model.addAttribute("studentId", studentId);
        model.addAttribute("subjects", GradeSubject.values());

        return "grade-add";
    }

    @PostMapping("/add")
    public String add(Grade grade, Long studentId) {
        gradeService.add(grade, studentId);

        return "redirect:/student/" + studentId + "/grades";
    }

    @GetMapping("/delete/{gradeID}")
    public String deletePV(Model model,
                           @PathVariable(name = "gradeID") Long id) {
        Optional<Grade> gradeOptional = gradeService.findById(id);
        if(gradeOptional.isPresent()){
            Grade grade = gradeOptional.get();
            Long studentId = grade.getStudent().getId();
            gradeService.deleteGrade(grade);
            return "redirect:/student/"+studentId+"/grades";
        }
        return "redirect:/student/list";

    }
}

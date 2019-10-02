package pl.sda.gda25.spring.students.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;

@Entity//to jest encja bazodanowa
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    private int age;

    private boolean alive;
    @Formula(value = "(SELECT AVG(g.value) FROM Grade g Where g.student_id = id)")
    private Double average;
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Cascade(value = {org.hibernate.annotations.CascadeType.REMOVE})
    private List<Grade> grades;

}

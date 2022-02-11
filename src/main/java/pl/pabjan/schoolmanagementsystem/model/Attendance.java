package pl.pabjan.schoolmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.pabjan.schoolmanagementsystem.model.User.Student;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attendance")
public class Attendance extends AbstractModel {

    @Column(name = "was_present")
    private boolean wasPresent;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;


    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}

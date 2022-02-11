package pl.pabjan.schoolmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.pabjan.schoolmanagementsystem.model.User.Student;
import pl.pabjan.schoolmanagementsystem.model.User.Teacher;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subject_group")
public class SubjectGroup extends AbstractModel {

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "school_class_id")
    private SchoolClass schoolClass;

    @ManyToMany(mappedBy = "subjectGroups")
    private Set<Student> students;

    @OneToMany(mappedBy = "subjectGroup", fetch = FetchType.LAZY)
    private List<Mark> marks;

    @OneToMany(mappedBy = "subjectGroup", fetch = FetchType.LAZY)
    private List<Attendance> attendances;
}

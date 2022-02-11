package pl.pabjan.schoolmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.pabjan.schoolmanagementsystem.model.User.Student;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "school_class")
public class SchoolClass extends AbstractModel {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "schoolClass", fetch = FetchType.LAZY)
    private List<SubjectGroup> subjectGroups;

    @OneToMany(mappedBy = "schoolClass", fetch = FetchType.LAZY)
    private List<Student> schoolClass;

}

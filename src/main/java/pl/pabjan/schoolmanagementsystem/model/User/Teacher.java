package pl.pabjan.schoolmanagementsystem.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.pabjan.schoolmanagementsystem.model.Subject;
import pl.pabjan.schoolmanagementsystem.model.SubjectGroup;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teacher")
public class Teacher extends AbstractUser {

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<SubjectGroup> subjectGroup;
}

package pl.pabjan.schoolmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subject")
public class Subject extends AbstractModel{

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    private List<Attendance> attendances;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    private List<SubjectGroup> subjectGroups;


}

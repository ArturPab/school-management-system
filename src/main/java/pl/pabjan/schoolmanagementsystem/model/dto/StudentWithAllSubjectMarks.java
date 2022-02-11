package pl.pabjan.schoolmanagementsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentWithAllSubjectMarks {

    private String name;
    private String lastname;
    private Set<SubjectWithMarksResponse> subjectsWithMarks;
}

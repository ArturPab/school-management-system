package pl.pabjan.schoolmanagementsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentWithAllSubjectMarks {

    private String name;
    private String lastname;
    private List<SubjectWithMarksResponse> subjectsWithMarks;
}

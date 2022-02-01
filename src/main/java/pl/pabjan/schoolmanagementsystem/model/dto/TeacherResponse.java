package pl.pabjan.schoolmanagementsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherResponse extends AbstractUserResponse {
    private String subjectName;
}

package pl.pabjan.schoolmanagementsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

    private String name;
    private String lastname;
    private LocalDate dateOfBirth;
    private String className;
}

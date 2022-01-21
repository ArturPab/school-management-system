package pl.pabjan.schoolmanagementsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminResponse {
    private Long id;
    private String name;
    private String lastname;
    private LocalDate dateOfBirth;
}

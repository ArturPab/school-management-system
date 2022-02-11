package pl.pabjan.schoolmanagementsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarkRequest {
    private double mark;
    private String description;
    private Long subjectGroupId;
    private Long studentId;
}

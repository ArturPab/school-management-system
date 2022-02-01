package pl.pabjan.schoolmanagementsystem.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AbstractUserResponse {
    private String name;
    private String lastname;
    private LocalDate dateOfBirth;
}

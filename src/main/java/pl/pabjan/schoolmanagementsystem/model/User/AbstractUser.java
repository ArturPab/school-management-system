package pl.pabjan.schoolmanagementsystem.model.User;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * abstract class which configures how to user is created
 * it doesn't create table in database
 */
@Data
@MappedSuperclass
public abstract class AbstractUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "role")
    private String role;

}

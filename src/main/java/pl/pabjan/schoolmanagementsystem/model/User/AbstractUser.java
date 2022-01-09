package pl.pabjan.schoolmanagementsystem.model.User;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "lastname")
    private String lastname;

    @NotBlank
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @NotBlank
    @Column(name = "role")
    private String role;


    @Override
    public boolean equals(Object obj) {
        if(this==obj) {
            return true;
        }
        if(obj==null) {
            return false;
        }
        if(getClass()!=obj.getClass()) {
            return false;
        }
        AbstractUser other = (AbstractUser) obj;
        if(getId()==null || other.getId() == null) {
            return false;
        }

        return getId().equals(other.getId());
    }
}

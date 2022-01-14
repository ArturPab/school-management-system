package pl.pabjan.schoolmanagementsystem.mapper;

import org.springframework.stereotype.Component;
import pl.pabjan.schoolmanagementsystem.model.User.AbstractUser;
import pl.pabjan.schoolmanagementsystem.model.User.Admin;
import pl.pabjan.schoolmanagementsystem.model.User.Student;
import pl.pabjan.schoolmanagementsystem.model.User.Teacher;
import pl.pabjan.schoolmanagementsystem.model.dto.UserResponse;

@Component
public class UserMapper {

    public UserResponse mapToDto(Object user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(((AbstractUser) user).getId());
        userResponse.setName(((AbstractUser) user).getName());
        userResponse.setLastname(((AbstractUser) user).getLastname());
        userResponse.setRole(((AbstractUser) user).getRole());

        return userResponse;
    }

    private boolean isClassCorrect(Object user) {
        return user.getClass() == Admin.class || user.getClass() == Teacher.class || user.getClass() == Student.class;
    }
}

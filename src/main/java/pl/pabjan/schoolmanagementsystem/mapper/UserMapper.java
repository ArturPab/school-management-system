package pl.pabjan.schoolmanagementsystem.mapper;

import org.springframework.stereotype.Component;
import pl.pabjan.schoolmanagementsystem.model.User.AbstractUser;
import pl.pabjan.schoolmanagementsystem.model.dto.UserResponse;

@Component
public class UserMapper {

    /**
     * @param user - any class that extends AbstractUser class
     * @return user response model
     */
    public UserResponse mapToDto(Object user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(((AbstractUser) user).getId());
        userResponse.setName(((AbstractUser) user).getName());
        userResponse.setLastname(((AbstractUser) user).getLastname());
        userResponse.setRole(((AbstractUser) user).getRole());

        return userResponse;
    }
}

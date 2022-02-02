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
    public UserResponse mapToDto(AbstractUser user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setLastname(user.getLastname());
        userResponse.setRole(user.getRole());

        return userResponse;
    }
}

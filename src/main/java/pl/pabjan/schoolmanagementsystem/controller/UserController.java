package pl.pabjan.schoolmanagementsystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pabjan.schoolmanagementsystem.model.dto.UserResponse;
import pl.pabjan.schoolmanagementsystem.service.UserService;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    /**
     * @return http status with list of user response (teachers, students, admins)
     */
    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return status(HttpStatus.OK).body(userService.getAllUsers());
    }
}

package pl.pabjan.schoolmanagementsystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pabjan.schoolmanagementsystem.exceptions.UserNotFoundException;
import pl.pabjan.schoolmanagementsystem.model.dto.AdminRequest;
import pl.pabjan.schoolmanagementsystem.model.dto.AdminResponse;
import pl.pabjan.schoolmanagementsystem.service.AdminService;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/all")
    public ResponseEntity<List<AdminResponse>> getAllAdmins() {
        return status(HttpStatus.OK).body(adminService.getAllAdmins());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminResponse> getAdminById(@PathVariable Long id) throws UserNotFoundException {
        return status(HttpStatus.OK).body(adminService.getAdminById(id));
    }

    @GetMapping("current")
    public ResponseEntity<AdminResponse> getCurrentAdmin() throws UserNotFoundException {
        return status(HttpStatus.OK).body(adminService.getCurrentAdmin());
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createAdmin(@RequestBody AdminRequest adminRequest) {
        adminService.createAdmin(adminRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

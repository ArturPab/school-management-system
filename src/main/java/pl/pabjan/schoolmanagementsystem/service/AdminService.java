package pl.pabjan.schoolmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pabjan.schoolmanagementsystem.exceptions.UserNotFoundException;
import pl.pabjan.schoolmanagementsystem.mapper.AdminMapper;
import pl.pabjan.schoolmanagementsystem.model.dto.AdminRequest;
import pl.pabjan.schoolmanagementsystem.model.dto.AdminResponse;
import pl.pabjan.schoolmanagementsystem.repository.AdminRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminService {

    private final PasswordEncoder passwordEncoder;
    private final AdminRepo adminRepo;
    private final AdminMapper adminMapper;

    /**
     *
     * @return all admins mapped to dto
     */
    public List<AdminResponse> getAllAdmins() {
        return adminRepo.findAll().stream().map(adminMapper::mapToDto).collect(Collectors.toList());
    }

    /**
     *
     * @param id of admin
     * @return admin found in db by id mapped to dto
     * @throws UserNotFoundException when admin was not found
     */
    public AdminResponse getAdminById(Long id) throws UserNotFoundException {
        return adminMapper.mapToDto(adminRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Admin was not found!")));
    }

    /**
     *
     * @param adminRequest's body
     * adminRequest is mapped to Admin model and then created in db
     */
    public void createAdmin(AdminRequest adminRequest) {
        adminRequest.setPassword(passwordEncoder.encode(adminRequest.getPassword()));
        adminRepo.save(adminMapper.map(adminRequest));
    }


    /**
     *
     * @return current Admin
     * @throws UserNotFoundException when admin was not found
     */
    public AdminResponse getCurrentAdmin() throws UserNotFoundException {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        return adminMapper.mapToDto(adminRepo.findById(Long.parseLong(id)).orElseThrow(() -> new UserNotFoundException("Admin not found!")));
    }
}

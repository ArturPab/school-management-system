package pl.pabjan.schoolmanagementsystem.mapper;

import org.springframework.stereotype.Component;
import pl.pabjan.schoolmanagementsystem.model.User.Admin;
import pl.pabjan.schoolmanagementsystem.model.dto.AdminRequest;
import pl.pabjan.schoolmanagementsystem.model.dto.AdminResponse;

@Component
public class AdminMapper {

    /**
     *
     * @param admin model for response
     * @return admin response model
     */
    public AdminResponse mapToDto(Admin admin) {
        AdminResponse adminResponse = new AdminResponse();
        adminResponse.setId(admin.getId());
        adminResponse.setName(admin.getName());
        adminResponse.setLastname(admin.getLastname());
        adminResponse.setDateOfBirth(admin.getDateOfBirth());

        return adminResponse;
    }

    /**
     *
     * @param adminRequest model
     * @return mapped admin model request for admin model
     */
    public Admin map(AdminRequest adminRequest) {
        Admin admin = new Admin();
        admin.setName(adminRequest.getName());
        admin.setLastname(adminRequest.getLastname());
        admin.setPassword(adminRequest.getPassword());
        admin.setDateOfBirth(adminRequest.getDateOfBirth());
        admin.setRole("ROLE_ADMIN");

        return admin;
    }
}

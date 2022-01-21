package pl.pabjan.schoolmanagementsystem.service.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.pabjan.schoolmanagementsystem.model.User.Admin;
import pl.pabjan.schoolmanagementsystem.model.User.Student;
import pl.pabjan.schoolmanagementsystem.model.User.Teacher;
import pl.pabjan.schoolmanagementsystem.repository.AdminRepo;
import pl.pabjan.schoolmanagementsystem.repository.StudentRepo;
import pl.pabjan.schoolmanagementsystem.repository.TeacherRepo;

import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AdminRepo adminRepo;
    private final StudentRepo studentRepo;
    private final TeacherRepo teacherRepo;

    /**
     *
     * @param idWithRole - id of user with role
     * @return User depends on role and id
     * @throws UsernameNotFoundException when user doesn't exist in db
     */
    @Override
    public UserDetails loadUserByUsername(String idWithRole) throws UsernameNotFoundException {
        int indexOfRoleBegin = idWithRole.indexOf('+');
        Long id = Long.parseLong(idWithRole.substring(0, indexOfRoleBegin));
        String role = idWithRole.substring(indexOfRoleBegin + 1);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));

        return getUser(id, authorities);
    }

    private User getUser(Long id, Collection<SimpleGrantedAuthority> authorities) {
        if (isAdmin(authorities)) {
            Admin admin = adminRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Not found admin with id " + id));
            return new User(admin.getId().toString(), admin.getPassword(), true, true, true, true, authorities);
        } else if (isStudent(authorities)) {
            Student student = studentRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Not found student with id " + id));
            return new User(student.getId().toString(), student.getPassword(), true, true, true, true, authorities);
        } else {
            Teacher teacher = teacherRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Not found teacher with id " + id));
            return new User(teacher.getId().toString(), teacher.getPassword(), true, true, true, true, authorities);
        }
    }

    private boolean isStudent(Collection<SimpleGrantedAuthority> roles) {
        return roles.stream().findFirst().orElseThrow(() -> new UsernameNotFoundException("Not found role")).getAuthority().equals("ROLE_STUDENT");
    }

    private boolean isAdmin(Collection<SimpleGrantedAuthority> roles) {
        return roles.stream().findFirst().orElseThrow(() -> new UsernameNotFoundException("Not found role")).getAuthority().equals("ROLE_ADMIN");
    }
}

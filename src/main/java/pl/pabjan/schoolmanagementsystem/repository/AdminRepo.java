package pl.pabjan.schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pabjan.schoolmanagementsystem.model.User.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
}

package pl.pabjan.schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pabjan.schoolmanagementsystem.model.User.Teacher;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {
}

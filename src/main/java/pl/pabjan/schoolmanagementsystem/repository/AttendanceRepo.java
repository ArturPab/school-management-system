package pl.pabjan.schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pabjan.schoolmanagementsystem.model.Attendance;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Long> {
}

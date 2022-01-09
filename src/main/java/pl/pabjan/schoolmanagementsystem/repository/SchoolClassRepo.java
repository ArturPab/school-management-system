package pl.pabjan.schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pabjan.schoolmanagementsystem.model.SchoolClass;

@Repository
public interface SchoolClassRepo extends JpaRepository<SchoolClass, Long> {
}

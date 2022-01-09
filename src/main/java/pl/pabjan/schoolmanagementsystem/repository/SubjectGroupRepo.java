package pl.pabjan.schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pabjan.schoolmanagementsystem.model.SubjectGroup;

@Repository
public interface SubjectGroupRepo extends JpaRepository<SubjectGroup, Long> {
}

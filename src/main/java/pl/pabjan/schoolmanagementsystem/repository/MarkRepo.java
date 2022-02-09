package pl.pabjan.schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pabjan.schoolmanagementsystem.model.Mark;

import java.util.List;

@Repository
public interface MarkRepo extends JpaRepository<Mark, Long> {
    List<Mark> findBySubjectId(Long id);

    List<Mark> findByStudentId(Long id);
}

package pl.pabjan.schoolmanagementsystem.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pabjan.schoolmanagementsystem.model.Mark;
import pl.pabjan.schoolmanagementsystem.model.dto.MarkRequest;
import pl.pabjan.schoolmanagementsystem.model.dto.MarkResponse;
import pl.pabjan.schoolmanagementsystem.repository.StudentRepo;
import pl.pabjan.schoolmanagementsystem.repository.SubjectRepo;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
public class MarkMapper {
    private final StudentRepo studentRepo;
    private final SubjectRepo subjectRepo;

    public Mark map(MarkRequest markRequest) {
        Mark mark = new Mark();
        mark.setMark(markRequest.getMark());
        mark.setDescription(markRequest.getDescription());
        mark.setInserted(LocalDate.now());
        mark.setStudent(studentRepo.getById(markRequest.getStudentId()));
        mark.setSubject(subjectRepo.getById(markRequest.getSubjectId()));

        return mark;
    }

    public MarkResponse mapToDto(Mark mark) {
        MarkResponse markResponse = new MarkResponse();
        markResponse.setMark(mark.getMark());
        markResponse.setInserted(mark.getInserted());
        markResponse.setDescription(mark.getDescription());
        markResponse.setSubjectName(mark.getSubject().getName());

        return markResponse;
    }
}

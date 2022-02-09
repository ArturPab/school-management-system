package pl.pabjan.schoolmanagementsystem.mapper;

import org.springframework.stereotype.Component;
import pl.pabjan.schoolmanagementsystem.model.Mark;
import pl.pabjan.schoolmanagementsystem.model.dto.MarkRequest;
import pl.pabjan.schoolmanagementsystem.model.dto.MarkResponse;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Component
public class MarkMapper {
    public Mark map(MarkRequest markRequest) {
        Mark mark = new Mark();
        mark.setMark(markRequest.getMark());
        mark.setDescription(markRequest.getDescription());
        mark.setInserted(LocalDate.now());
        mark.setStudentId(markRequest.getStudentId());
        mark.setSubjectId(markRequest.getSubjectId());

        return mark;
    }

    public MarkResponse mapToDto(Mark mark, String subjectName) {
        MarkResponse markResponse = new MarkResponse();
        markResponse.setMark(mark.getMark());
        markResponse.setInserted(mark.getInserted());
        markResponse.setDescription(mark.getDescription());
        markResponse.setSubjectName(subjectName);

        return markResponse;
    }
}

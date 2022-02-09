package pl.pabjan.schoolmanagementsystem.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pabjan.schoolmanagementsystem.model.Mark;
import pl.pabjan.schoolmanagementsystem.model.Subject;
import pl.pabjan.schoolmanagementsystem.model.dto.MarkResponse;
import pl.pabjan.schoolmanagementsystem.model.dto.SubjectWithMarksResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SubjectMapper {
    private final MarkMapper markMapper;

    public SubjectWithMarksResponse mapToDto(Subject subject, List<Mark> newMarks) {
        SubjectWithMarksResponse subjectWithMarksResponse = new SubjectWithMarksResponse();
        subjectWithMarksResponse.setSubjectName(subject.getName());
        List<Mark> marks = newMarks.stream().filter(mark -> mark.getSubjectId().equals(subject.getId())).collect(Collectors.toList());
        List<MarkResponse> markResponses = marks.stream().map(mark -> markMapper.mapToDto(mark, subject.getName())).collect(Collectors.toList());
        subjectWithMarksResponse.setMarks(markResponses);

        return subjectWithMarksResponse;
    }
}

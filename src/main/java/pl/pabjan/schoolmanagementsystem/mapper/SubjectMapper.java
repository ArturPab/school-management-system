package pl.pabjan.schoolmanagementsystem.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pabjan.schoolmanagementsystem.model.Mark;
import pl.pabjan.schoolmanagementsystem.model.Subject;
import pl.pabjan.schoolmanagementsystem.model.SubjectGroup;
import pl.pabjan.schoolmanagementsystem.model.dto.MarkResponse;
import pl.pabjan.schoolmanagementsystem.model.dto.SubjectWithMarksResponse;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SubjectMapper {
    private final MarkMapper markMapper;

    public SubjectWithMarksResponse mapToDto(SubjectGroup subjectGroup, List<Mark> marks) {
        SubjectWithMarksResponse subjectWithMarksResponse = new SubjectWithMarksResponse();
        subjectWithMarksResponse.setSubjectName(subjectGroup.getSubject().getName());
        Set<Mark> newMarks = marks.stream().filter(mark -> mark.getSubject().getId().equals(subjectGroup.getSubject().getId())).collect(Collectors.toSet());
        subjectWithMarksResponse.setMarks(newMarks.stream().map(markMapper::mapToDto).collect(Collectors.toSet()));

        return subjectWithMarksResponse;
    }
}

package pl.pabjan.schoolmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pabjan.schoolmanagementsystem.exceptions.InvalidMarkValueException;
import pl.pabjan.schoolmanagementsystem.mapper.MarkMapper;
import pl.pabjan.schoolmanagementsystem.model.Mark;
import pl.pabjan.schoolmanagementsystem.model.MarkValue;
import pl.pabjan.schoolmanagementsystem.model.dto.MarkRequest;
import pl.pabjan.schoolmanagementsystem.repository.MarkRepo;
import pl.pabjan.schoolmanagementsystem.repository.StudentRepo;
import pl.pabjan.schoolmanagementsystem.repository.SubjectRepo;

@Service
@AllArgsConstructor
public class MarkService {
    private final MarkRepo markRepo;
    private final MarkMapper markMapper;
    private final StudentRepo studentRepo;
    private final SubjectRepo subjectRepo;

    public void createMark(MarkRequest markRequest) throws InvalidMarkValueException {
        if (!markRequestIsCorrect(markRequest)) {
            throw new InvalidMarkValueException("Invalid mark's details!");
        }

        Mark mark = markMapper.map(markRequest);
        markRepo.save(mark);
    }

    private boolean markRequestIsCorrect(MarkRequest markRequest) {
        return studentRepo.findById(markRequest.getStudentId()).isPresent() && subjectRepo.findById(markRequest.getSubjectId()).isPresent() && markIsCorrect(markRequest.getMark());
    }

    private boolean markIsCorrect(double mark) {
        for (MarkValue markValue : MarkValue.values()) {
            if (mark == markValue.getValue())
                return true;
        }
        return false;
    }
}

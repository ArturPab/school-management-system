package pl.pabjan.schoolmanagementsystem.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pabjan.schoolmanagementsystem.model.SchoolClass;
import pl.pabjan.schoolmanagementsystem.model.User.Student;
import pl.pabjan.schoolmanagementsystem.model.dto.*;
import pl.pabjan.schoolmanagementsystem.repository.SchoolClassRepo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class StudentMapper {

    private final MarkMapper markMapper;
    private final SubjectMapper subjectMapper;
    private final SchoolClassRepo schoolClassRepo;

    public Student map(StudentRequest studentRequest) {
        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setLastname(studentRequest.getLastname());
        student.setPassword(studentRequest.getPassword());
        student.setDateOfBirth(studentRequest.getDateOfBirth());
        student.setRole("ROLE_STUDENT");
        SchoolClass schoolClass = schoolClassRepo.getById(studentRequest.getClassId());
        student.setSchoolClass(schoolClass);

        return student;
    }


    public StudentResponse mapToDto(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setName(student.getName());
        studentResponse.setLastname(student.getLastname());
        studentResponse.setDateOfBirth(student.getDateOfBirth());
        studentResponse.setClassName(student.getSchoolClass().getName());

        return studentResponse;
    }

    public StudentWithMark mapToStudentWithMarks(Student student) {
        StudentWithMark studentWithMark = new StudentWithMark();
        studentWithMark.setName(student.getName());
        studentWithMark.setLastname(student.getLastname());
        List<MarkResponse> markResponses = student.getMarks().stream().map(markMapper::mapToDto).collect(Collectors.toList());
        studentWithMark.setMarks(markResponses);

        return studentWithMark;
    }

    public StudentWithAllSubjectMarks mapToStudentWithAllSubjectMarks(Student student) {
        StudentWithAllSubjectMarks studentDto = new StudentWithAllSubjectMarks();
        studentDto.setName(student.getName());
        studentDto.setLastname(student.getLastname());
        Set<SubjectWithMarksResponse> subjectWithMarksResponses = student.getSubjectGroups().stream().map(subjectGroup -> subjectMapper.mapToDto(subjectGroup, student.getMarks())).collect(Collectors.toSet());
        studentDto.setSubjectsWithMarks(subjectWithMarksResponses);

        return studentDto;
    }
}

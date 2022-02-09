package pl.pabjan.schoolmanagementsystem.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pabjan.schoolmanagementsystem.model.Mark;
import pl.pabjan.schoolmanagementsystem.model.SchoolClass;
import pl.pabjan.schoolmanagementsystem.model.Subject;
import pl.pabjan.schoolmanagementsystem.model.User.Student;
import pl.pabjan.schoolmanagementsystem.model.dto.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class StudentMapper {

    private final MarkMapper markMapper;
    private final SubjectMapper subjectMapper;

    public StudentResponse mapToDto(Student student, List<SchoolClass> classes) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setName(student.getName());
        studentResponse.setLastname(student.getLastname());
        studentResponse.setDateOfBirth(student.getDateOfBirth());
        Optional<SchoolClass> schoolClass = classes.stream().filter(currentSchoolClass -> currentSchoolClass.getId().equals(student.getClassId())).findFirst();
        if (schoolClass.isPresent()) {
            String className = schoolClass.get().getName();
            studentResponse.setClassName(className);
        } else {
            studentResponse.setClassName("Empty");
        }

        return studentResponse;
    }

    public StudentResponse mapToDto(Student student, String className) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setName(student.getName());
        studentResponse.setLastname(student.getLastname());
        studentResponse.setDateOfBirth(student.getDateOfBirth());
        studentResponse.setClassName(className);

        return studentResponse;
    }

    public Student map(StudentRequest studentRequest) {
        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setLastname(studentRequest.getLastname());
        student.setPassword(studentRequest.getPassword());
        student.setDateOfBirth(studentRequest.getDateOfBirth());
        student.setRole("ROLE_STUDENT");
        student.setClassId(studentRequest.getClassId());

        return student;
    }

    public StudentWithMark mapToDto(Student student, String subjectName, List<Mark> marks) {
        List<Mark> newMarks = marks.stream().filter(mark -> mark.getStudentId().equals(student.getId())).collect(Collectors.toList());
        List<MarkResponse> markResponses = newMarks.stream().map(mark -> markMapper.mapToDto(mark, subjectName)).collect(Collectors.toList());
        StudentWithMark studentWithMark = new StudentWithMark();
        studentWithMark.setName(student.getName());
        studentWithMark.setLastname(student.getLastname());
        studentWithMark.setMarks(markResponses);

        return studentWithMark;
    }

    public StudentWithAllSubjectMarks mapToDto(Student student, List<Mark> marks, List<Subject> subjects) {
        StudentWithAllSubjectMarks studentWithAllSubjectMarks = new StudentWithAllSubjectMarks();
        studentWithAllSubjectMarks.setName(student.getName());
        studentWithAllSubjectMarks.setLastname(student.getLastname());
        List<Mark> newMarks = marks.stream().filter(mark -> mark.getStudentId().equals(student.getId())).collect(Collectors.toList());
        studentWithAllSubjectMarks.setSubjectsWithMarks(subjects.stream().map(subject -> subjectMapper.mapToDto(subject, newMarks)).collect(Collectors.toList()));

        return studentWithAllSubjectMarks;
    }
}

package pl.pabjan.schoolmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.pabjan.schoolmanagementsystem.mapper.StudentMapper;
import pl.pabjan.schoolmanagementsystem.model.SchoolClass;
import pl.pabjan.schoolmanagementsystem.model.SubjectGroup;
import pl.pabjan.schoolmanagementsystem.model.User.Student;
import pl.pabjan.schoolmanagementsystem.model.dto.StudentRequest;
import pl.pabjan.schoolmanagementsystem.model.dto.StudentResponse;
import pl.pabjan.schoolmanagementsystem.model.dto.StudentWithAllSubjectMarks;
import pl.pabjan.schoolmanagementsystem.model.dto.StudentWithMark;
import pl.pabjan.schoolmanagementsystem.repository.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;
    private final SchoolClassRepo classRepo;
    private final StudentMapper studentMapper;
    private final SubjectRepo subjectRepo;
    private final MarkRepo markRepo;
    private final SubjectGroupRepo subjectGroupRepo;

    public List<StudentResponse> getAllStudents() {
        List<Student> students = studentRepo.findAll();

        return students.stream().map(studentMapper::mapToDto).collect(Collectors.toList());
    }

    public StudentResponse getStudentById(Long id) {
        Student student = studentRepo.getById(id);

        return studentMapper.mapToDto(student);
    }

    public StudentResponse getCurrentStudent() {
        Long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        Student student = studentRepo.getById(id);

        return studentMapper.mapToDto(student);
    }

    public List<StudentResponse> getStudentsByClassId(Long id) {
        SchoolClass schoolClass = classRepo.getById(id);
        List<Student> students = studentRepo.findBySchoolClass(schoolClass);

        return students.stream().map(studentMapper::mapToDto).collect(Collectors.toList());
    }

    public void createStudent(StudentRequest studentRequest) {
        Student student = studentMapper.map(studentRequest);
        studentRepo.save(student);
    }

    public List<StudentWithMark> getStudentsWithMarksBySubjectGroupId(Long id) {
        SubjectGroup subjectGroup = subjectGroupRepo.getById(id);
        Set<Student> students = subjectGroup.getStudents();

        return students.stream().map(studentMapper::mapToStudentWithMarks).collect(Collectors.toList());
    }

    public StudentWithAllSubjectMarks getCurrentStudentWithMarks() {
        Long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        Student student = studentRepo.getById(id);

        return studentMapper.mapToStudentWithAllSubjectMarks(student);
    }
}

package pl.pabjan.schoolmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.pabjan.schoolmanagementsystem.mapper.StudentMapper;
import pl.pabjan.schoolmanagementsystem.model.Mark;
import pl.pabjan.schoolmanagementsystem.model.SchoolClass;
import pl.pabjan.schoolmanagementsystem.model.Subject;
import pl.pabjan.schoolmanagementsystem.model.User.Student;
import pl.pabjan.schoolmanagementsystem.model.dto.StudentRequest;
import pl.pabjan.schoolmanagementsystem.model.dto.StudentResponse;
import pl.pabjan.schoolmanagementsystem.model.dto.StudentWithAllSubjectMarks;
import pl.pabjan.schoolmanagementsystem.model.dto.StudentWithMark;
import pl.pabjan.schoolmanagementsystem.repository.MarkRepo;
import pl.pabjan.schoolmanagementsystem.repository.SchoolClassRepo;
import pl.pabjan.schoolmanagementsystem.repository.StudentRepo;
import pl.pabjan.schoolmanagementsystem.repository.SubjectRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;
    private final SchoolClassRepo classRepo;
    private final StudentMapper studentMapper;
    private final SubjectRepo subjectRepo;
    private final MarkRepo markRepo;

    public List<StudentResponse> getAllStudents() {
        List<Student> students = studentRepo.findAll();
        List<SchoolClass> classes = classRepo.findAll();

        return students.stream().map(student -> studentMapper.mapToDto(student, classes)).collect(Collectors.toList());
    }

    public StudentResponse getStudentById(Long id) {
        List<SchoolClass> classes = classRepo.findAll();
        Student student = studentRepo.getById(id);

        return studentMapper.mapToDto(student, classes);
    }

    public StudentResponse getCurrentStudent() {
        Long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        List<SchoolClass> classes = classRepo.findAll();
        Student student = studentRepo.getById(id);

        return studentMapper.mapToDto(student, classes);
    }

    public List<StudentResponse> getStudentsByClassId(Long id) {
        SchoolClass schoolClass = classRepo.getById(id);
        List<Student> students = studentRepo.findByClassId(id);

        return students.stream().map(student -> studentMapper.mapToDto(student, schoolClass.getName())).collect(Collectors.toList());
    }

    public void createStudent(StudentRequest studentRequest) {
        Student student = studentMapper.map(studentRequest);
        studentRepo.save(student);
    }

    public List<StudentWithMark> getStudentsWithMarksBySubjectId(Long id) {
        List<Student> students = studentRepo.findAll();
        Subject subject = subjectRepo.getById(id);
        List<Mark> marks = markRepo.findBySubjectId(id);

        return students.stream().map(student -> studentMapper.mapToDto(student, subject.getName(), marks)).collect(Collectors.toList());
    }

    public StudentWithAllSubjectMarks getCurrentStudentWithMarks() {
        Long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Mark> marks = markRepo.findByStudentId(id);
        List<Subject> subjects = subjectRepo.findAll();
        Student student = studentRepo.getById(id);

        return studentMapper.mapToDto(student, marks, subjects);
    }
}

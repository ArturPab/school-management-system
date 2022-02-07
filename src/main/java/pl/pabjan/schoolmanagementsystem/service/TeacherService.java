package pl.pabjan.schoolmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pabjan.schoolmanagementsystem.mapper.TeacherMapper;
import pl.pabjan.schoolmanagementsystem.model.Subject;
import pl.pabjan.schoolmanagementsystem.model.User.Teacher;
import pl.pabjan.schoolmanagementsystem.model.dto.TeacherRequest;
import pl.pabjan.schoolmanagementsystem.model.dto.TeacherResponse;
import pl.pabjan.schoolmanagementsystem.repository.SubjectRepo;
import pl.pabjan.schoolmanagementsystem.repository.TeacherRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherService {
    private final TeacherRepo teacherRepo;
    private final SubjectRepo subjectRepo;
    private final TeacherMapper teacherMapper;
    private final PasswordEncoder passwordEncoder;

    public List<TeacherResponse> getAllTeachers() {
        List<Subject> subjects = subjectRepo.findAll();
        List<Teacher> teachers = teacherRepo.findAll();

        return teachers.stream().map(teacher -> teacherMapper.mapToDto(teacher, subjects)).collect(Collectors.toList());
    }

    public TeacherResponse getTeacherById(Long id) {
        List<Subject> subjects = subjectRepo.findAll();
        Teacher teacher = teacherRepo.getById(id);

        return teacherMapper.mapToDto(teacher, subjects);
    }

    public TeacherResponse getCurrentTeacher() {
        Long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Subject> subjects = subjectRepo.findAll();
        Teacher teacher = teacherRepo.getById(id);

        return teacherMapper.mapToDto(teacher, subjects);
    }

    public List<TeacherResponse> getTeachersBySubjectId(Long id) {
        Subject subject = subjectRepo.getById(id);
        List<Teacher> teachers = teacherRepo.findBySubjectId(id);

        return teachers.stream().map(teacher -> teacherMapper.mapToDto(teacher, subject.getName())).collect(Collectors.toList());
    }

    public void createTeacher(TeacherRequest teacherRequest) {
        teacherRequest.setPassword(passwordEncoder.encode(teacherRequest.getPassword()));
        Teacher teacher = teacherMapper.map(teacherRequest);
        teacherRepo.save(teacher);
    }
}

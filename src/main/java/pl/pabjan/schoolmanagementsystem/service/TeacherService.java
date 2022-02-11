package pl.pabjan.schoolmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pabjan.schoolmanagementsystem.mapper.TeacherMapper;
import pl.pabjan.schoolmanagementsystem.model.Subject;
import pl.pabjan.schoolmanagementsystem.model.SubjectGroup;
import pl.pabjan.schoolmanagementsystem.model.User.Teacher;
import pl.pabjan.schoolmanagementsystem.model.dto.TeacherRequest;
import pl.pabjan.schoolmanagementsystem.model.dto.TeacherResponse;
import pl.pabjan.schoolmanagementsystem.repository.SubjectGroupRepo;
import pl.pabjan.schoolmanagementsystem.repository.SubjectRepo;
import pl.pabjan.schoolmanagementsystem.repository.TeacherRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherService {
    private final TeacherRepo teacherRepo;
    private final TeacherMapper teacherMapper;
    private final PasswordEncoder passwordEncoder;
    private SubjectGroupRepo subjectGroupRepo;

    public List<TeacherResponse> getAllTeachers() {
        List<Teacher> teachers = teacherRepo.findAll();

        return teachers.stream().map(teacherMapper::mapToDto).collect(Collectors.toList());
    }

    public TeacherResponse getTeacherById(Long id) {
        Teacher teacher = teacherRepo.getById(id);

        return teacherMapper.mapToDto(teacher);
    }

    public TeacherResponse getCurrentTeacher() {
        Long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        Teacher teacher = teacherRepo.getById(id);

        return teacherMapper.mapToDto(teacher);
    }

    public TeacherResponse getTeacherBySubjectId(Long id) {
        SubjectGroup subjectGroup = subjectGroupRepo.getById(id);
        Teacher teacher = teacherRepo.findBySubjectGroup(subjectGroup);

        return teacherMapper.mapToDto(teacher);
    }

    public void createTeacher(TeacherRequest teacherRequest) {
        teacherRequest.setPassword(passwordEncoder.encode(teacherRequest.getPassword()));
        Teacher teacher = teacherMapper.map(teacherRequest);
        teacherRepo.save(teacher);
    }
}

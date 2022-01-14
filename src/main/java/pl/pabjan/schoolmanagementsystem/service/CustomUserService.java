package pl.pabjan.schoolmanagementsystem.service;

import liquibase.repackaged.org.apache.commons.collections4.ListUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pabjan.schoolmanagementsystem.mapper.UserMapper;
import pl.pabjan.schoolmanagementsystem.model.User.Admin;
import pl.pabjan.schoolmanagementsystem.model.User.Student;
import pl.pabjan.schoolmanagementsystem.model.User.Teacher;
import pl.pabjan.schoolmanagementsystem.model.dto.UserResponse;
import pl.pabjan.schoolmanagementsystem.repository.AdminRepo;
import pl.pabjan.schoolmanagementsystem.repository.StudentRepo;
import pl.pabjan.schoolmanagementsystem.repository.TeacherRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserService {

    private final AdminRepo adminRepo;
    private final TeacherRepo teacherRepo;
    private final StudentRepo studentRepo;
    private final UserMapper userMapper;

    public List<UserResponse> getAllUsers() {
        List<Admin> admins = adminRepo.findAll();
        List<Teacher> teachers = teacherRepo.findAll();
        List<Student> students = studentRepo.findAll();
        List<UserResponse> adminResponseList = admins.stream().map(userMapper::mapToDto).collect(Collectors.toList());
        List<UserResponse> teacherResponseList = teachers.stream().map(userMapper::mapToDto).collect(Collectors.toList());
        List<UserResponse> studentResponseList = students.stream().map(userMapper::mapToDto).collect(Collectors.toList());


        return ListUtils.union(ListUtils.union(adminResponseList, teacherResponseList), studentResponseList);
    }
}

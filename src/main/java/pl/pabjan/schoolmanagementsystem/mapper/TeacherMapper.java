package pl.pabjan.schoolmanagementsystem.mapper;

import org.springframework.stereotype.Component;
import pl.pabjan.schoolmanagementsystem.model.User.Teacher;
import pl.pabjan.schoolmanagementsystem.model.dto.TeacherRequest;
import pl.pabjan.schoolmanagementsystem.model.dto.TeacherResponse;

import java.util.stream.Collectors;

@Component
public class TeacherMapper {

    public Teacher map(TeacherRequest teacherRequest) {
        Teacher teacher = new Teacher();
        teacher.setPassword(teacherRequest.getPassword());
        teacher.setName(teacherRequest.getName());
        teacher.setLastname(teacherRequest.getLastname());
        teacher.setDateOfBirth(teacherRequest.getDateOfBirth());
        teacher.setRole("ROLE_TEACHER");
        teacher.setSubjectGroup(null);

        return teacher;
    }

    public TeacherResponse mapToDto(Teacher teacher) {
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setName(teacher.getName());
        teacherResponse.setLastname(teacher.getLastname());
        teacherResponse.setDateOfBirth(teacher.getDateOfBirth());
        teacherResponse.setSubjectName(teacher.getSubjectGroup().stream().map(subjectGroup -> subjectGroup.getSubject().getName()).collect(Collectors.toSet()));

        return teacherResponse;
    }
}

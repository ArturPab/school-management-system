package pl.pabjan.schoolmanagementsystem.mapper;

import org.springframework.stereotype.Component;
import pl.pabjan.schoolmanagementsystem.model.Subject;
import pl.pabjan.schoolmanagementsystem.model.User.Teacher;
import pl.pabjan.schoolmanagementsystem.model.dto.TeacherRequest;
import pl.pabjan.schoolmanagementsystem.model.dto.TeacherResponse;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class TeacherMapper {

    public TeacherResponse mapToDto(Teacher teacher, List<Subject> subjects) {
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setName(teacher.getName());
        teacherResponse.setLastname(teacher.getLastname());
        teacherResponse.setDateOfBirth(teacher.getDateOfBirth());
        Optional<Subject> currentSubject = subjects.stream().filter(subject -> Objects.equals(subject.getId(), teacher.getSubjectId())).findFirst();
        if (currentSubject.isPresent())
            teacherResponse.setSubjectName(currentSubject.get().getName());
        else
            teacherResponse.setSubjectName("Empty");

        return teacherResponse;
    }

    public TeacherResponse mapToDto(Teacher teacher, String subjectName) {
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setName(teacher.getName());
        teacherResponse.setLastname(teacher.getLastname());
        teacherResponse.setDateOfBirth(teacher.getDateOfBirth());
        teacherResponse.setSubjectName(subjectName);

        return teacherResponse;
    }

    public Teacher map(TeacherRequest teacherRequest) {
        Teacher teacher = new Teacher();
        teacher.setPassword(teacherRequest.getPassword());
        teacher.setName(teacherRequest.getName());
        teacher.setLastname(teacherRequest.getLastname());
        teacher.setDateOfBirth(teacherRequest.getDateOfBirth());
        teacher.setRole("ROLE_TEACHER");
        teacher.setSubjectId(teacherRequest.getSubjectId());

        return teacher;
    }
}

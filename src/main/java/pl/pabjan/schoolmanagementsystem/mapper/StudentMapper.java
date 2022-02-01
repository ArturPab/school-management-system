package pl.pabjan.schoolmanagementsystem.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pabjan.schoolmanagementsystem.model.SchoolClass;
import pl.pabjan.schoolmanagementsystem.model.User.Student;
import pl.pabjan.schoolmanagementsystem.model.dto.StudentRequest;
import pl.pabjan.schoolmanagementsystem.model.dto.StudentResponse;
import pl.pabjan.schoolmanagementsystem.repository.SchoolClassRepo;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class StudentMapper {

    public StudentResponse mapToDto(Student student, List<SchoolClass> classes) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setName(student.getName());
        studentResponse.setLastname(student.getLastname());
        studentResponse.setDateOfBirth(student.getDateOfBirth());
        Optional<SchoolClass> schoolClass = classes.stream().filter(currentSchoolClass -> currentSchoolClass.getId().equals(student.getClassId())).findFirst();
        if(schoolClass.isPresent()) {
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
}

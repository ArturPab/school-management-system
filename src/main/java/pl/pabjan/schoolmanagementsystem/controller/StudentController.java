package pl.pabjan.schoolmanagementsystem.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.pabjan.schoolmanagementsystem.model.dto.StudentRequest;
import pl.pabjan.schoolmanagementsystem.model.dto.StudentResponse;
import pl.pabjan.schoolmanagementsystem.model.dto.StudentWithAllSubjectMarks;
import pl.pabjan.schoolmanagementsystem.model.dto.StudentWithMark;
import pl.pabjan.schoolmanagementsystem.service.StudentService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/all")
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById(id));
    }

    @GetMapping("/current")
    public ResponseEntity<StudentResponse> getCurrentStudent() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getCurrentStudent());
    }

    @GetMapping("/by-class-id/{id}")
    public ResponseEntity<List<StudentResponse>> getStudentsByClassId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentsByClassId(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createStudent(@RequestBody StudentRequest studentRequest) {
        studentRequest.setPassword(passwordEncoder.encode(studentRequest.getPassword()));
        studentService.createStudent(studentRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/marks/by-subject-id/{id}")
    public ResponseEntity<List<StudentWithMark>> getStudentsWithMarksBySubjectId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentsWithMarksBySubjectId(id));
    }

    @GetMapping("/marks/by-current-student")
    public ResponseEntity<StudentWithAllSubjectMarks> getCurrentStudentWithMarks() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getCurrentStudentWithMarks());
    }
}

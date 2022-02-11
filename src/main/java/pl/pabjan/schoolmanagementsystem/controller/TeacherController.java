package pl.pabjan.schoolmanagementsystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pabjan.schoolmanagementsystem.model.dto.TeacherRequest;
import pl.pabjan.schoolmanagementsystem.model.dto.TeacherResponse;
import pl.pabjan.schoolmanagementsystem.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/all")
    public ResponseEntity<List<TeacherResponse>> getAllTeachers() {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.getAllTeachers());
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<TeacherResponse> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.getTeacherById(id));
    }

    @GetMapping("/current")
    public ResponseEntity<TeacherResponse> getCurrentTeacher() {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.getCurrentTeacher());
    }

    @GetMapping("/by-subject-id/{id}")
    public ResponseEntity<TeacherResponse> getTeachersBySubjectId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.getTeacherBySubjectId(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createTeacher(@RequestBody TeacherRequest teacherRequest) {
        teacherService.createTeacher(teacherRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

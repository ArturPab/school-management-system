package pl.pabjan.schoolmanagementsystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pabjan.schoolmanagementsystem.exceptions.InvalidMarkValueException;
import pl.pabjan.schoolmanagementsystem.model.dto.MarkRequest;
import pl.pabjan.schoolmanagementsystem.service.MarkService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/mark")
public class MarkController {

    private final MarkService markService;

    @PostMapping("/create")
    public ResponseEntity<Void> createMark(@RequestBody MarkRequest markRequest) throws InvalidMarkValueException {
        markService.createMark(markRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

package ru.benefic.geekhome.task.students.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.benefic.geekhome.task.students.model.dto.StudentDto;
import ru.benefic.geekhome.task.students.service.StudentService;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public Page<StudentDto> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "5") int size) {

        if (page <= 1) {
            page = 0;
        } else {
            page--;
        }

        return service.getAll(page, size);
    }

    @GetMapping("/{id}")
    public StudentDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDto add(@RequestBody StudentDto student) {
        return service.save(student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}

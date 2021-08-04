package ru.benefic.geekhome.task.students.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.benefic.geekhome.task.students.model.dto.StudentDto;
import ru.benefic.geekhome.task.students.model.entity.Student;
import ru.benefic.geekhome.task.students.repository.StudentRepository;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
public class StudentService {
    private final StudentRepository repository;
    private final ModelMapper mapper;

    public StudentService(StudentRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<StudentDto> getAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size, Sort.by(Sort.Order.by("name"))))
                .map(it -> mapper.map(it, StudentDto.class));
    }

    public StudentDto getById(long id) {
        return repository.findById(id).map(it -> mapper.map(it, StudentDto.class))
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public StudentDto save(StudentDto student) {
        return mapper.map(repository.save(mapper.map(student, Student.class)), StudentDto.class);
    }

    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

}

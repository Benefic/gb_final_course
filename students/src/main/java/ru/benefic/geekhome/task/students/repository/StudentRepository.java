package ru.benefic.geekhome.task.students.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.benefic.geekhome.task.students.model.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}

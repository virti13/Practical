package com.practical.studentdetails.repository;

import com.practical.studentdetails.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends CrudRepository<Student, Long> {

    boolean existsByEmail(String email);

    @Query(value = "SELECT * from Student where id in (SELECT student_id from result where result = 'fail' AND year = 2014 AND subject_id = (SELECT id from Subject where name = 'english'))", nativeQuery = true)
    List<Student> getStudentsFailedInEnglish();

    @Query(value = "SELECT * from Student where id in (SELECT student_id from result where year = 2015)",nativeQuery = true)
    List<Student> getSortedMarksStudent();

    @Query(value = "select sum(marks) from result where student_id = ?1", nativeQuery = true)
    int getTotal(Long id);
}

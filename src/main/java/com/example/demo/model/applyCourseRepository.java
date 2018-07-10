package com.example.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hello on 2018/7/8.
 */
public interface ApplyCourseRepository extends JpaRepository<applyCourse,Integer> {
    @Transactional
    @Query(value = "SELECT apply_course.id,apply_course.student_id,apply_course.apply_info,apply_course.course_id,apply_course.`status` from apply_course INNER JOIN\n" +
            "class_info on class_info.id=apply_course.course_id where class_info.teacher_id=?1 and apply_course.`status`!=-1",nativeQuery = true)
    public List<applyCourse> findByStatus(String status);

    @Transactional
    @Modifying
    @Query(value="UPDATE apply_course SET apply_course.`status`=-1 WHERE apply_course.id=?1",nativeQuery = true)
    public void UUpdateStatus(Integer Id);
}

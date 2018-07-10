package com.example.demo.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClassAndStudentsRepository extends JpaRepository<ClassAndStutents,String> {
    @Query(value = "SELECT class_info.id,class_info.class_name FROM class_info INNER JOIN class_and_stutents " +
            " ON class_info.id=class_and_stutents.class_id WHERE class_and_stutents.stutents_id=?1",nativeQuery = true)
    public List<Object[]> findByStutentsId(String studentsid);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM class_and_stutents where class_and_stutents.stutents_id=?1 ",nativeQuery = true)
    public void deleteByStutentsId(String studentId);

<<<<<<< HEAD
   /* @Transactional
    @Query(value = "SELECT class_and_stutents.class_id,sys_user.username from sys_user INNER JOIN class_and_stutents " +
            " on class_and_stutents.stutents_id=sys_user.id ",nativeQuery = true)
    public List<Object[]> findAllClassAndStutents();*/
=======
    @Transactional
    @Query(value = "SELECT class_and_stutents.class_id,sys_user.username from sys_user INNER JOIN class_and_stutents " +
            " on class_and_stutents.stutents_id=sys_user.id ",nativeQuery = true)
    public List<Object[]> findAllClassAndStutents();
>>>>>>> 8474578df8bce3bcdcc724860ac56c1876a0f729
}

package com.example.demo.springsecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hello on 2018/4/2.
 */
public interface SysUserRepository extends JpaRepository<SysUser,Long> {
    public SysUser findByUsername(String username);
    @Transactional
    @Modifying
    @Query(value = "delete from sys_user  where id =( select t.id from (SELECT MAX(id) as id FROM sys_user) as t)",nativeQuery = true)
    public void deleteSysUserById();

    @Query(value = "SELECT sys_user.username,sys_user.id FROM sys_user INNER JOIN class_and_stutents on \n" +
            "sys_user.id = class_and_stutents.stutents_id \n" +
            "WHERE class_and_stutents.class_id =?1",nativeQuery = true)
    List<Object[]> findbyClassId(String classid);
}

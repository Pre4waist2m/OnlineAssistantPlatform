package com.example.demo.servletUploadify;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hello on 2018/7/3.
 */
public interface resourceListRepository extends JpaRepository<ResourceList, String> {
    public List<ResourceList> findByClassId(String classid);
    @Transactional
    public void deleteByResourceId(String resourceId);
}

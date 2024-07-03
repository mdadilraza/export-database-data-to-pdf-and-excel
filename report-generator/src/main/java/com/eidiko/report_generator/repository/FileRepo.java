package com.eidiko.report_generator.repository;

import com.eidiko.report_generator.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepo extends JpaRepository<FileEntity, Integer> {


     FileEntity findByFileName(String fileName);
}

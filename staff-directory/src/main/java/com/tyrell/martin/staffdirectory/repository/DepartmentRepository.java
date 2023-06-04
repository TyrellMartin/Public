package com.tyrell.martin.staffdirectory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyrell.martin.staffdirectory.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String>{

}

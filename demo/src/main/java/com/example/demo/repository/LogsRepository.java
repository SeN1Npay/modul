package com.example.demo.repository;

import com.example.demo.entity.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepository extends CrudRepository<Logs, Integer>{

}
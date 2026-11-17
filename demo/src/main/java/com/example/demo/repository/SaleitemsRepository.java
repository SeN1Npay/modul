package com.example.demo.repository;

import com.example.demo.entity.Saleitems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleitemsRepository extends CrudRepository<Saleitems, Integer>{

}
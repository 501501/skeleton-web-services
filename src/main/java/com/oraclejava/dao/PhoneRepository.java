package com.oraclejava.dao;

import com.oraclejava.model.Phone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhoneRepository extends PagingAndSortingRepository<Phone, Integer>,
        QuerydslPredicateExecutor<Phone> {
    @Query("select p from Phone p where p.name like %:name%")
    List<Phone> findByNameContaining(@Param("name") String name);
}

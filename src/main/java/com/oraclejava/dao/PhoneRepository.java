package com.oraclejava.dao;

import com.oraclejava.model.Phone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PhoneRepository extends PagingAndSortingRepository<Phone, Integer> {}

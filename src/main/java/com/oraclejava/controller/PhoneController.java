package com.oraclejava.controller;

import com.oraclejava.dao.PhoneRepository;
import com.oraclejava.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
public class PhoneController {
    @Autowired
    private PhoneRepository phoneRepository;

    @RequestMapping(value = "api/phones", method = RequestMethod.GET)
    public ResponseEntity<List<Phone>> getPhones() {
        List<Phone> phones = (List<Phone>) phoneRepository.findAll();
        return new ResponseEntity<>(phones, HttpStatus.OK);
    }
}

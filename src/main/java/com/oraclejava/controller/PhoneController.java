package com.oraclejava.controller;

import com.oraclejava.dao.PhonePredicate;
import com.oraclejava.dao.PhoneRepository;
import com.oraclejava.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
public class PhoneController {
    @Autowired
    private PhoneRepository phoneRepository;

    @RequestMapping(value = "api/phones", method = RequestMethod.GET)
    public ResponseEntity<List<Phone>> getPhones(@RequestParam String name) {
        // List<Phone> phones = (List<Phone>) phoneRepository.findByNameContaining(name);
        List<Phone> phones = (List<Phone>) phoneRepository.findAll(PhonePredicate.search(name));
        return new ResponseEntity<>(phones, HttpStatus.OK);
    }
}

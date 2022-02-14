package com.oraclejava.controller;

import com.oraclejava.dao.PhoneRepository;
import com.oraclejava.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
    @Autowired
    private PhoneRepository phoneRepository;
    private static final int PAGE_SIZE = 5;

    @RequestMapping(value = "/phone/create", method = RequestMethod.GET)
    public String create(Model model) {
        return "entity/phoneCreate";
    }

    @RequestMapping(value = "/phone/create", method = RequestMethod.POST)
    public String create(Phone phone, Model model) {
        phoneRepository.save(phone);
        return "redirect:/";
    }

    @RequestMapping(value = "/phone/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Integer id, Model model) {
        Phone phone = phoneRepository.findById(id).get();
        model.addAttribute("phone", phone);
        return "entity/phoneUpdate";
    }

    // update
    @RequestMapping(params = "update", value = "/phone/update/{id}", method = RequestMethod.POST)
    public String update(Phone phone, Model model) {
        Phone sPhone = phoneRepository.findById(phone.getId()).get();
        sPhone.setName(phone.getName());
        sPhone.setPrice(phone.getPrice());
        phoneRepository.save(sPhone);
        return "redirect:/";
    }

    // delete
    @RequestMapping(params = "delete", value = "/phone/update/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable Integer id, Model model) {
        Phone sPhone = phoneRepository.findById(id).get();
        phoneRepository.delete(sPhone);
        return "redirect:/";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(@RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = 1;
        }

        ModelAndView mav = new ModelAndView();
        mav.setViewName("entity/index");
        mav.addObject("msg", "안녕하세요");
        // mav.addObject("phoneList", phoneRepository.findAll());
        Page<Phone> phones =
                phoneRepository.findAll(PageRequest.of(pageNumber-1,
                        PAGE_SIZE, Sort.by("name").ascending()));
        int current = phones.getNumber() + 1;
        int begin = 1;
        int end = phones.getTotalPages();

        mav.addObject("phoneList", phones);
        mav.addObject("beginIndex", begin);
        mav.addObject("endIndex", end);
        mav.addObject("currentIndex", current);

        return mav;
    }
}

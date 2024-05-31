package com.practice_spring_boot_3.controller;

import com.practice_spring_boot_3.model.Customer;
import com.practice_spring_boot_3.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public ModelAndView listCustomer(){
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("customers", customerService.findAll());
        return modelAndView;
    }

    @GetMapping("/create-form")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/save-create")
    public ModelAndView saveCreate(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", "New Customer Created Successfully");
        return modelAndView;
    }

    @GetMapping("/update-form/{id}")
    public ModelAndView updateForm(@PathVariable("id") Integer id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("update");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            return new ModelAndView("error");
        }
    }

    @PostMapping("/save-update")
    public ModelAndView saveUpdate(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", "Customer Updated Successfully");
        return modelAndView;
    }

    @GetMapping("/delete-form/{id}")
    public ModelAndView deleteForm(@PathVariable("id") Integer id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("delete");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            return new ModelAndView("error");
        }
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("customer") Customer customer) {
        customerService.delete(customer.getId());
        return "redirect:/customers";
    }
}



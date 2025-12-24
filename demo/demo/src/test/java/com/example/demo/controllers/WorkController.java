package com.example.demo.controllers;

import com.example.demo.repositories.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/works")
public class WorkController {

    @Autowired private WorkInOrderRepository workRepo;
    @Autowired private OrderRepository orderRepo;
    @Autowired private ServiceRepository serviceRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("works", workRepo.findAll());
        return "works/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("work", new WorkInOrder());
        model.addAttribute("orders", orderRepo.findAll());
        model.addAttribute("services", serviceRepo.findAll());
        return "works/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("work") WorkInOrder work, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Если данные некорректны, возвращаем на форму с сохранением ошибок
            model.addAttribute("orders", orderRepo.findAll());
            model.addAttribute("services", serviceRepo.findAll());
            return "works/form";
        }
        workRepo.save(work);
        return "redirect:/works";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        try {
            workRepo.deleteById(id);
        } catch (Exception e) {
            // Если удалить нельзя (например, данные связаны), сайт не упадет
            return "redirect:/works?error=conflict";
        }
        return "redirect:/works";
    }
}
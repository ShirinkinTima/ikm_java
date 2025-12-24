package com.example.demo.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleConflict(DataIntegrityViolationException ex, Model model) {
        model.addAttribute("errorMessage", "Такая запись уже существует в базе данных (дубликат по ключевому полю)!");
        return "error-page";
    }

    @ExceptionHandler(Exception.class)
    public String handleAll(Exception ex, Model model) {
        model.addAttribute("errorMessage", "Техническая ошибка: " + ex.getMessage());
        return "error-page";
    }
}
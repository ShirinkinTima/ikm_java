package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin") // Все адреса будут начинаться с /admin
public class AdminController {

    @Autowired private MasterRepository masterRepo;
    @Autowired private PositionRepository positionRepo;
    @Autowired private ClientRepository clientRepo;
    @Autowired private CarRepository carRepo;
    @Autowired private ServiceRepository serviceRepo;
    @Autowired private OrderRepository orderRepo;

    // --- МАСТЕРА ---
    @GetMapping("/masters")
    public String listMasters(Model model) {
        model.addAttribute("masters", masterRepo.findAll());
        model.addAttribute("positions", positionRepo.findAll());
        model.addAttribute("newMaster", new Master());
        return "admin/masters";
    }

    @PostMapping("/masters/save")
    public String saveMaster(@ModelAttribute Master master) {
        masterRepo.save(master);
        return "redirect:/admin/masters";
    }

    @GetMapping("/masters/delete/{id}")
    public String deleteMaster(@PathVariable Long id) {
        masterRepo.deleteById(id);
        return "redirect:/admin/masters";
    }

    // --- УСЛУГИ ---
    @GetMapping("/services")
    public String listServices(Model model) {
        model.addAttribute("services", serviceRepo.findAll());
        model.addAttribute("newService", new ServiceEntity());
        return "admin/services";
    }

    @PostMapping("/services/save")
    public String saveService(@ModelAttribute ServiceEntity service) {
        serviceRepo.save(service);
        return "redirect:/admin/services";
    }

    @GetMapping("/services/delete/{id}")
    public String deleteService(@PathVariable Long id) {
        serviceRepo.deleteById(id);
        return "redirect:/admin/services";
    }

    // --- КЛИЕНТЫ И АВТО ---
    @GetMapping("/clients")
    public String listClients(Model model) {
        model.addAttribute("clients", clientRepo.findAll());
        model.addAttribute("cars", carRepo.findAll());
        model.addAttribute("newClient", new Client());
        return "admin/clients";
    }

    @PostMapping("/clients/save")
    public String saveClient(@ModelAttribute Client client) {
        clientRepo.save(client);
        return "redirect:/admin/clients";
    }

    // --- ДОЛЖНОСТИ ---
    @GetMapping("/positions")
    public String listPositions(Model model) {
        model.addAttribute("positions", positionRepo.findAll());
        model.addAttribute("newPosition", new Position());
        return "admin/positions";
    }

    @PostMapping("/positions/save")
    public String savePosition(@ModelAttribute Position position) {
        positionRepo.save(position);
        return "redirect:/admin/positions";
    }

    // --- ЗАКАЗЫ (Управление статусами) ---
    @GetMapping("/orders")
    public String listOrders(Model model) {
        model.addAttribute("orders", orderRepo.findAll());
        model.addAttribute("clients", clientRepo.findAll());
        model.addAttribute("masters", masterRepo.findAll());
        model.addAttribute("newOrder", new Order());
        return "admin/orders";
    }

    @PostMapping("/orders/save")
    public String saveOrder(@ModelAttribute Order order) {
        orderRepo.save(order);
        return "redirect:/admin/orders";
    }
}
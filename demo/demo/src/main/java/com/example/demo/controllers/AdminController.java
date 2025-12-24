package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.repositories.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {

    @Autowired private ClientRepository clientRepo;
    @Autowired private VehicleRepository vehicleRepo;
    @Autowired private ServiceCatalogRepository catalogRepo;

    // ==========================================================
    // КЛИЕНТЫ (Client)
    // ==========================================================

    @GetMapping("/clients")
    public String listClients(Model model) {
        model.addAttribute("clients", clientRepo.findAll());
        model.addAttribute("newClient", new Client());
        return "admin/clients";
    }

    @GetMapping("/clients/edit/{id}")
    public String editClientForm(@PathVariable("id") Long id, Model model) {
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Неверный ID клиента: " + id));
        model.addAttribute("client", client);
        return "admin/client-edit";
    }

//    @PostMapping("/clients/save")
//    public String saveClient(@Valid @ModelAttribute("newClient") Client client,
//                             BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            model.addAttribute("clients", clientRepo.findAll());
//            return (client.getId() == null) ? "admin/clients" : "admin/client-edit";
//        }
//        clientRepo.save(client);
//        return "redirect:/clients";
//    }

    @GetMapping("/clients/delete/{id}")
    public String deleteClient(@PathVariable Long id, RedirectAttributes ra) {
        try {
            clientRepo.deleteById(id);
            ra.addFlashAttribute("success", "Клиент успешно удален.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Нельзя удалить клиента: за ним числятся автомобили!");
        }
        return "redirect:/clients";
    }

    // ==========================================================
    // АВТОМОБИЛИ (Vehicle)
    // ==========================================================

    @GetMapping("/vehicles")
    public String listVehicles(Model model) {
        model.addAttribute("vehicles", vehicleRepo.findAll());
        model.addAttribute("clients", clientRepo.findAll());
        model.addAttribute("newVehicle", new Vehicle());
        return "admin/vehicles";
    }

    @GetMapping("/vehicles/edit/{id}")
    public String editVehicleForm(@PathVariable("id") Long id, Model model) {
        Vehicle vehicle = vehicleRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Неверный ID авто: " + id));
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("clients", clientRepo.findAll());
        return "admin/vehicle-edit";
    }

//    @PostMapping("/vehicles/save")
//    public String saveVehicle(@Valid @ModelAttribute("newVehicle") Vehicle vehicle,
//                              BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            model.addAttribute("vehicles", vehicleRepo.findAll());
//            model.addAttribute("clients", clientRepo.findAll());
//            return (vehicle.getId() == null) ? "admin/vehicles" : "admin/vehicle-edit";
//        }
//        vehicleRepo.save(vehicle);
//        return "redirect:/vehicles";
//    }

    @GetMapping("/vehicles/delete/{id}")
    public String deleteVehicle(@PathVariable Long id, RedirectAttributes ra) {
        try {
            vehicleRepo.deleteById(id);
            ra.addFlashAttribute("success", "Автомобиль удален.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Нельзя удалить авто: по нему есть записи в журнале заказов!");
        }
        return "redirect:/vehicles";
    }

    // ==========================================================
    // УСЛУГИ (ServiceCatalog)
    // ==========================================================

    @GetMapping("/services")
    public String listServices(Model model) {
        model.addAttribute("services", catalogRepo.findAll());
        model.addAttribute("newService", new ServiceCatalog());
        return "admin/services";
    }

    @GetMapping("/services/edit/{id}")
    public String editServiceForm(@PathVariable("id") Long id, Model model) {
        ServiceCatalog service = catalogRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Неверный ID услуги: " + id));
        model.addAttribute("service", service);
        return "admin/service-edit";
    }

//    @PostMapping("/services/save")
//    public String saveService(@Valid @ModelAttribute("service") ServiceCatalog service,
//                              BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            model.addAttribute("services", catalogRepo.findAll());
//            return (service.getId() == null) ? "admin/services" : "admin/service-edit";
//        }
//        catalogRepo.save(service);
//        return "redirect:/services";
//    }

    @GetMapping("/services/delete/{id}")
    public String deleteService(@PathVariable Long id, RedirectAttributes ra) {
        try {
            catalogRepo.deleteById(id);
            ra.addFlashAttribute("success", "Услуга удалена.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Нельзя удалить услугу: она уже использована в заказах!");
        }
        return "redirect:/services";
    }
}
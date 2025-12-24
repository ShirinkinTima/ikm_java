package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.repositories.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired private ServiceOrderRepository orderRepo;
    @Autowired private VehicleRepository vehicleRepo;
    @Autowired private ServiceCatalogRepository catalogRepo;
    @Autowired private ClientRepository clientRepo;

    // Просмотр списка всех заказов
    @GetMapping
    public String list(Model model) {
        model.addAttribute("orders", orderRepo.findAll());
        return "orders/list";
    }

    // Форма добавления (инициализация цепочки объектов: Заказ -> Авто -> Клиент)
    @GetMapping("/add")
    public String addForm(Model model) {
        ServiceOrder order = new ServiceOrder();
        Vehicle vehicle = new Vehicle();
        vehicle.setOwner(new Client()); // Создаем пустых "родителей", чтобы форма могла их заполнить
        order.setVehicle(vehicle);

        model.addAttribute("order", order);
        populateModel(model);
        return "orders/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        ServiceOrder order = orderRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Заказ не найден: " + id));

        model.addAttribute("order", order);
        populateModel(model);
        return "orders/form";
    }

    // Метод сохранения
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("order") ServiceOrder order, BindingResult result, Model model) {

        Vehicle v = order.getVehicle();

        // валидация новых данных
        if (v != null && v.getId() == null) { // Если выбрано "Новое авто"
            if (v.getModel() == null || v.getModel().isBlank()) {
                result.rejectValue("vehicle.model", "field.required", "Введите модель автомобиля");
            }
            if (v.getPlateNumber() == null || v.getPlateNumber().isBlank()) {
                result.rejectValue("vehicle.plateNumber", "field.required", "Введите госномер");
            }

            // Если выбран и "Новый клиент"
            if (v.getOwner() != null && v.getOwner().getId() == null) {
                if (v.getOwner().getFullName() == null || v.getOwner().getFullName().isBlank()) {
                    result.rejectValue("vehicle.owner.fullName", "field.required", "Введите имя владельца");
                }
                if (v.getOwner().getPhone() == null || v.getOwner().getPhone().isBlank()) {
                    result.rejectValue("vehicle.owner.phone", "field.required", "Введите телефон");
                }
            }
        }

        // Проверка на дубликат заказа
        if (!result.hasErrors() && v != null && v.getId() != null && order.getService() != null && order.getOrderDate() != null) {
            Optional<ServiceOrder> similar = orderRepo.findSimilar(
                    order.getOrderDate(), order.getVehicle(), order.getService()
            );
            if (similar.isPresent() && !similar.get().getId().equals(order.getId())) {
                result.rejectValue("orderDate", "duplicate", "Такой заказ уже существует в журнале!");
            }
        }

        if (result.hasErrors()) {
            populateModel(model);
            return "orders/form";
        }

        // сохраняем
        try {
            if (v != null && v.getOwner() != null && v.getOwner().getId() == null) {
                clientRepo.save(v.getOwner());
            }
            if (v != null && v.getId() == null) {
                vehicleRepo.save(v);
            }
            orderRepo.save(order);
        } catch (Exception e) {
            // Если упало на уровне БД (например, дубликат госномера в таблице авто)
            result.rejectValue("vehicle.plateNumber", "db.error", "Ошибка: " + e.getMessage());
            populateModel(model);
            return "orders/form";
        }

        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        orderRepo.deleteById(id);
        return "redirect:/orders";
    }

    // метод для заполнения списков
    private void populateModel(Model model) {
        model.addAttribute("vehicles", vehicleRepo.findAll());
        model.addAttribute("services", catalogRepo.findAll());
        model.addAttribute("clients", clientRepo.findAll());
    }
}
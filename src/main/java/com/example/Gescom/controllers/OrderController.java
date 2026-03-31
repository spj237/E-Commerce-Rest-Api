package com.example.Gescom.controllers;

import com.example.Gescom.dtos.OrderDto;
import com.example.Gescom.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.Gescom.dtos.Response;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public Response create(@RequestBody OrderDto dto) {
        return orderService.create(dto);
    }

    @GetMapping("all")
    public Response getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public Response getById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @PutMapping("/{id}")
    public Response update(@PathVariable Long id, @RequestBody OrderDto dto) {
        return orderService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        return orderService.delete(id);
    }
}

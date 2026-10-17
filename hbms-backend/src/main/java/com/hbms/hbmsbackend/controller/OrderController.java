package com.hbms.hbmsbackend.controller;

import com.hbms.hbmsbackend.entity.Order;
import com.hbms.hbmsbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    // PATCH: http://localhost:8080/api/orders/1/status?newStatus=Baking
    @PatchMapping("/{id}/status")
    public Order updateOrderStatus(@PathVariable Long id, @RequestParam String newStatus) {
        return orderService.updateOrderStatus(id, newStatus);
    }
}
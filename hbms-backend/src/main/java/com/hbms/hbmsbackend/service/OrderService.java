package com.hbms.hbmsbackend.service;

import com.hbms.hbmsbackend.entity.*;
import com.hbms.hbmsbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final RecipeRepository recipeRepository;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        OrderDetailRepository orderDetailRepository,
                        RecipeRepository recipeRepository,
                        InventoryRepository inventoryRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.recipeRepository = recipeRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public List<Order> getAllOrders() { return orderRepository.findAll(); }
    public Order getOrderById(Long id) { return orderRepository.findById(id).orElse(null); }
    public Order saveOrder(Order order) { return orderRepository.save(order); }
    public void deleteOrder(Long id) { orderRepository.deleteById(id); }

    /**
     * CORE BUSINESS LOGIC (FR-3.1 in SRS)
     * When an order moves to "Baking", deduct ingredients from inventory.
     */
    @Transactional
    public Order updateOrderStatus(Long orderId, String newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // If transitioning to "Baking", deduct stock
        if ("Baking".equalsIgnoreCase(newStatus) && !"Baking".equalsIgnoreCase(order.getOrderStatus())) {
            deductIngredients(order);
        }
        // SRS FR-3.2: If cancelled before Baking, restore stock
        if ("Cancelled".equalsIgnoreCase(newStatus) && "Baking".equalsIgnoreCase(order.getOrderStatus())) {
            restoreIngredients(order);
        }

        order.setOrderStatus(newStatus);
        return orderRepository.save(order);
    }

    private void deductIngredients(Order order) {
        List<OrderDetail> details = orderDetailRepository.findAll();
        for (OrderDetail detail : details) {
            if (!detail.getOrder().getOrderId().equals(order.getOrderId())) continue;

            Product product = detail.getProduct();
            int quantityOrdered = detail.getQuantity();

            // Get all recipe lines for this product
            List<Recipe> recipes = recipeRepository.findAll();
            for (Recipe recipe : recipes) {
                if (!recipe.getProduct().getProductId().equals(product.getProductId())) continue;

                // Calculate required amount = recipe.qty * ordered quantity
                double required = recipe.getQuantityRequired() * quantityOrdered;
                Inventory ingredient = recipe.getIngredient();

                if (ingredient.getQuantityStock() < required) {
                    throw new RuntimeException("Insufficient stock for " + ingredient.getIngredientName());
                }
                ingredient.setQuantityStock(ingredient.getQuantityStock() - required);
                inventoryRepository.save(ingredient);
            }
        }
    }

    private void restoreIngredients(Order order) {
        List<OrderDetail> details = orderDetailRepository.findAll();
        for (OrderDetail detail : details) {
            if (!detail.getOrder().getOrderId().equals(order.getOrderId())) continue;

            Product product = detail.getProduct();
            int quantityOrdered = detail.getQuantity();

            List<Recipe> recipes = recipeRepository.findAll();
            for (Recipe recipe : recipes) {
                if (!recipe.getProduct().getProductId().equals(product.getProductId())) continue;

                double required = recipe.getQuantityRequired() * quantityOrdered;
                Inventory ingredient = recipe.getIngredient();
                ingredient.setQuantityStock(ingredient.getQuantityStock() + required);
                inventoryRepository.save(ingredient);
            }
        }
    }
}
package com.example.Gescom.services;

import com.example.Gescom.dtos.OrderDto;
import com.example.Gescom.dtos.Response;
import com.example.Gescom.entities.Orders;
import com.example.Gescom.entities.Product;

import com.example.Gescom.entities.enums.OrderStatus;
import com.example.Gescom.repositories.OrderRepository;
import com.example.Gescom.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    // Création
    public Response create(OrderDto dto) {
        try {
            Orders order = new Orders();
            order.setDate(LocalDateTime.now()); // Date automatique
            order.setTotal(dto.getTotal());
            order.setStatus(OrderStatus.EN_COURS);

            Set<Product> products = new HashSet<>(productRepository.findAllById(dto.getProductIds()));
            order.setProducts(products);

            Orders savedOrder = orderRepository.save(order);
            return new Response(200, true, "Commande créée avec succès", null, null);

        } catch (Exception e) {
            return new Response(500, false, "Erreur lors de la création de la commande", null, e.getMessage());
        }
    }

    public Response getAll() {
        try {
            List<OrderDto> dtos = orderRepository.findAll()
                    .stream()
                    .map(order -> {
                        OrderDto dto = new OrderDto();
                        dto.setId(order.getId());
                        dto.setTotal(order.getTotal());
                        dto.setStatus(order.getStatus());
                        dto.setProducts(order.getProducts()); // <-- direct
                        return dto;
                    }).toList();

            return new Response(200, true, "Liste des commandes récupérée", dtos, null);
        } catch (Exception e) {
            return new Response(500, false, "Erreur lors de la récupération des commandes", null, e.getMessage());
        }
    }

    public Response getById(Long id) {
        try {
            Optional<Orders> optionalOrder = orderRepository.findById(id);
            if (optionalOrder.isEmpty()) {
                return new Response(404, false, "Commande introuvable", null, null);
            }

            Orders order = optionalOrder.get();
            OrderDto dto = new OrderDto();
            dto.setId(order.getId());
            dto.setTotal(order.getTotal());
            dto.setStatus(order.getStatus());
            dto.setProducts(order.getProducts()); // <-- direct

            return new Response(200, true, "Commande trouvée", dto, null);
        } catch (Exception e) {
            return new Response(500, false, "Erreur lors de la récupération de la commande", null, e.getMessage());
        }
    }

    // Mise à jour
    public Response update(Long id, OrderDto dto) {
        try {
            Optional<Orders> optionalOrder = orderRepository.findById(id);
            if (!optionalOrder.isPresent()) {
                return new Response(404, false, "Commande introuvable", null, null);
            }

            Orders order = optionalOrder.get();
            order.setTotal(dto.getTotal());
            order.setStatus(dto.getStatus()); // Enum mis à jour

            if (dto.getProductIds() != null && !dto.getProductIds().isEmpty()) {
                Set<Product> products = new HashSet<>(productRepository.findAllById(dto.getProductIds()));
                order.setProducts(products);
            }

            Orders updatedOrder = orderRepository.save(order);
            return new Response(200, true, "Commande mise à jour avec succès", updatedOrder, null);

        } catch (Exception e) {
            return new Response(500, false, "Erreur lors de la mise à jour de la commande", null, e.getMessage());
        }
    }

    // Suppression
    public Response delete(Long id) {
        try {
            Optional<Orders> optionalOrder = orderRepository.findById(id);
            if (!optionalOrder.isPresent()) {
                return new Response(404, false, "Commande introuvable", null, null);
            }

            orderRepository.delete(optionalOrder.get());
            return new Response(200, true, "Commande supprimée avec succès", null, null);

        } catch (Exception e) {
            return new Response(500, false, "Erreur lors de la suppression de la commande", null, e.getMessage());
        }
    }
}

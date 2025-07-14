package com.example.orderservice.Service;

import com.example.orderservice.entity.Order;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {

    Order createOrder (Order order);

    Order getOrderById (Long id);

  List<Order> getOrdersByOrderId(Long userId);

    List<Order> getOrdersByUserId(Long userId);

    List<Order> getAllOrders();

    boolean updateOrderStatus(Long id, String status);

    List<Order> getOrdersByStatus(String status);

    Page<Order> getAllPaginateOrders(int offset, int pageSize);
}

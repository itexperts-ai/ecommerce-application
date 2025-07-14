package com.example.orderservice.Repository;

import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderStatusHistory, Long> {
List<OrderStatusHistory>allOrdersByStatusChangedAt(Long orderId);
}

package com.example.orderservice.Repository;

import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderStatusHistory, Long> {

List<OrderStatusHistory> allOrdersByStatusChangedAt(Long orderId);
}

package com.example.orderservice.Service;

import com.example.orderservice.entity.OrderHistory;

import java.util.List;

public interface OrderHistoryService {
    List<OrderHistory> getHistoryByOrderId(Long OrderId);
}

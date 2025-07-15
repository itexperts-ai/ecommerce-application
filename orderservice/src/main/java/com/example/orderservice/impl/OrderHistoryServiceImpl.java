package com.example.orderservice.impl;

import com.example.orderservice.Repository.OrderHistoryRepository;
import com.example.orderservice.Service.OrderHistoryService;
import com.example.orderservice.entity.OrderHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;



    @Override
    public List<OrderHistory> getHistoryByOrderId(Long orderId) {
        return orderHistoryRepository.findByOrderIdOrderByChangedAtAsc(orderId);
    }
}

package com.example.orderservice.impl;

import com.example.orderservice.Repository.OrderHistoryRepository;
import com.example.orderservice.Service.OrderHistoryService;
import com.example.orderservice.entity.OrderStatusHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;


    @Override
    public List<Map<String, Object>> getAllOrdersHistoryStatus() {
        List<OrderStatusHistory> histories = orderHistoryRepository.findAll(Sort.by("orderId").ascending().and(Sort.by("changedAt").ascending()));

        Map<Long,List<OrderStatusHistory>> grouped = histories.stream().collect(Collectors.groupingBy(OrderStatusHistory::getOrderId));

        List<Map<String,Object>> result = new ArrayList<>();

        for (Map.Entry<Long, List<OrderStatusHistory>> entry : grouped.entrySet()){
            Map<String, Object> map = new HashMap<>();
            map.put("orderId",entry.getKey());
            map.put("history",entry.getValue().stream().map(h -> {Map<String, Object> histMap = new HashMap<>();
                histMap.put("oldStatus", h.getOldStatus());
                histMap.put("newStatus", h.getNewStatus());
                histMap.put("changedAt", h.getChangedAt());
                return histMap;
            }).collect(Collectors.toList()));
            result.add(map);
        }
            return result;

    }
}

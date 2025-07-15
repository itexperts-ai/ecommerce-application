package com.example.orderservice.Service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface OrderHistoryService {
    List<Map<String, Object>>getAllOrdersHistoryStatus();
}

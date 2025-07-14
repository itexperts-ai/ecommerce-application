package com.example.orderservice.Service;

import java.util.List;
import java.util.Map;

public interface OrderHistoryService {
    List<Map<String, Object>>getAllOrdersHistoryStatus();
}

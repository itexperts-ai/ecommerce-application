package com.example.orderservice.impl;
import com.example.orderservice.Exception.DataNotFoundException;
import com.example.orderservice.Repository.OrderRepository;
import com.example.orderservice.Service.OrderService;
import com.example.orderservice.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        validateUserAndProduct(order.getUserId(), order.getProductId());
        order.setOrderDate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Order not found given with id: " + id));
    }

    @Override
    public List<Order> getOrdersByOrderId(Long userId) {
        return List.of();
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order>getAllOrders(){
        return orderRepository.findAll();
    }

    @Override
    public boolean updateOrderStatus(Long orderId, String status ){
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()){
            Order order = orderOptional.get();
            order.setStatus(status);
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    @Override
    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    public Page<Order> getAllPaginateOrders(int offset, int pageSize) {
    Page<Order> orders = orderRepository.findAll(PageRequest.of(offset, pageSize));
        return orders;
    }


    private void validateUserAndProduct(Long userId, Long productId) {
        if (userId == null || userId <= 0) {
            throw new DataNotFoundException("Invalid user ID: " + userId);
        }
        if (productId == null || productId <= 0) {
            throw new DataNotFoundException("Invalid product ID: " + productId);
        }
    }
}


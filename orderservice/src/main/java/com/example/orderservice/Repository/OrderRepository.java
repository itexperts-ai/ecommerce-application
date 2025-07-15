package com.example.orderservice.Repository;

import com.example.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{
    List<Order> findByUserId(Long userId);
    List<Order> findAll();
    List<Order> findByStatus(String status);


}

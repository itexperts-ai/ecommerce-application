package com.example.orderservice.Controller;

import com.example.orderservice.Service.OrderHistoryService;
import com.example.orderservice.entity.Order;
import com.example.orderservice.Service.OrderService;
import com.example.orderservice.entity.OrderStatusHistory;
import com.example.orderservice.impl.RestTemplateProvider;
import com.example.orderservice.entity.UserEntity;
import com.example.orderservice.util.ApiOrderPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(ApiOrderPath.V1)
@CrossOrigin(origins = "*")
@Slf4j
public class OrderController {

    @Autowired
    public OrderService orderService;

    @Autowired
    public OrderStatusHistory orderStatusHistory;

    @Autowired
    public OrderHistoryService orderHistoryService;

    @Autowired
    private RestTemplateProvider restTemplateProvider;

    @PostMapping(ApiOrderPath.CREATE_ORDER)
    public Order createOrder (@RequestBody Order order){
        log.debug("Request Received for Order !!");
        if(order == null){
            log.warn("Order Request Body is Null.....");
        }
             Order or =orderService.createOrder(order);
                  if  (or==null){
                      log.error("Order is Missing..... or Order Fail.. ");
                  }
                  log.info("Order Placed Succesfully....");
                  return or;
    }

    @GetMapping(ApiOrderPath.ORDER_BY_ID)
    public Order getOrderById(@PathVariable Long id) {
        log.debug("Receving Order By Order Id: {}",id);
        if(id==null){
            log.info("Order is Invalid Or Wrong...");
        }
        Order or = orderService.getOrderById(id);
            if (or == null){
                log.warn("Order is Not Available for this Id.....");
            }
            log.info("Order Found Succusfully... {}",id);
        return or;
    }
    
    @GetMapping(ApiOrderPath.ORDER_BY_USER_ID)
    public List<Order> getOrdersByUserId(@PathVariable Long userId){
        log.debug("Found Order By UserId: {}", userId);
        if(userId==null){
            log.warn("Order Id null or Invalid");
        }
        return orderService.getOrdersByUserId(userId);
    }

    private final RestTemplate restTemplate = new RestTemplate();
    @GetMapping(ApiOrderPath.FIND_BY_USER_ID)
    public Optional<UserEntity> getUserFromUserService(@PathVariable Long id) {
        Optional<UserEntity> user = restTemplateProvider.getUserData(id);
        System.out.println(user);
        return restTemplateProvider.getUserData(id);
    }

    @GetMapping(ApiOrderPath.FIND_ALL_ORDERS)
    public List<Order>getAllOrders(){
        log.debug("Fetching All Orders");
        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty()){

            log.info("No Orders found in System");
        }else{
            log.info("Total order Found: ",orders.size());
        }
        return orders;
    }

    @GetMapping(ApiOrderPath.ORDER_BY_STATUS)
    public  ResponseEntity<List<Order>> getOrderByStatus(@RequestParam String status){
        List<Order> order = orderService.getOrdersByStatus(status);
        if (order.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(order);
    }

    @PutMapping(ApiOrderPath.UPDATE_ORDER)
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long id,@RequestParam String status){
        boolean update = orderService.updateOrderStatus(id,status);
        if (update){
            return  ResponseEntity.ok("Order Status Update Successfully. ");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Not Found.");
        }
    }

    @GetMapping(ApiOrderPath.ORDER_PAGINATED)
    public ResponseEntity<Page<Order>> getAllPaginateOrders(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Order> allOrders = orderService.getAllPaginateOrders(offset,pageSize);
        return new ResponseEntity<>(allOrders,HttpStatus.OK);

    }


}

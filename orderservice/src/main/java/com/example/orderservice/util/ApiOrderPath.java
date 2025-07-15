package com.example.orderservice.util;
//Constant Path
public interface ApiOrderPath {
    String API = "/api";
    String V1 = API + "/v1";
    String CREATE_ORDER = "/createOrder";
    String FIND_ALL_ORDERS = "/orders";
    String FIND_BY_USER_ID = "/getuser/{id}";
    String UPDATE_ORDER = "/order/update/{id}/{status}";
    String ORDER_BY_ID = "/order/{id}";
    String ORDER_BY_USER_ID = "/user/{userId}";
    String ORDER_BY_STATUS = "/order";
    String ORDER_PAGINATED = "/page/{offset}/{pageSize}";
    String ORDER_STATUS_HISTORY = "/order/history/{orderId}";

}

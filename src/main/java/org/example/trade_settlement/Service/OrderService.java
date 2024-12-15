package org.example.trade_settlement.Service;

import org.example.trade_settlement.Model.Order;
import org.example.trade_settlement.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    public Order placeOrder(Order order){
        order.setStatus("NEW");
        Order savedOrder = orderRepository.save(order);
        kafkaTemplate.send("order-placement", savedOrder);
        return savedOrder;
    }
}

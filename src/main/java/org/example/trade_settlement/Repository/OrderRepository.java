package org.example.trade_settlement.Repository;

import org.example.trade_settlement.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatusAndOrderType(String status, String orderType);
}

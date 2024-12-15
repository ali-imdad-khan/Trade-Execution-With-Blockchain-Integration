package org.example.trade_settlement.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "trades")
@Setter
@Getter
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sellerId;

    private Long buyerId;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal tradeQuantity;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal tradePrice;

    @Column(nullable = false)
    private LocalDateTime timeStamp;

}

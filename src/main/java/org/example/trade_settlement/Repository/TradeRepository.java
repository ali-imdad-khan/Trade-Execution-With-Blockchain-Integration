package org.example.trade_settlement.Repository;

import org.example.trade_settlement.Model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {
}

package org.example.trade_settlement.Repository;

import org.example.trade_settlement.Model.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerRepository extends JpaRepository<Ledger, Long> {
}

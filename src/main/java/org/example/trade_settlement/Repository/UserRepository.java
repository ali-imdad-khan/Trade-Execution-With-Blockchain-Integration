package org.example.trade_settlement.Repository;

import org.example.trade_settlement.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
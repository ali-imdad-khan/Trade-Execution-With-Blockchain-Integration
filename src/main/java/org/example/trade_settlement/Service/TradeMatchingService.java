package org.example.trade_settlement.Service;

import org.example.trade_settlement.Model.Order;
import org.example.trade_settlement.Model.Trade;
import org.example.trade_settlement.Repository.OrderRepository;
import org.example.trade_settlement.Repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class TradeMatchingService {
    private static final Logger logger = LoggerFactory.getLogger(TradeMatchingService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private BlockchainService blockchainService;

    @KafkaListener(topics = "order-placement", groupId = "trade-group")
    public void matchOrders(Order incomingOrder){

        List<Order> matchingOrders = orderRepository.findByStatusAndOrderType(
                "NEW", incomingOrder.getOrderType().equals("BUY") ? "SELL" : "BUY"
        );

        logger.info("Processing incoming order: {}", incomingOrder);

        for (Order matchingOrder: matchingOrders){

            if (matchingOrder.getPrice().compareTo(incomingOrder.getPrice()) == 0){

                logger.info("Found matching order: {}", matchingOrder);

                //Create a trade object
                BigDecimal tradeQuantity = incomingOrder.getQuantity().min(matchingOrder.getQuantity());
                logger.info("Trade quantity: {}", tradeQuantity);

                Trade trade = new Trade();
                trade.setBuyerId(incomingOrder.getOrderType().equals("BUY") ? incomingOrder.getUserId() : matchingOrder.getUserId());
                trade.setSellerId(incomingOrder.getOrderType().equals("SELL") ? incomingOrder.getUserId() : matchingOrder.getUserId());
                trade.setTradePrice(incomingOrder.getPrice());
                trade.setTradeQuantity(tradeQuantity);
                trade.setTimeStamp(LocalDateTime.now());
                tradeRepository.save(trade);

                logger.info("Trade saved: {}", trade);

                // Update Order quantities and statuses
                incomingOrder.setQuantity(incomingOrder.getQuantity().subtract(tradeQuantity));
                matchingOrder.setQuantity(matchingOrder.getQuantity().subtract(tradeQuantity));
                logger.info("Updated incoming order quantity: {}", incomingOrder.getQuantity());
                logger.info("Updated matching order quantity: {}", matchingOrder.getQuantity());

                if (incomingOrder.getQuantity().compareTo(BigDecimal.ZERO) == 0){
                    incomingOrder.setStatus("MATCHED");
                }
                if (matchingOrder.getQuantity().compareTo(BigDecimal.ZERO) == 0){
                    matchingOrder.setStatus("MATCHED");
                }
                orderRepository.save(incomingOrder);
                orderRepository.save(matchingOrder);

                // Record the trade on the blockchain
                logger.info("Start recording Trade on blockchain.");
                try {
                    blockchainService.recordTrade(
                            trade.getId(),
                            trade.getBuyerId().toString(),
                            trade.getSellerId().toString(),
                            trade.getTradeQuantity().toBigInteger(),
                            trade.getTradePrice().toBigInteger()
                    );
                    logger.info("Trade recorded successfully on the blockchain.");

                } catch (Exception e){
                    e.printStackTrace();
                    logger.info("Failed to record trade on blockchain.");
                }

                if (incomingOrder.getQuantity().compareTo(BigDecimal.ZERO) == 0) {
                    break;
                }

            }
        }
    }
}

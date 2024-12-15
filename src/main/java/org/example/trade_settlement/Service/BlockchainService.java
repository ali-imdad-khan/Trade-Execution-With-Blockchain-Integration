package org.example.trade_settlement.Service;

import org.example.trade_settlement.Blockchain.TradeSettlement;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;
import java.math.BigInteger;
@Service
public class BlockchainService {
    private final Web3j web3j;
    private final TradeSettlement contract;
    public BlockchainService() throws Exception{
        //Connect to Ganache
        this.web3j = Web3j.build(new HttpService("http://127.0.0.1:7545"));

        //Smart contract address from Tuffle deployment
        String contractAddress = "0xbF61A38D0c1767e9e634F97fda6dDcb48F236ED4";

        // Ganache Account address
        String ownerAddress = "0xbB907A62FE68b4cbf8f90f0E2a62435DC844e070";
//        PRIVATE KEY
//        0xd3f3edf31cd0d20110f2295af65e680b92c08c0e83d959dcff0c29b9c9df86ca
        // Transaction manager to sign transaction
        ClientTransactionManager transactionManager = new ClientTransactionManager(web3j, ownerAddress);

        //Load the contract
        this.contract = TradeSettlement.load(
                contractAddress,
                web3j,
                transactionManager,
                new org.web3j.tx.gas.DefaultGasProvider()
        );
    }
    //Record a trade on the blockchain
    public void recordTrade(Long tradeId,
                            String buyer,
                            String seller,
                            BigInteger quantity,
                            BigInteger price ) throws Exception{

        contract.recordTrade(
                BigInteger.valueOf(tradeId),
                buyer,
                seller,
                quantity,
                price
        ).send();
        System.out.println("Trade recorded successfully on blockchain.");
    }

    //Get number of trades on the blockchain
    public BigInteger getTradesCount() throws Exception {
        return contract.getTradesCount().send();
    }

}

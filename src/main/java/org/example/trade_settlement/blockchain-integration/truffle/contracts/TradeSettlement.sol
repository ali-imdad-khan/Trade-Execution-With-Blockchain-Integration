// SPDX License Identifier:MIT
pragma solidity ^0.8.0;

contract TradeSettlement{
    struct Trade{
        uint256 tradeId;
        address buyer;
        address seller;
        uint256 quantity;
        uint256 price;
        uint256 timestamp;
    }

    Trade[] public trades;

    event TradeRecorded(
        uint256 tradeId,
        address indexed buyer,
        address indexed seller,
        uint256 quantity,
        uint256 price,
        uint256 timestamp
    );

    function recordTrade(
        uint256 _tradeId,
        address _buyer,
        address _seller,
        uint256 _quantity,
        uint256 _price
    ) public {
        trades.push(Trade({
            tradeId: _tradeId,
            buyer: _buyer,
            seller: _seller,
            quantity: _quantity,
            price: _price,
            timestamp: block.timestamp
        })
        );
        emit TradeRecorded(_tradeId,_buyer,_seller,_quantity,_price,block.timestamp);
    }
    function getTrade(uint256 index) public view returns (
        uint256 tradeId,
        address buyer,
        address seller,
        uint256 quantity,
        uint256 price,
        uint256 timestamp
    ){
        Trade storage trade = trades[index];
        return (
        trade.tradeId,
        trade.buyer,
        trade.seller,
        trade.quantity,
        trade.price,
        trade.timestamp
    );
    }
    function getTradesCount() public view returns (uint256){
        return trades.length;
    }
}


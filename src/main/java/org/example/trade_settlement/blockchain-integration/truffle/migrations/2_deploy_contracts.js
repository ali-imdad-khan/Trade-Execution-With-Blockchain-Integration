const TradeSettlement = artifacts.require("TradeSettlement");

module.exports = function(deployer){
    deployer.deploy(TradeSettlement)
}
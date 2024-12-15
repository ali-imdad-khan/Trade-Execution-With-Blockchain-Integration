package org.example.trade_settlement.web3j;
import org.web3j.codegen.SolidityFunctionWrapperGenerator;

import java.io.File;

public class Web3JCodegen {
    public static void main(String[] args) {
        try {
            // Path to the Solidity ABI file
            String abiPath = "src/main/java/org/example/trade_settlement/web3j/TradeSettlement.abi";

            // Output directory for the generated Java wrapper
            String outputDir = "src/main/java";

            // Java package name for the generated class
            String packageName = "org.example.trade_settlement.Blockchain";

            // Generate Java wrapper
            SolidityFunctionWrapperGenerator.main(new String[]{
                    "-a", abiPath,
                    "-o", outputDir,
                    "-p", packageName
            });

            System.out.println("Java wrapper generated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
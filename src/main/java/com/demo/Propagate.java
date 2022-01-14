/*
 * Rich Zhao  zhao.zhenhua at {gmail | qq}.com
 */

package com.demo;

import com.demo.contracts.Account;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.client.protocol.response.BlockNumber;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

import java.math.BigInteger;

/**
 * @author Rich Zhao zhao.zhenhua@qq.com
 * @Description
 * @create 2021/8/5 1:48 PM
 */
public class Propagate {
    public static String configFile = Propagate.class.getClassLoader()
            .getResource("config.toml").getPath();
//    private static Client client;

    static BcosSDK sdk;
    static Client client;
    static CryptoSuite cryptoSuite;

    public String loadAccountIDFromPem(String pemFile) {
        CryptoSuite cryptoSuite = client.getCryptoSuite();

        cryptoSuite.loadAccount("pem",
                "account/0x15f6d84315bea7cb7929bd375afac88fbab18b0a.pem", null
        );
        return cryptoSuite.getCryptoKeyPair().getAddress();
    }

    public static void main(String[] args) {
        init();
//        try {
//            issuePoint("0x15f6d84315bea7cb7929bd375afac88fbab18b0a");
//        } catch (ContractException e) {
//            e.printStackTrace();
//        }
//        propagting();
        listBalance();
    }


    public static void init() {
        sdk = BcosSDK.build(configFile);

        client = sdk.getClient(Integer.valueOf(1));

        cryptoSuite = client.getCryptoSuite();

        cryptoSuite.loadAccount("pem",
                "account/0x15f6d84315bea7cb7929bd375afac88fbab18b0a.pem", null
        );
    }

    public static int issuePoint(String toAccountID) throws ContractException {
        Account account = Account.load("0xbc7fa7eeed94aafad658fd51fe2da843c7fa12aa", client,
                cryptoSuite.getCryptoKeyPair());
        TransactionReceipt receipt = account.deposit("0x15f6d84315bea7cb7929bd375afac88fbab18b0a", BigInteger.valueOf(900000));
        System.out.println("Deposit completed." + receipt.getOutput());
        BigInteger balance = account.getBalance();
//        TransactionReceipt receipt = account.withdrawal("0x15f6d84315bea7cb7929bd375afac88fbab18b0a", BigInteger.valueOf(2000));
        System.out.println("Balance = " + balance.toString());
        return 0;
    }

    public static BigInteger getRandom(int start,int end) {
        int num=(int) (Math.random()*(end-start+1)+start);
        return BigInteger.valueOf(num);
    }

    public static void propagting() {
        Account account = Account.load("0xbc7fa7eeed94aafad658fd51fe2da843c7fa12aa", client,
                cryptoSuite.getCryptoKeyPair());
        account.transfer("0x1ba73140e4c323745c9b3b18f147103bd89b1395", getRandom(10000, 100000));
        account.transfer("0x21d5141d0765714fa066a3287cd1ff03da5d4be4", getRandom(10000, 100000));
        account.transfer("0x31100667259f11bfaed4f8a5c9143aada2e42874", getRandom(10000, 100000));
        account.transfer("0x3802ad34a4b44239256320d1c53613b8c2184f97", getRandom(10000, 100000));
        account.transfer("0x525176453b4d8b315be5d48f6f36b34cd70c154e", getRandom(10000, 100000));
        account.transfer("0x6e8f174debea758743fbd123daebff0ef6859110", getRandom(10000, 100000));
        account.transfer("0x71857113502dd8fdf108fcab6042d87c798968c4", getRandom(10000, 100000));
        account.transfer("0x91d2752857d9a80fb713d9de69d71e167992da2f", getRandom(10000, 100000));
        account.transfer("0xd91bd83020b20553fd2ae745a6d50bc13c0ddca7", getRandom(10000, 100000));
    }

    public static void transfer() {
        Account account = Account.load("0xbc7fa7eeed94aafad658fd51fe2da843c7fa12aa", client,
                cryptoSuite.getCryptoKeyPair());
        account.transfer("0x1ba73140e4c323745c9b3b18f147103bd89b1395", getRandom(10000, 100000));
        account.transfer("0x21d5141d0765714fa066a3287cd1ff03da5d4be4", getRandom(10000, 100000));
        account.transfer("0x31100667259f11bfaed4f8a5c9143aada2e42874", getRandom(10000, 100000));
        account.transfer("0x3802ad34a4b44239256320d1c53613b8c2184f97", getRandom(10000, 100000));
        account.transfer("0x525176453b4d8b315be5d48f6f36b34cd70c154e", getRandom(10000, 100000));
        account.transfer("0x6e8f174debea758743fbd123daebff0ef6859110", getRandom(10000, 100000));
        account.transfer("0x71857113502dd8fdf108fcab6042d87c798968c4", getRandom(10000, 100000));
        account.transfer("0x91d2752857d9a80fb713d9de69d71e167992da2f", getRandom(10000, 100000));
        account.transfer("0xd91bd83020b20553fd2ae745a6d50bc13c0ddca7", getRandom(10000, 100000));
    }

    public static void listBalance() {
        Account account = Account.load("0xbc7fa7eeed94aafad658fd51fe2da843c7fa12aa", client,
                cryptoSuite.getCryptoKeyPair());
        try {
            System.out.println("balance of 0x15f6d84315bea7cb7929bd375afac88fbab18b0a = " +
                    account.getBalance("0x15f6d84315bea7cb7929bd375afac88fbab18b0a"));
        } catch (ContractException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("balance of 0x1ba73140e4c323745c9b3b18f147103bd89b1395 = "
                    + account.getBalance("0x1ba73140e4c323745c9b3b18f147103bd89b1395"));
        } catch (ContractException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("balance of 0x21d5141d0765714fa066a3287cd1ff03da5d4be4 = "
                    + account.getBalance("0x21d5141d0765714fa066a3287cd1ff03da5d4be4"));
        } catch (ContractException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("balance of 0x31100667259f11bfaed4f8a5c9143aada2e42874 = "
                    + account.getBalance("0x31100667259f11bfaed4f8a5c9143aada2e42874"));
        } catch (ContractException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("balance of 0x3802ad34a4b44239256320d1c53613b8c2184f97 = "
                    + account.getBalance("0x3802ad34a4b44239256320d1c53613b8c2184f97"));
        } catch (ContractException e) {
            e.printStackTrace();
        }


        try {
            System.out.println("balance of 0x525176453b4d8b315be5d48f6f36b34cd70c154e = "
                    + account.getBalance("0x525176453b4d8b315be5d48f6f36b34cd70c154e"));
        } catch (ContractException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("balance of 0x6e8f174debea758743fbd123daebff0ef6859110 = "
                    + account.getBalance("0x6e8f174debea758743fbd123daebff0ef6859110"));
        } catch (ContractException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("balance of 0x71857113502dd8fdf108fcab6042d87c798968c4 = "
                    + account.getBalance("0x71857113502dd8fdf108fcab6042d87c798968c4"));
        } catch (ContractException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("balance of 0x91d2752857d9a80fb713d9de69d71e167992da2f = "
                    + account.getBalance("0x91d2752857d9a80fb713d9de69d71e167992da2f"));
        } catch (ContractException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("balance of 0xd91bd83020b20553fd2ae745a6d50bc13c0ddca7 = "
                    + account.getBalance("0xd91bd83020b20553fd2ae745a6d50bc13c0ddca7"));
        } catch (ContractException e) {
            e.printStackTrace();
        }
    }
}

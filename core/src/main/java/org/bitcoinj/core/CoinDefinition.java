package org.bitcoinj.core;

import java.math.BigInteger;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Hash Engineering Solutions
 * Date: 5/3/14
 * To change this template use File | Settings | File Templates.
 */
public class CoinDefinition {


    public static final String coinName = "ION";
    public static final String coinTicker = "ION";
    public static final String coinURIScheme = "ion";

//    public static final String cryptsyMarketId = "0";
//    public static final String cryptsyMarketCurrency = "ION";
//    public static final String PATTERN_PRIVATE_KEY_START_UNCOMPRESSED = "[7]";
//    public static final String PATTERN_PRIVATE_KEY_START_COMPRESSED = "[X]";

    // To decrease granularity of timestamp
    // Supposed to be 2^n-1
    public static final int stakeTimestampMask = 15; // kernel.h
    public static final long futureDrift = 15; // TODO
    public static final long minerMiliSleep = 500; // TODO
    //common to all coins
    public static final int bulgarianConst = 128;
    //main.cpp#L2822 pchMessageStart[4] = { 0x70, 0x35, 0x22, 0x05 };
    public static final long packetMagic = 0xbff41ab6;
    public static final String checkpoint0 = "000001a7bb3214e3e1d2e4c256082b817a3c5dff5def37456ae16d7edaa508be";
    public static final String blackAlertSigningKey = "";
    public static final int ionv = 0x0488B21E; // TODO
    public static final int ionp = 0x0488ADE4; // TODO
    public static final int stakeMinConfirmations = 30; // main.cpp
    public static final long dust = 546;

    public enum CoinPrecision {
        Coins,
        Millicoins,
    }
//    public static final CoinPrecision coinPrecision = CoinPrecision.Coins;
//
//    public static final String UNSPENT_API_URL = "https://chainz.cryptoid.info/ion/api.dws?q=unspent";

//    public enum UnspentAPIType {
//        BitEasy,
//        Blockr,
//        Abe,
//        Cryptoid,
//    };
//    public static final UnspentAPIType UnspentAPI = UnspentAPIType.Cryptoid;

//    public static final String BLOCKEXPLORER_BASE_URL_PROD = "https://ionchain.xom/";
//    public static final String BLOCKEXPLORER_ADDRESS_PATH = "address/";             //blockr.io path
//    public static final String BLOCKEXPLORER_TRANSACTION_PATH = "tx/";              //blockr.io path
//    public static final String BLOCKEXPLORER_BLOCK_PATH = "block/";                 //blockr.io path
//    public static final String BLOCKEXPLORER_BASE_URL_TEST = "https://testnet.ionchain.com/";

//    public static final String DONATION_ADDRESS = "iZbDhBuCqq6NntURxHo5s5S5a7oYcFaZcY";  //Hash Engineering donation DASH address

    enum CoinHash {
        SHA256,
        scrypt,
        x11
    };

//    public static final CoinHash coinPOWHash = CoinHash.x11;

//    public static boolean checkpointFileSupport = true;

    public static final int targetTimespan = (int)(2 * 60);  //main.cpp#L988 nTargetTimespan = 2 * 60; // per difficulty cycle, on average.
    public static final int targetSpacing = 1 * 60;     //main.cpp#L43 nTargetSpacing = 1 * 60;
    public static final int targetSpacing2 = 1 * 60;
    public static final int interval = targetTimespan / targetSpacing;

    //main.cpp#L48 nCoinbaseMaturity = 500
    public static int spendableCoinbaseDepth = 30; //main.h: static const int COINBASE_MATURITY
    public static final long maxCoins = 55000000;                 //main.h:  MAX_MONEY
    public static final int maxBlockSize = 8000000;

    public static final long minTxFee = Coin.valueOf(1000).longValue();
//    public static final long DUST_LIMIT = Coin.valueOf(1000).longValue(); //main.h CTransaction::GetMinFee
//    public static final long INSTANTX_FEE = Coin.valueOf(1000000).longValue();

    //
    // Ion
    //

    public static final int protocolVersion = 60027;
    public static final int minProtocolVersion = 60016;
    public static final long blockVersion = 7;
    public static final int protocolV1RetargetingFixed = 38423; // TODO
    public static final long txTimeProtocolV3 = 1444028400; // TODO

//    public static final boolean supportsBloomFiltering = true; //Requires protocolVersion 70000 in the client

    //
    //  Production
    //
//    public static final boolean allowBitcoinPrivateKey = false; //for backward compatibility with previous versions
//    public static final int dumpedPrivateKeyHeader = 128;   //common to all coins
//    public static final long oldPacketMagic = 0xfbc0b6db;      //0xfb, 0xc0, 0xb6, 0xdb
//    public static final long PacketMagic = 0xbff41ab6;

    public static BigInteger maxTarget;

    public static int minBroadcastConnections = 0;   //0 for default; we need more peers.

    // TODO
    public static int subsidyDecreaseBlockCount = 525600;  // TODO   //main.cpp GetBlockValue(height, fee)

    public static BigInteger proofOfWorkLimit = Utils.decodeCompactBits(0x1e0fffffL);  //main.cpp bnProofOfWorkLimit (~uint256(0) >> 20);



    /** The string returned by getId() for the main, production network where people trade things. */
    public static final String ID_MAINNET = "com.ionomy.production";
    /** The string returned by getId() for the testnet. */
    public static final String ID_TESTNET = "com.ionomy.test";
    /** Unit test network. */
    public static final String ID_UNITTESTNET = "com.ionomy.unittest";
    /** Regtest network if any */
    public static final String ID_REGTESTNET = "com.ionomy.regtest";


    //
    // MAINNET
    //
    public static final int port = 58273; // 45104 //protocol.h GetDefaultPort(testnet=false)

    //Genesis Block Information from main.cpp: LoadBlockIndex
    ///main.cpp#L2521 nBits=1e0fffff
    public static final long genesisDifficultyTarget = (0x1e0fffffL);

    //clientmodel.cpp#L56 fromTime_t(1458750507L); // Genesis block's genesisBlockTime
    static public long genesisBlockTime = 1458750507L;                       //main.cpp: LoadBlockIndex
    //main.cpp#L2521 nNonce=164482
    static public long genesisBlockNonce = (468977L);                         //main.cpp: LoadBlockIndex

    //base58.h#L279 PUBKEY_ADDRESS = 25
    public static final int addressHeader = 25;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS
    public static final int p2shHeader = 85;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS
    static public String genesisHash = "000001a7bb3214e3e1d2e4c256082b817a3c5dff5def37456ae16d7edaa508be"; //main.cpp: hashGenesisBlock
    static public String genesisMerkleRoot = "a1de9df44936bd1dd483e217fa17ec1881d2caf741ca67a33f6cd6850183078c"; // ? or is this block 1 TODO
//    static public int genesisBlockValue = 10900000;                                                              //main.cpp: LoadBlockIndex
    //taken from the raw data of the block explorer
//    static public String genesisTxInBytes = "04ffff001d01044c5957697265642030392f4a616e2f3230313420546865204772616e64204578706572696d656e7420476f6573204c6976653a204f76657273746f636b2e636f6d204973204e6f7720416363657074696e6720426974636f696e73";   //"limecoin se convertira en una de las monedas mas segura del mercado, checa nuestros avances"
//    static public String genesisTxOutBytes = "040184710fa689ad5023690c80f3a49c8f13f8d45b8c857fbcbc8bc4a8e4d3eb4b10f4d4604fa08dce601aaf0f470216fe1b51850b4acf21b179c45070ac7b03a9";

    //net.cpp strDNSSeed
    static public String[] dnsSeeds = new String[] {
            "dnsseed.ionomy.com",
            "45.32.211.127",
    };


    //
    // TestNet
    //
    public static final int testPort = 51002;     //protocol.h GetDefaultPort(testnet=true)
    public static String testBlackAlertSigningKey = ""; // TODO
    public static final boolean supportsTestNet = true;
    public static final int testnetAddressHeader = 111;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS_TEST
    public static final int testnetp2shHeader = 196;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS_TEST
    public static final long testnetPacketMagic = 0x5ff21530;      //
    public static final String testnetGenesisHash = "0000070638e1fb122fb31b4753a5311f3c8784604d9f6ce42e8fec96d94173b4";
    static public long testnetGenesisBlockDifficultyTarget = (0x1f00ffffL);         //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockTime = 1458750507L;                       //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockNonce = (72686);                        //main.cpp: LoadBlockIndex
    static public String[] testnetDnsSeeds = new String[] {
    };


    //
    // Unit Test Information
    //
    public static final String UNITTEST_ADDRESS = "";
    public static final String UNITTEST_ADDRESS_PRIVATE_KEY = "";


    //main.cpp GetBlockValue(height, fee)
    public static final Coin GetBlockReward(int height)
    {
        int COIN = 1;
        Coin nSubsidy = Coin.valueOf(0, 0);
        if (height == 1)
            nSubsidy = Coin.valueOf(420000, 0);
        return nSubsidy;
    }

    //checkpoints.cpp Checkpoints::mapCheckpoints
    public static void initCheckpoints(Map<Integer, Sha256Hash> checkpoints)
    {
        // TODO
        // checkpoints.put(  11111, Sha256Hash.wrap("c195054e4ee5b2db531cb8f4c2a336a15ca0969406709f4930297d88e825cbb6"));
        // TODO
        // checkpoints.put(  33333, Sha256Hash.wrap("56b21543820df6d2bd45771452a47ee41594ae6475ab6fed6b942a6bf8d03841"));
    }

//    public static final int getInterval(int height, boolean testNet) {
//            return interval;
//    }
//    public static final int getIntervalCheckpoints() {
//        return interval;
//
//    }
//    public static final int getTargetTimespan(int height, boolean testNet) {
//        return targetTimespan;
//    }
}

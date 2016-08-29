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
    public static final String cryptsyMarketId = "0";
    public static final String cryptsyMarketCurrency = "ION";
    public static final String PATTERN_PRIVATE_KEY_START_UNCOMPRESSED = "[7]";
    public static final String PATTERN_PRIVATE_KEY_START_COMPRESSED = "[X]";

    public enum CoinPrecision {
        Coins,
        Millicoins,
    }
    public static final CoinPrecision coinPrecision = CoinPrecision.Coins;

    public static final String UNSPENT_API_URL = "https://chainz.cryptoid.info/ion/api.dws?q=unspent";
    public enum UnspentAPIType {
        BitEasy,
        Blockr,
        Abe,
        Cryptoid,
    };
    public static final UnspentAPIType UnspentAPI = UnspentAPIType.Cryptoid;

    public static final String BLOCKEXPLORER_BASE_URL_PROD = "https://ionchain.xom/";
    public static final String BLOCKEXPLORER_ADDRESS_PATH = "address/";             //blockr.io path
    public static final String BLOCKEXPLORER_TRANSACTION_PATH = "tx/";              //blockr.io path
    public static final String BLOCKEXPLORER_BLOCK_PATH = "block/";                 //blockr.io path
    public static final String BLOCKEXPLORER_BASE_URL_TEST = "https://testnet.ionchain.com/";

    public static final String DONATION_ADDRESS = "iZbDhBuCqq6NntURxHo5s5S5a7oYcFaZcY";  //Hash Engineering donation DASH address

    enum CoinHash {
        SHA256,
        scrypt,
        x11
    };
    public static final CoinHash coinPOWHash = CoinHash.x11;

    public static boolean checkpointFileSupport = true;

    public static final int TARGET_TIMESPAN = (int)(2 * 60);  // per difficulty cycle, on average.
    public static final int TARGET_SPACING = (int)(1 * 60);  // 1 minute per block.
    public static final int INTERVAL = TARGET_TIMESPAN / TARGET_SPACING;  // 1440 blocks

    public static final int getInterval(int height, boolean testNet) {
            return INTERVAL;
    }
    public static final int getIntervalCheckpoints() {
        return INTERVAL;

    }
    public static final int getTargetTimespan(int height, boolean testNet) {
        return TARGET_TIMESPAN;
    }

    public static int spendableCoinbaseDepth = 30; //main.h: static const int COINBASE_MATURITY
    public static final long MAX_COINS = 55000000;                 //main.h:  MAX_MONEY


    public static final long DEFAULT_MIN_TX_FEE = Coin.valueOf(1000).longValue();   // MIN_TX_FEE
    public static final long DUST_LIMIT = Coin.valueOf(1000).longValue(); //main.h CTransaction::GetMinFee
    public static final long INSTANTX_FEE = Coin.valueOf(1000000).longValue();

    //
    // Dash 0.12
    //
    public static final int PROTOCOL_VERSION = 60027;          //version.h PROTOCOL_VERSION
    public static final int MIN_PROTOCOL_VERSION = 60027;        //version.h MIN_PROTO_VERSION

    public static final int BLOCK_CURRENTVERSION = 7;   //CBlock::CURRENT_VERSION
    public static final int MAX_BLOCK_SIZE = 8000000;


    public static final boolean supportsBloomFiltering = true; //Requires PROTOCOL_VERSION 70000 in the client

    public static final int Port    = 58273;       //protocol.h GetDefaultPort(testnet=false)
    public static final int TestPort = 51002;     //protocol.h GetDefaultPort(testnet=true)

    //
    //  Production
    //
    public static final int AddressHeader = 25;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS
    public static final int p2shHeader = 85;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS
    public static final boolean allowBitcoinPrivateKey = false; //for backward compatibility with previous versions
    public static final int dumpedPrivateKeyHeader = 128;   //common to all coins
    public static final long oldPacketMagic = 0xfbc0b6db;      //0xfb, 0xc0, 0xb6, 0xdb
    public static final long PacketMagic = 0xbff41ab6;

    //Genesis Block Information from main.cpp: LoadBlockIndex
    static public long genesisBlockDifficultyTarget = (0x1e0fffff);         //main.cpp: LoadBlockIndex
    static public long genesisBlockTime = 1458750507L;                       //main.cpp: LoadBlockIndex
    static public long genesisBlockNonce = (468977L);                         //main.cpp: LoadBlockIndex
    static public String genesisHash = "000001a7bb3214e3e1d2e4c256082b817a3c5dff5def37456ae16d7edaa508be"; //main.cpp: hashGenesisBlock
    static public String genesisMerkleRoot = "a1de9df44936bd1dd483e217fa17ec1881d2caf741ca67a33f6cd6850183078c"; // ? or is this block 1 TODO
    static public int genesisBlockValue = 10900000;                                                              //main.cpp: LoadBlockIndex
    //taken from the raw data of the block explorer
    static public String genesisTxInBytes = "04ffff001d01044c5957697265642030392f4a616e2f3230313420546865204772616e64204578706572696d656e7420476f6573204c6976653a204f76657273746f636b2e636f6d204973204e6f7720416363657074696e6720426974636f696e73";   //"limecoin se convertira en una de las monedas mas segura del mercado, checa nuestros avances"
    static public String genesisTxOutBytes = "040184710fa689ad5023690c80f3a49c8f13f8d45b8c857fbcbc8bc4a8e4d3eb4b10f4d4604fa08dce601aaf0f470216fe1b51850b4acf21b179c45070ac7b03a9";

    //net.cpp strDNSSeed
    static public String[] dnsSeeds = new String[] {
            "dnsseed.ionomy.com",
            "45.32.211.127",
    };

    public static int minBroadcastConnections = 0;   //0 for default; we need more peers.

    //
    // TestNet
    //
    public static final boolean supportsTestNet = true;
    public static final int testnetAddressHeader = 111;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS_TEST
    public static final int testnetp2shHeader = 196;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS_TEST
    public static final long testnetPacketMagic = 0x5ff21530;      //
    public static final String testnetGenesisHash = "0000070638e1fb122fb31b4753a5311f3c8784604d9f6ce42e8fec96d94173b4";
    static public long testnetGenesisBlockDifficultyTarget = (0x1f00ffffL);         //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockTime = 1458750507L;                       //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockNonce = (72686);                        //main.cpp: LoadBlockIndex





    //main.cpp GetBlockValue(height, fee)
    public static final Coin GetBlockReward(int height)
    {
        int COIN = 1;
        Coin nSubsidy = Coin.valueOf(0, 0);
        if (height == 1)
            nSubsidy = Coin.valueOf(420000, 0);
        return nSubsidy;
    }

    // TODO
    public static int subsidyDecreaseBlockCount = 525600;  // TODO   //main.cpp GetBlockValue(height, fee)

    public static BigInteger proofOfWorkLimit = Utils.decodeCompactBits(0x1e0fffffL);  //main.cpp bnProofOfWorkLimit (~uint256(0) >> 20);


    static public String[] testnetDnsSeeds = new String[] {
    };

    //from main.h: CAlert::CheckSignature
    // ?
    public static final String SATOSHI_KEY = ""; //"048240a8748a80a286b270ba126705ced4f2ce5a7847b3610ea3c06513150dade2a8512ed5ea86320824683fc0818f0ac019214973e677acd1244f6d0571fc5103";
    // ?
    public static final String TESTNET_SATOSHI_KEY = ""; //"04517d8a699cb43d3938d7b24faaff7cda448ca4ea267723ba614784de661949bf632d6304316b244646dea079735b9a6fc4af804efb4752075b9fe2245e14e412";

    /** The string returned by getId() for the main, production network where people trade things. */
    public static final String ID_MAINNET = "com.ionomy.production";
    /** The string returned by getId() for the testnet. */
    public static final String ID_TESTNET = "com.ionomy.test";
    /** Unit test network. */
    public static final String ID_UNITTESTNET = "com.google.darkcoin.unittest";

    //checkpoints.cpp Checkpoints::mapCheckpoints
    public static void initCheckpoints(Map<Integer, Sha256Hash> checkpoints)
    {
        // TODO
        // checkpoints.put(  11111, Sha256Hash.wrap("c195054e4ee5b2db531cb8f4c2a336a15ca0969406709f4930297d88e825cbb6"));
        // TODO
        // checkpoints.put(  33333, Sha256Hash.wrap("56b21543820df6d2bd45771452a47ee41594ae6475ab6fed6b942a6bf8d03841"));
    }

    //Unit Test Information
    public static final String UNITTEST_ADDRESS = "";
    public static final String UNITTEST_ADDRESS_PRIVATE_KEY = "";

}
